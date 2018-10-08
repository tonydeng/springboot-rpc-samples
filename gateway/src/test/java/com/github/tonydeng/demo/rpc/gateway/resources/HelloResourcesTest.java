package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class HelloResourcesTest extends BaseTest {
    @Resource
    private HelloResources helloResources;

    @Test
    void testHello() {
        assertEquals("Hello World!", helloResources.sayHello());
        assertNotNull(helloResources.sayHello("tony"));
        assertEquals("Hello Tony!",helloResources.sayHello("tony"));
    }
}
