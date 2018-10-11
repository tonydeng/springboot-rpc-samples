package com.github.tonydeng.demo.rpc.armeria.producer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class BookHandlerTest extends BaseTest {
    @Resource
    private BookHandler bookHandler;

    @Test
    void testGetBook() throws TException {
        assertNotNull(bookHandler);
        assertNotNull(bookHandler.getBook(null));
    }

    @Test
    void testCreateBooks() throws TException {
        assertNotNull(bookHandler.createBooks(Lists.newArrayList()));
    }
}
