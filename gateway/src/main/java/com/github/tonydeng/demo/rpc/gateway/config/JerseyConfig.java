package com.github.tonydeng.demo.rpc.gateway.config;

import com.alibaba.fastjson.support.jaxrs.FastJsonAutoDiscoverable;
import com.github.tonydeng.demo.rpc.gateway.scanner.PackageAnnotationScanner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

@Slf4j
@Component
@ApplicationPath(JerseyConfig.APPLICAION_PREIFX)
public class JerseyConfig extends ResourceConfig {
    static final String APPLICAION_PREIFX = "api/v1";
    static final String[] RESOURCE_PACKAGE_NAME = {
            "com.github.tonydeng.demo.rpc.gateway.resources",
            "com.github.tonydeng.demo.rpc.gateway.filter",
            "com.github.tonydeng.demo.rpc.gateway.config"
    };

    public JerseyConfig() {
        log.info("Jersey Config Init.......");
        register(MultiPartFeature.class);
        FastJsonAutoDiscoverable.autoDiscover = false;
        scan(RESOURCE_PACKAGE_NAME);
    }

    private void scan(String... packages) {
        registerClasses(
                PackageAnnotationScanner.scan(
                        this.getClassLoader(),
                        Lists.newArrayList(packages),
                        Lists.newArrayList(Path.class, Provider.class)
                )
        );
    }
}
