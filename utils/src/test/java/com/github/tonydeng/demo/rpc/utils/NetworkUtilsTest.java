package com.github.tonydeng.demo.rpc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkUtilsTest {

    @Test
    void testIsPortAvailable() {

        assertTrue(NetworkUtils.isPortAvailable(9000));
        assertFalse(NetworkUtils.isPortAvailable(22));
    }

    @Test
    void testIsPortUnvailable() {
        assertFalse(NetworkUtils.isPortUnavailable(9000));
        assertTrue(NetworkUtils.isPortUnavailable(22));
    }
}
