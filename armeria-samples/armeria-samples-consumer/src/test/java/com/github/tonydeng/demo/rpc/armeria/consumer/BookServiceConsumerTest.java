package com.github.tonydeng.demo.rpc.armeria.consumer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.github.tonydeng.demo.rpc.utils.NetworkUtils;
import com.google.common.collect.Lists;
import com.linecorp.armeria.client.Clients;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookServiceConsumerTest {

    @Resource
    private BookService.Iface bookService;

    @Test
    void testClientBuilder() throws TException {
        if (NetworkUtils.isPortAvailable(9000)) {
            BookService.Iface bookService = Clients.newClient("tbinary+http://127.0.0.1:9000/book", BookService.Iface.class);
            Book book = bookService.getBook("ttt");
            assertNotNull(book);
        }
    }

    @Test
    void testGetBook() throws TException {
        bookService.getBook("aaa");
    }

    @Test
    void testCreateBooks() throws TException {
        bookService.createBooks(Lists.newArrayList());
    }
}
