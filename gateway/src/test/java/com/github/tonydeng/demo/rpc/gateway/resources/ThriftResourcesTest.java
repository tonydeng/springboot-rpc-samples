package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import com.github.tonydeng.demo.rpc.thrift.facade.Book;
import com.github.tonydeng.demo.rpc.thrift.parent.BookType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ThriftResourcesTest extends BaseTest {

    private static Book book;

    @Resource
    private ThriftResources thriftResources;

    @BeforeAll
    static void setUp() {
        book = new Book()
                .setTitle("Maven & Git")
                .setAuthor("Tony")
                .setKeyword(Lists.newArrayList(
                        "Git", "Maven"
                ))
                .setPage(100)
                .setISBN("123")
                .setType(BookType.BOOK);
    }

    @Test
    void testGetBook() throws TException {
        assertNotNull(thriftResources.getBook(""));
        assertEquals(book,
                thriftResources.getBook("123"));
    }

    @Test
    void testCreateEmptyBooks() throws TException {
        assertNotNull(thriftResources.createBooks(Lists.newArrayList()));
        assertNotNull(thriftResources.createBooks(null));
    }

    @Test
    void testCreateNotEmptyBooks() throws TException {
        List<Book> books = Lists.newArrayList(book);

        List<Book> result = thriftResources.createBooks(books);

        assertEquals(books, result);
        result.forEach(b -> assertNotNull(b.getISBN()));
    }
}
