package com.javatechie.connection;

import com.javatechie.service.manager.GrpcManager;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GrpcConnection implements BaseConnection {

    private final GrpcManager grpcManager;

    public GrpcConnection() {
        this.grpcManager = new GrpcManager(initChannels());
        this.grpcManager.initStubs();
    }

    private HashMap<String, ManagedChannel> initChannels() {
        HashMap<String, ManagedChannel> channelMap = new HashMap<>();
        channelMap.put(
                "midas",
                ManagedChannelBuilder.forAddress("localhost", 9090)
                        .usePlaintext()
                        .build()
        );
        channelMap.put(
                "robin_hood",
                ManagedChannelBuilder.forAddress("localhost", 9090)
                        .usePlaintext()
                        .build()
        );
        channelMap.put(
                "coin_moin",
                ManagedChannelBuilder.forAddress("localhost", 9090)
                        .usePlaintext()
                        .build()
        );
        return channelMap;
    }

    @Override
    public void run() {
        grpcManager.run("midas", "blocking");
        grpcManager.run("midas", "async");
        grpcManager.run("coin_moin", "blocking");
        grpcManager.run("coin_moin", "async");
        grpcManager.run("robin_hood", "blocking");
        grpcManager.run("robin_hood", "async");
    }

    @Override
    public EConnectionType getConnectionType() {
        return EConnectionType.GRPC;
    }
}
