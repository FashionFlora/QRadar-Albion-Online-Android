package com.minhui.vpn.tunnel;


import com.minhui.vpn.KeyHandler;
import com.minhui.vpn.nat.NatSession;
import com.minhui.vpn.nat.NatSessionManager;
import com.minhui.vpn.service.FirewallVpnService;
import com.minhui.vpn.utils.AppDebug;
import com.minhui.vpn.utils.DebugLog;
import com.minhui.vpn.utils.VpnServiceHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zengzheying on 15/12/29.
 */
public abstract class TcpTunnel implements KeyHandler
{
    public static long sessionCount;
    protected InetSocketAddress mDestAddress;
    private SocketChannel mInnerChannel;
    private Selector mSelector;
    private boolean isHttpsRequest = false;
    private TcpTunnel mBrotherTunnel;
    private boolean mDisposed;
    private InetSocketAddress mServerEP;
    short portKey;
    ConcurrentLinkedQueue<ByteBuffer> needWriteData = new ConcurrentLinkedQueue<>();

    public TcpTunnel(SocketChannel innerChannel, Selector selector)
    {
        mInnerChannel = innerChannel;
        mSelector = selector;
        sessionCount++;
    }

    public TcpTunnel(InetSocketAddress serverAddress, Selector selector, short portKey) throws IOException
    {
        SocketChannel innerChannel = SocketChannel.open();
        innerChannel.configureBlocking(false);
        this.mInnerChannel = innerChannel;
        this.mSelector = selector;
        this.mServerEP = serverAddress;
        this.portKey = portKey;
        sessionCount++;
    }

    @Override
    public void onKeyReady(SelectionKey key)
    {
        if (key.isReadable())
        {
            onReadable(key);
        }
        else if (key.isWritable())
        {
            onWritable(key);
        }
        else if (key.isConnectable())
        {
            onConnectable();
        }
    }

    protected abstract void onConnected() throws Exception;

    protected abstract boolean isTunnelEstablished();

    protected abstract void beforeSend(ByteBuffer buffer) throws Exception;

    protected abstract void afterReceived(ByteBuffer buffer) throws Exception;

    protected abstract void onDispose();

    public void setBrotherTunnel(TcpTunnel brotherTunnel)
    {
        this.mBrotherTunnel = brotherTunnel;
    }

    public void connect(InetSocketAddress destAddress) throws Exception
    {
        if (VpnServiceHelper.protect(mInnerChannel.socket()))
        {
            mDestAddress = destAddress;

            mInnerChannel.register(mSelector, SelectionKey.OP_CONNECT, this);
            mInnerChannel.connect(mServerEP);

            DebugLog.i("Connecting to %s", mServerEP);
        }
        else
        {
            throw new Exception("VPN protect socket failed.");
        }
    }

    public void onConnectable()
    {
        try
        {
            if (mInnerChannel.finishConnect())
            {
                onConnected();
                DebugLog.i("Connected to %s", mServerEP);
            }
            else
            {
                DebugLog.e("Connect to %s failed.", mServerEP);
                this.dispose();
            }
        }
        catch (Exception e)
        {
            if (AppDebug.IS_DEBUG)
            {
                e.printStackTrace(System.err);
            }

            DebugLog.e("Connect to %s failed: %s", mServerEP, e);
            this.dispose();
        }
    }

    protected void beginReceived() throws Exception
    {
        if (mInnerChannel.isBlocking())
        {
            mInnerChannel.configureBlocking(false);
        }

        mSelector.wakeup();
        mInnerChannel.register(mSelector, SelectionKey.OP_READ, this);
    }

    public void onReadable(SelectionKey key)
    {
        try
        {
            ByteBuffer buffer = ByteBuffer.allocate(FirewallVpnService.MUTE_SIZE);
            buffer.clear();
            int bytesRead = mInnerChannel.read(buffer);

            if (bytesRead > 0)
            {
                buffer.flip();
                afterReceived(buffer);
                sendToBrother(key, buffer);
            }
            else if (bytesRead < 0)
            {
                this.dispose();
            }
        }
        catch (Exception ex)
        {
            if (AppDebug.IS_DEBUG)
            {
                ex.printStackTrace(System.err);
            }

            DebugLog.e("onReadable catch an exception: %s", ex);
            this.dispose();
        }
    }

    protected void sendToBrother(SelectionKey key, ByteBuffer buffer) throws Exception
    {
        if (isTunnelEstablished() && buffer.hasRemaining())
        {
            //    mBrotherTunnel.beforeSend(buffer);
            mBrotherTunnel.getWriteDataFromBrother(buffer);
        }
    }

    private void getWriteDataFromBrother(ByteBuffer buffer)
    {
        if (buffer.hasRemaining() && needWriteData.size() == 0)
        {
            int writeSize = 0;

            try
            {
                writeSize = write(buffer);
            }
            catch (Exception e)
            {
                writeSize = 0;
                e.printStackTrace();
            }

            if (writeSize > 0)
            {
                return;
            }
        }
        needWriteData.offer(buffer);

        try
        {
            mSelector.wakeup();
            mInnerChannel.register(mSelector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, this);
        }
        catch (ClosedChannelException e)
        {
            e.printStackTrace();
        }
    }

    protected int write(ByteBuffer buffer) throws Exception
    {
        int byteSendSum = 0;
    
        while (buffer.hasRemaining())
        {
            int byteSent = mInnerChannel.write(buffer);
            byteSendSum += byteSent;

            if (byteSent == 0)
            {
                break;
            }
        }
        return byteSendSum;
    }


    public void onWritable(SelectionKey key)
    {
        try
        {
            ByteBuffer mSendRemainBuffer = needWriteData.poll();

            if (mSendRemainBuffer == null)
            {
                return;
            }

            write(mSendRemainBuffer);

            if (needWriteData.size() == 0)
            {
                try
                {
                    mSelector.wakeup();
                    mInnerChannel.register(mSelector, SelectionKey.OP_READ, this);
                }
                catch (ClosedChannelException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            if (AppDebug.IS_DEBUG)
            {
                ex.printStackTrace(System.err);
            }

            DebugLog.e("onWritable catch an exception: %s", ex);

            this.dispose();
        }
    }

    protected void onTunnelEstablished() throws Exception
    {
        this.beginReceived();
        mBrotherTunnel.beginReceived();
    }

    public void dispose()
    {
        disposeInternal(true);
    }

    void disposeInternal(boolean disposeBrother)
    {
        if (!mDisposed)
        {
            try
            {
                mInnerChannel.close();
            }
            catch (Exception ex)
            {
                if (AppDebug.IS_DEBUG)
                {
                    ex.printStackTrace(System.err);
                }

                DebugLog.e("InnerChannel close catch an exception: %s", ex);
            }

            if (mBrotherTunnel != null && disposeBrother)
            {
                mBrotherTunnel.disposeInternal(false);
            }

            mInnerChannel = null;
            mSelector = null;
            mBrotherTunnel = null;
            mDisposed = true;
            sessionCount--;

            onDispose();
            NatSessionManager.removeSession(portKey);
        }
    }

    public void setIsHttpsRequest(boolean isHttpsRequest)
    {
        this.isHttpsRequest = isHttpsRequest;
    }

    public boolean isHttpsRequest()
    {
        return isHttpsRequest;
    }
}
