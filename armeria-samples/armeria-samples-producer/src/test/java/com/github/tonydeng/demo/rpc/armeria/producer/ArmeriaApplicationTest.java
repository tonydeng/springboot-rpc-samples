package com.github.tonydeng.demo.rpc.armeria.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ArmeriaApplicationTest extends BaseTest {

    @Test
    void testMain() {
        ArmeriaApplication.main(new String[]{});
    }
}
