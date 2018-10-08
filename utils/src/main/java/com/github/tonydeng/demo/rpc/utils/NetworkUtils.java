package com.github.tonydeng.demo.rpc.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class NetworkUtils {

    private NetworkUtils() {
    }

    private static void bindPort(String host, int port) throws IOException {
        try (Socket socket = new Socket()) {
            log.debug("bind {}:{}", host, port);
            socket.bind(new InetSocketAddress(host, port));
        }
    }

    public static boolean isPortAvailable(int port) {
        try {
            bindPort("0.0.0.0", port);
            bindPort(InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
