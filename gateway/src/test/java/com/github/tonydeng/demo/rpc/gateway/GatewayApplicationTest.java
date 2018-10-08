package com.github.tonydeng.demo.rpc.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class GatewayApplicationTest extends BaseTest {

    @Test
    public void testMain() {
        GatewayApplication.main(new String[]{});
    }
}
