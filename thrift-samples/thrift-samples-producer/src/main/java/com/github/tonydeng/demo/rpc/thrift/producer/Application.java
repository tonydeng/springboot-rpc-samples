package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.github.tonydeng.demo.rpc.thrift"})
public class Application {
    private static final int PORT = 9000;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {
            log.info("Thrift RPC's Server Start......");
            TProcessor processor = new BookService.Processor<BookService.Iface>(new BookServiceProducer());
            TServerSocket serverTransport = new TServerSocket(PORT);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            log.error("Thrift RPC's Server serve error \n", e);
        }
    }
}
