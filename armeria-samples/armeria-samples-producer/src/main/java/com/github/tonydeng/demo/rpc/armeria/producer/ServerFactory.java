package com.github.tonydeng.demo.rpc.armeria.producer;

import com.github.tonydeng.demo.rpc.scanner.PackageAnnotationScanner;
import com.google.common.collect.Lists;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public final class ServerFactory {
    private ServerFactory() {

    }

    public static Server of(int port) {
        checkArgument(port >= 0 && port <= 65535, "port: %s (expected: 0-65535");
        final ServerBuilder sb = new ServerBuilder();

        Set<Class<?>> classes = PackageAnnotationScanner.scan(ServerFactory.class.getClassLoader(),
                Lists.newArrayList("com.github.tonydeng.demo.rpc.armeria.producer"),
                Lists.newArrayList(Service.class));

        classes.forEach(c -> {
            try {
                sb.annotatedService(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });


        sb.http(port);
        sb.serviceUnder("/docs/", new DocService());
        return sb.build();
    }
}
