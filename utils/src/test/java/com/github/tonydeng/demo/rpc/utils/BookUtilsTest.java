package com.github.tonydeng.demo.rpc.utils;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class BookUtilsTest {

    @RepeatedTest(1000)
    @DisplayName("generateIsbn 1000")
    void testGenerateIsbn() {
        assertNotNull(BookUtils.generateIsbn(), BookUtils.generateIsbn());
    }
}
