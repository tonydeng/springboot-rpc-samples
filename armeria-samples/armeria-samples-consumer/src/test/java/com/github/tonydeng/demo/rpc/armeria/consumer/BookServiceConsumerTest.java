package com.github.tonydeng.demo.rpc.armeria.consumer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.github.tonydeng.demo.rpc.utils.NetworkUtils;
import com.google.common.collect.Lists;
import com.linecorp.armeria.client.Clients;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookServiceConsumerTest {

    @Resource
    private BookService.Iface bookService;

    @Test
    @Disabled
    void testClientBuilder() throws TException {
        if (NetworkUtils.isPortUnavailable(9000)) {
            BookService.Iface bookService = Clients.newClient("tbinary+http://127.0.0.1:9000/book", BookService.Iface.class);
            Book book = bookService.getBook("ttt");
            assertNotNull(book);
        }
    }

    @Test
    void testGetBook() throws TException {
        if (NetworkUtils.isPortUnavailable(9000)) {
            Book book = bookService.getBook("aaa");
            assertNotNull(book);
            assertEquals("aaa", book.getISBN());
        }
    }

    @Test
    void testCreateBooks() throws TException {
        if (NetworkUtils.isPortUnavailable(9000)) {
            List<Book> books = bookService.createBooks(Lists.newArrayList(
                    new Book().setTitle("a")
            ));

            assertNotNull(books);
            assertEquals(1, books.size());
            assertNotNull(books.get(0).getISBN());
        }
    }
}
