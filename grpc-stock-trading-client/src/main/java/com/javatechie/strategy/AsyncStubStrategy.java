package com.javatechie.strategy;

import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class AsyncStubStrategy implements StockStubStrategy {

    private final StockTradingServiceGrpc.StockTradingServiceStub asyncStub;

    public AsyncStubStrategy(StockTradingServiceGrpc.StockTradingServiceStub asyncStub) {
        this.asyncStub = asyncStub;
    }

    @Override
    public void get(StockRequest stockRequest, String stockMarketName) {
        asyncStub.getStockPrice(stockRequest, new StreamObserver<StockResponse>() {
            @Override
            public void onNext(StockResponse stockResponse) {
                System.out.println(stockResponse.getStockSymbol() + " STOCK PRICE : " + stockResponse.getPrice() + " (ASYNC STUB) " + "(" + stockMarketName + ")");
                System.out.println("-------------------");
                // send to ui via ws
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("COMPLETED (ASYNC STUB)");
                // send to ui via ws
            }
        });
    }
}
