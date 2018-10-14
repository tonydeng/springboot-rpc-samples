package com.github.tonydeng.demo.rpc.utils;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
public class NetworkUtils {

    /**
     * 端口是否可用
     *
     * @param port
     * @return
     */
    public static boolean isPortAvailable(int port) {
        checkArgument((port >= 0 && port <= 65535), "port: %s (expected: 0-65535)", port);
        try {
            bindPort("0.0.0.0", port);
            bindPort(InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 端口是否被占用
     *
     * @param port
     * @return
     */
    public static boolean isPortUnavailable(int port) {
        return !isPortAvailable(port);
    }

    private NetworkUtils() {
    }

    private static void bindPort(String host, int port) throws IOException {
        try (Socket socket = new Socket()) {
            log.debug("bind {}:{}", host, port);
            socket.bind(new InetSocketAddress(host, port));
        }
    }
}
