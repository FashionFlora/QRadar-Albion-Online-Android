package com.minhui.vpn.tunnel;

import android.net.VpnService;
import android.os.Handler;
import android.os.Looper;

import com.minhui.vpn.KeyHandler;
import com.minhui.vpn.Packet;
import com.minhui.vpn.PhotonPackageParser.PhotonPacketThreading;
import com.minhui.vpn.UDPServer;
import com.minhui.vpn.VPNConstants;
import com.minhui.vpn.VPNLog;
import com.minhui.vpn.nat.NatSession;
import com.minhui.vpn.nat.NatSessionManager;
import com.minhui.vpn.processparse.PortHostService;
import com.minhui.vpn.utils.SocketUtils;
import com.minhui.vpn.utils.TcpDataSaveHelper;
import com.minhui.vpn.utils.ThreadProxy;
import com.minhui.vpn.utils.TimeFormatUtil;
import com.minhui.vpn.utils.VpnServiceHelper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by minhui.zhu on 2017/7/11.
 * Copyright © 2017年 minhui.zhu. All rights reserved.
 */

public class UDPTunnel implements KeyHandler
{
    private static final String TAG = UDPTunnel.class.getSimpleName();
    private final VpnService vpnService;
    private final Selector selector;
    private final UDPServer vpnServer;
    private final Queue<Packet> outputQueue;
    private TcpDataSaveHelper helper;
    private Packet referencePacket;
    private SelectionKey selectionKey;

    private DatagramChannel channel;
    private final ConcurrentLinkedQueue<Packet> toNetWorkPackets = new ConcurrentLinkedQueue<>();
    private static final int HEADER_SIZE = Packet.IP4_HEADER_SIZE + Packet.UDP_HEADER_SIZE;
    private Short portKey;
    String ipAndPort;
    private final NatSession session;
    private final Handler handler;

    public UDPTunnel(VpnService vpnService, Selector selector, UDPServer vpnServer, Packet packet, Queue<Packet> outputQueue, short portKey)
    {
        this.vpnService = vpnService;
        this.selector = selector;
        this.vpnServer = vpnServer;
        this.referencePacket = packet;
        this.ipAndPort = packet.getIpAndPort();
        this.outputQueue = outputQueue;
        this.portKey = portKey;
        this.session = NatSessionManager.getSession(portKey);
        this.handler = new Handler(Looper.getMainLooper());

        if (VpnServiceHelper.isUDPDataNeedSave())
        {
            String helperDir = new StringBuilder()
                    .append(VPNConstants.DATA_DIR)
                    .append(TimeFormatUtil.formatYYMMDDHHMMSS(session.vpnStartTime))
                    .append("/")
                    .append(session.getUniqueName())
                    .toString();

            helper = new TcpDataSaveHelper(helperDir);
        }
    }

    private void processKey(SelectionKey key)
    {
        if (key.isWritable())
        {
            processSend();
        }
        else if (key.isReadable())
        {
            processReceived();
        }

        updateInterests();
    }

    private void processReceived()
    {
        //VPNLog.d(TAG, "processReceived:" + ipAndPort);
        ByteBuffer receiveBuffer = SocketUtils.getByteBuffer();

        // Leave space for the header
        receiveBuffer.position(HEADER_SIZE);
        int readBytes = 0;

        try
        {
            readBytes = channel.read(receiveBuffer);
        }
        catch (Exception e)
        {
            VPNLog.d(TAG, "failed to read udp datas ");
            return;
        }

        if (readBytes == -1)
        {
            VPNLog.d(TAG, "read  data error :" + ipAndPort);
        }
        else if (readBytes == 0)
        {
            VPNLog.d(TAG, "read no data :" + ipAndPort);
        }
        else
        {
            // VPNLog.d(TAG, "read readBytes:" + readBytes + "ipAndPort:" + ipAndPort);
            Packet newPacket = referencePacket.duplicated();

            try
            {
                if (newPacket.udpHeader.destinationPort == 5056 || newPacket.udpHeader.sourcePort == 5056)
                {
                    ByteBuffer buffer = deepCopy(receiveBuffer);
                    buffer.limit(buffer.capacity());
                    buffer.position(HEADER_SIZE);

                    photonPacketThreading.receivePacketFromOtherThread(buffer);
                }
            }
            catch (Exception ignored)
            {

            }

            newPacket.updateUDPBuffer(receiveBuffer, readBytes);
            receiveBuffer.position(HEADER_SIZE + readBytes);

            outputQueue.offer(newPacket);
        }
    }

