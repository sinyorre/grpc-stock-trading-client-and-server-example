package com.javatechie.strategy;

import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;

public class BlockingStubStrategy implements StockStubStrategy {
    private final StockTradingServiceGrpc.StockTradingServiceBlockingStub blockingStub;

    public BlockingStubStrategy(StockTradingServiceGrpc.StockTradingServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    @Override
    public void get(StockRequest stockRequest, String stockMarketName) {
        StockResponse stockResponse = blockingStub.getStockStatus(stockRequest);
        System.out.println(stockResponse.getStockSymbol() + " STOCK STATUS is OK (BLOCKING STUB)" + " (" + stockMarketName + ")");
        System.out.println("-------------------");
        // send to ui via ws
    }
}
