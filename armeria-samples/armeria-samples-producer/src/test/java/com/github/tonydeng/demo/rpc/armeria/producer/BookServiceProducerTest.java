package com.github.tonydeng.demo.rpc.armeria.producer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.github.tonydeng.demo.rpc.utils.BookUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceProducerTest {
    @Resource
    private BookService bookService;

    @Test
    void testGetBook() {
        assertNotNull(bookService);
        assertNotNull(bookService.getBook(null));
        assertEquals(Arrays.asList("RPC", "Armeria"), bookService.getBook(null).getKeys());
    }

    @Test
    void testCreateBooks() {
        assertNotNull(bookService.createBooks(null));
        List<Book> books = Lists.newArrayList(
                bookService.getBook(BookUtils.generateIsbn()),
                bookService.getBook(BookUtils.generateIsbn())
        );

        assertSame(books, bookService.createBooks(books));
        assertEquals(books, bookService.createBooks(Lists.newArrayList(books)));

        assertNotSame(books, bookService.createBooks(new ArrayList<>(books)));

        assertNotEquals(books, bookService.createBooks(null));
        assertNotEquals(books, bookService.createBooks(Lists.newArrayList(
                bookService.getBook(BookUtils.generateIsbn()),
                bookService.getBook(BookUtils.generateIsbn())
        )));
    }
}
