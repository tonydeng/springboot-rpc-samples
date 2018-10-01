package com.github.tonydeng.demo.rpc.gateway.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class FastJsonResolver implements ContextResolver<FastJsonConfig> {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Override
    public FastJsonConfig getContext(Class<?> type) {
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat(DATE_FORMAT);
        return config;
    }
}
