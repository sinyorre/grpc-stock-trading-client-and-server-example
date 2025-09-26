package com.javatechie.service;


import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.time.Instant;
import java.util.Random;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {


    @Override
    public void getStockPrice(StockRequest request,
                              StreamObserver<StockResponse> responseObserver) {
        for (int i = 0; i < 5; i++) {
            int randomValue = new Random().nextInt(60);
            StockResponse response = StockResponse.newBuilder()
                    .setStockSymbol("AMD")
                    .setPrice(400.0 + randomValue)
                    .setTimestamp(Instant.now().toString())
                    .build();
            responseObserver.onNext(response);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getStockStatus(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        StockResponse stockResponse = StockResponse.newBuilder()
                .setStockSymbol("APPL")
                .setPrice(300.0)
                .setTimestamp(Instant.now().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();
    }
}