    public static ByteBuffer deepCopy( ByteBuffer orig )
    {
        int pos = orig.position(), lim = orig.limit();

        try
        {
            orig.position(0).limit(orig.capacity()); // set range to entire buffer
            ByteBuffer toReturn = deepCopyVisible(orig); // deep copy range
            toReturn.position(pos).limit(lim); // set range to original
            return toReturn;
        }
        finally // do in finally in case something goes wrong we don't bork the orig
        {
            orig.position(pos).limit(lim); // restore original
        }
    }

    public static ByteBuffer deepCopyVisible( ByteBuffer orig )
    {
        int pos = orig.position();

        try
        {
            ByteBuffer toReturn;

            // try to maintain implementation to keep performance
            if( orig.isDirect())
                toReturn = ByteBuffer.allocateDirect(orig.remaining());
            else
                toReturn = ByteBuffer.allocate(orig.remaining());

            toReturn.put(orig);
            toReturn.order(orig.order());

            return (ByteBuffer) toReturn.position(0);
        }
        finally
        {
            orig.position(pos);
        }
    }
    PhotonPacketThreading photonPacketThreading = new PhotonPacketThreading();

    private void processSend()
    {
        // VPNLog.d(TAG, "processWriteUDPData " + ipAndPort);

        Packet toNetWorkPacket = getToNetWorkPackets();

        if (toNetWorkPacket == null)
        {
            VPNLog.d(TAG, "write data  no packet ");
            return;
        }

        try
        {
            ByteBuffer payloadBuffer = toNetWorkPacket.backingBuffer;
            session.packetSent++;
            int sendSize = payloadBuffer.limit() - payloadBuffer.position();
            session.bytesSent += sendSize;

            try
            {
                if (toNetWorkPacket.udpHeader.destinationPort == 5056 || toNetWorkPacket.udpHeader.sourcePort == 5056)
                {
                    ByteBuffer buffer = deepCopy(payloadBuffer);
                    buffer.limit(buffer.capacity());
                    buffer.position(HEADER_SIZE);

                    photonPacketThreading.receivePacketFromOtherThread(buffer);
                }
            }
            catch (Exception ignored)
            {

            }

            while (payloadBuffer.hasRemaining())
            {
                channel.write(payloadBuffer);
            }
        }
        catch (IOException e)
        {
            VPNLog.w(TAG, "Network write error: " + ipAndPort, e);
            vpnServer.closeUDPConn(this);
        }
    }

    public void initConnection()
    {
        VPNLog.d(TAG, "init  ipAndPort:" + ipAndPort);
        InetAddress destinationAddress = referencePacket.ip4Header.destinationAddress;
        int destinationPort = referencePacket.udpHeader.destinationPort;

        try
        {
            channel = DatagramChannel.open();
            vpnService.protect(channel.socket());
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(destinationAddress, destinationPort));
            selector.wakeup();
            selectionKey = channel.register(selector,
                    SelectionKey.OP_READ, this);
        }
        catch (IOException e)
        {
            SocketUtils.closeResources(channel);
            return;
        }

        referencePacket.swapSourceAndDestination();
        addToNetWorkPacket(referencePacket);
    }

    public void processPacket(Packet packet)
    {
        addToNetWorkPacket(packet);
        updateInterests();
    }

    public void close()
    {
        try
        {
            if (selectionKey != null)
            {
                selectionKey.cancel();
            }

            if (channel != null)
            {
                channel.close();
            }

            if (session.appInfo == null && PortHostService.getInstance() != null)
            {
                PortHostService.getInstance().refreshSessionInfo();
            }

            handler.postDelayed(() -> ThreadProxy.getInstance().execute(() ->
            {
                if (session.receiveByteNum == 0 && session.bytesSent == 0)
                {
                    return;
                }

            }), 1000);
        } catch (Exception e) {
            VPNLog.w(TAG, "error to close UDP channel IpAndPort" + ipAndPort + ",error is " + e.getMessage());
        }
    }

    Packet getToNetWorkPackets() {
        return toNetWorkPackets.poll();
    }

    void addToNetWorkPacket(Packet packet)
    {
        toNetWorkPackets.offer(packet);
        updateInterests();
    }

    void updateInterests()
    {
        int ops;

        if (toNetWorkPackets.isEmpty())
        {
            ops = SelectionKey.OP_READ;
        }
        else
        {
            ops = SelectionKey.OP_WRITE | SelectionKey.OP_READ;
        }

        selector.wakeup();
        selectionKey.interestOps(ops);

        // VPNLog.d(TAG, "updateInterests ops:" + ops + ",ip" + ipAndPort);
    }

    @Override
    public void onKeyReady(SelectionKey key) {
        processKey(key);
    }

    public Short getPortKey() {
        return portKey;
    }
}
