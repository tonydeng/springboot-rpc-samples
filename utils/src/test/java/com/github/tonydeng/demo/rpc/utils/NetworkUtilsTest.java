package com.github.tonydeng.demo.rpc.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkUtilsTest {

    @ParameterizedTest
    @ValueSource(ints = {8080, 9000, 9090, 9001, 65535})
    void testIsPortAvailable(int port) {
        assertTrue(NetworkUtils.isPortAvailable(port));
    }

    @ParameterizedTest
    @ValueSource(ints = {22})
    void testIsPortUnvailable(int port) {
        assertTrue(NetworkUtils.isPortUnavailable(port));
    }

    @ParameterizedTest
    @ValueSource(ints = {65536, 65537})
    void testPortExpected(int port) {
        assertThrows(IllegalArgumentException.class, () -> NetworkUtils.isPortAvailable(port));
    }
}
