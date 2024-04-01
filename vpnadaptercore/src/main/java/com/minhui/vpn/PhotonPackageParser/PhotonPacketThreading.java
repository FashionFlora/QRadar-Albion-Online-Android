package com.minhui.vpn.PhotonPackageParser;

import com.minhui.vpn.PhotonPackageParser.classes.PhotonPacketParser;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class PhotonPacketThreading
{
    private ExecutorService executor;
    private LinkedBlockingQueue<ByteBuffer> packetQueue;
    private PhotonPacketParser photonPacketParser = new PhotonPacketParser();

    public PhotonPacketThreading()
    {
        int numThreads = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(numThreads);
        packetQueue = new LinkedBlockingQueue<>();
    }

    public void receivePacketFromOtherThread(ByteBuffer packet)
    {
        synchronized (packetQueue)
        {
            packetQueue.offer(packet);

            if (packetQueue.size() == 1)
            {
                processNextPacket();
            }
        }
    }

    private void processNextPacket()
    {
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                ByteBuffer packet;

                synchronized (packetQueue)
                {
                    packet = packetQueue.poll();
                }

                if (packet != null)
                {
                    try
                    {
                        photonPacketParser.receive(packet);
                    }
                    catch (Exception ignored)
                    {

                    }

                    processNextPacket();
                }
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
    }
}