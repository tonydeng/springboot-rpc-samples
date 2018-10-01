package com.github.tonydeng.demo.rpc.utils;

import java.util.UUID;

public class BookUtils {
    private BookUtils() {
    }

    public static String generateIsbn() {
        return UUID.randomUUID().toString().replaceAll("/", "")
                .substring(0, 12);
    }
}
