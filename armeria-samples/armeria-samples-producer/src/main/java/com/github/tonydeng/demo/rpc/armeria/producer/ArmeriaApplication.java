package com.github.tonydeng.demo.rpc.armeria.producer;

import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.thrift.THttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.github.tonydeng.demo.rpc.armeria.producer"})
public class ArmeriaApplication {
    static final int PORT = 9000;

    public static void main(String[] args) {
        log.info("init armeria application.......");
        SpringApplication.run(ArmeriaApplication.class, args);
        final ServerBuilder sb = new ServerBuilder();

        sb.service("/book", THttpService.of(new BookServiceProducer(),
                ThriftSerializationFormats.COMPACT));
        sb.port(PORT, SessionProtocol.HTTP);
        sb.serviceUnder("/docs/", new DocService());
        sb.build().start().join();
        log.info("ArmeriaApplication Start.......");
    }
}
