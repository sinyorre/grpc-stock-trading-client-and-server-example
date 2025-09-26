package com.javatechie.service.manager;

import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockTradingServiceGrpc;
import com.javatechie.strategy.AsyncStubStrategy;
import com.javatechie.strategy.BlockingStubStrategy;
import com.javatechie.strategy.StockStubStrategy;
import io.grpc.ManagedChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GrpcManager implements BaseManager {
    private final Map<String, Map<String, StockStubStrategy>> stockMarketAndStubStrategyMap = new HashMap<>();
    private final HashMap<String, ManagedChannel> channelMap;

    public GrpcManager(HashMap<String, ManagedChannel> channelMap) {
        this.channelMap = channelMap;
    }

    public void addStock(String stockMarketName) {
        ManagedChannel channel = channelMap.get(stockMarketName);
        StockTradingServiceGrpc.StockTradingServiceStub asyncStub = StockTradingServiceGrpc.newStub(channel);
        StockTradingServiceGrpc.StockTradingServiceBlockingStub blockingStub = StockTradingServiceGrpc.newBlockingStub(channel);

        Map<String, StockStubStrategy> strategyMap = new HashMap<>();
        strategyMap.put("async", new AsyncStubStrategy(asyncStub));
        strategyMap.put("blocking", new BlockingStubStrategy(blockingStub));

        stockMarketAndStubStrategyMap.put(stockMarketName, strategyMap);
    }

    public void initStubs() {
        addStock("robin_hood");
        addStock("midas");
        addStock("coin_moin");
    }

    public void run(String stockMarketName, String stubType) {
        Optional.ofNullable(stockMarketAndStubStrategyMap.get(stockMarketName))
                .map(strategyMap -> strategyMap.get(stubType.toLowerCase()))
                .ifPresent(strategy -> {
                    StockRequest request = StockRequest.newBuilder().setStockSymbol("AMD").build();
                    strategy.get(request, stockMarketName);
                });
    }
}
