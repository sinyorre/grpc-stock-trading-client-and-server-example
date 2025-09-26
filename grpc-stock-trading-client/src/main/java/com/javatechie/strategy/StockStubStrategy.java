package com.javatechie.strategy;

import com.javatechie.grpc.StockRequest;

public interface StockStubStrategy {
    void get(StockRequest stockRequest, String stockMarketName);
}
