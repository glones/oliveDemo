package org.example.testforconbo.configs;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.testforconbo.services.DetectionIngestionServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GrpcServerConfig {

    @Value("${grpc.port:6565}")
    private int grpcPort;

    @Bean
    public Server grpcServer(DetectionIngestionServiceImpl detectionService) {
        return NettyServerBuilder
                .forPort(grpcPort)
                .addService(detectionService)
                .build();
    }

    @Bean
    public ApplicationRunner grpcRunner(Server server) {
        return args -> {
            server.start();
            log.info("gRPC server started on port {}", server.getPort());
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                log.info("Shutting down gRPC server...");
                server.shutdown();
            }));
        };
    }
}
