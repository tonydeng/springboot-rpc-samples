package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import com.github.tonydeng.demo.rpc.thrift.Book;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ThriftResourcesTest extends BaseTest {

    @Resource
    private ThriftResources thriftResources;

    @Test
    void testGetBook() throws TException {
        assertNotNull(thriftResources.getBook(""));
        assertEquals(new Book()
                .setTitle("Maven & Git")
                .setAuthor("Tony")
                .setKeyword(Lists.newArrayList(
                        "Git", "Maven"
                ))
                .setPage(100)
                .setISBN("123"),
                thriftResources.getBook("123"));
    }
}
