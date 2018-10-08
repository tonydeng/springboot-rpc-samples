package com.github.tonydeng.demo.rpc.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FastJsonResolverTest {

    private static FastJsonResolver resolver;

    @BeforeAll
    static void setUp() {
        resolver = new FastJsonResolver();
    }

    @Test
    void testGetContext() {
        assertEquals("yyyy-MM-dd HH:mm:ss", resolver.getContext(Date.class).getDateFormat());
    }
}
