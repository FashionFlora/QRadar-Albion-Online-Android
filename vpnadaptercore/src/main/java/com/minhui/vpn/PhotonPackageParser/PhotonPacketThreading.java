package com.minhui.vpn.PhotonPackageParser;

import com.minhui.vpn.PhotonPackageParser.classes.PhotonPacketParser;
import com.minhui.vpn.PhotonPackageParser.classes.PhotonPacketTemp;
import com.minhui.vpn.tunnel.UDPTunnel;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class PhotonPacketThreading {
    private ExecutorService executor;
    private LinkedBlockingQueue<ByteBuffer> packetQueue;
    PhotonPacketParser photonPacketParser = new PhotonPacketParser();


    public PhotonPacketThreading() {
        int numThreads = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(numThreads);
        packetQueue = new LinkedBlockingQueue<>();
    }


    public void receivePacketFromOtherThread(ByteBuffer packet) {


        synchronized (packetQueue) {

            packetQueue.offer(packet);
            if (packetQueue.size() == 1) {
                processNextPacket();
            }
        }
    }

    PhotonPacketTemp photonPacketTemp = new PhotonPacketTemp();
    private void processNextPacket() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ByteBuffer packet;
                synchronized (packetQueue) {
                    packet = packetQueue.poll();
                }
                if (packet != null) {

                    try
                    {
                        photonPacketTemp.receive(packet);
                       // photonPacketParser.handle(packet);
                    }
                    catch (Exception ignored) { }

                    processNextPacket();
                }
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
    }
}