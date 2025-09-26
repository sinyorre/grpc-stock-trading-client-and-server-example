package com.javatechie.connection;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EthernetConnection implements BaseConnection {

    @PostConstruct
    public void init() {
        // init connection
    }

    @Override
    public void run() {

    }

    @Override
    public EConnectionType getConnectionType() {
        return EConnectionType.ETHERNET;
    }
}
