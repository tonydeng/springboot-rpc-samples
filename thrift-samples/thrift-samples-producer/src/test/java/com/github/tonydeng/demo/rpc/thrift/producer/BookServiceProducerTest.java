package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceProducerTest {
    @Resource
    private BookService.Iface bookService;

    @Test
    void testGetBook() throws TException {
        Book book = bookService.getBook(null);

        assertNotNull(book);
        assertNotNull(book.getISBN());

        book = bookService.getBook("1234");
        assertEquals("1234", book.getISBN());
    }

    @Test
    void booksIsEmpytTest() throws TException {
        List<Book> books = bookService.createBooks(Lists.newArrayList());
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void testBooksIsNotEmpty() throws TException {
        List<Book> books = Lists.newArrayList(
                new Book().setAuthor("A")
                        .setKeyword(Lists.newArrayList("key", "t"))
                        .setPage(1)
        );

        assertNotNull(bookService.createBooks(books));
        assertEquals(1, bookService.createBooks(books).size());
        assertEquals(books.get(0).getTitle(), bookService.createBooks(books).get(0).getTitle());
        assertNotEquals(books.get(0).getISBN(), bookService.createBooks(books).get(0).getISBN());
    }
}
