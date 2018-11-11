package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import com.github.tonydeng.demo.rpc.utils.BookUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class BookServiceAsyncProducerTest extends BaseTest {
    @Resource
    private BookService.AsyncIface bookAsyncService;

    @Test
    void testGetBook() throws TException {
        String isbn = BookUtils.generateIsbn();


        MethodCallback<Book> callback = new MethodCallback();
        bookAsyncService.getBook(isbn, callback);
        assertNotNull(callback.getResponse());
        assertEquals(isbn, callback.getResponse().getISBN());
    }

    @Test
    void testGetIsbnEmtypBook() throws TException {
        MethodCallback<Book> cb = new MethodCallback<>();
        bookAsyncService.getBook(null,cb);

        assertNull(cb.getResponse());

        assertEquals(IllegalArgumentException.class,cb.getException().getClass());
        assertEquals("isbn is empty",cb.getException().getMessage());
    }

    @Test
    void testCreateBooks() throws TException {
        List<Book> books = Lists.newArrayList(
                new Book().setAuthor("Tony")
                        .setKeyword(Lists.newArrayList("key", "t"))
                        .setPage(1)
                        .setISBN(BookUtils.generateIsbn())
        );

        MethodCallback<List<Book>> callback = new MethodCallback<>();
        bookAsyncService.createBooks(books, callback);

        assertNotNull(callback.getResponse());
        assertEquals(1, callback.getResponse().size());
        assertEquals(books.get(0).getTitle(), callback.getResponse().get(0).getTitle());
    }
}
