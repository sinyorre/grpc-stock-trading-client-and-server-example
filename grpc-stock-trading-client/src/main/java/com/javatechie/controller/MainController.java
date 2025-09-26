package com.javatechie.controller;

import com.javatechie.connection.ConnectionFactory;
import com.javatechie.connection.EConnectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private ConnectionFactory connectionFactory;

    @GetMapping
    public String hello() {
        connectionFactory
                .getRelatedConnection(EConnectionType.GRPC)
                .run();
        return "mandosi";
    }
}
