package com.javatechie.connection;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConnectionFactory {
    private final Map<EConnectionType, BaseConnection> connectionMap;

    public ConnectionFactory(List<BaseConnection> connections) {
        connectionMap = connections.stream()
                .collect(
                        Collectors.toUnmodifiableMap(
                                BaseConnection::getConnectionType,
                                Function.identity()
                        )
                );
    }

    public BaseConnection getRelatedConnection(EConnectionType connectionType) {
        return Optional.ofNullable(connectionMap.get(connectionType))
                .orElseThrow(IllegalArgumentException::new);
    }
}
