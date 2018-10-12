package com.github.tonydeng.demo.rpc.swift.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.github.tonydeng.demo.rpc.swift.producer"})
public class SwiftApplication {
    private static final int PORT = 9000;

    public static void main(String[] args) {
        SpringApplication.run(SwiftApplication.class, args);
//        try {
//            log.info("Thrift RPC's Server Start......");
//
//            TProcessor processor = new TBaseProcessor<UserService>(new UserServiceProducer());
//            TServerSocket serverTransport = new TServerSocket(PORT);
//
//            TServer.Args tArgs = new TServer.Args(serverTransport);
//            tArgs.processor(processor);
//            tArgs.protocolFactory(new TBinaryProtocol.Factory());
//            TServer server = new TSimpleServer(tArgs);
//            server.serve();
//        } catch (TTransportException e) {
//            log.error("Thrift RPC's Server serve error \n", e);
//        }
    }
}
