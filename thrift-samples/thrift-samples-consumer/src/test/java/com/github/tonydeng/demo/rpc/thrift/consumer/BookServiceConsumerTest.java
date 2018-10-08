package com.github.tonydeng.demo.rpc.thrift.consumer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import com.github.tonydeng.demo.rpc.utils.NetworkUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 对Consumer的测试需要启动Producer
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceConsumerTest {

    @Resource
    private BookService.Iface bookService;

    private static final int PORT = 9000;

//    @Disabled
    @Test
    void booksIsEmpytTest() throws TException {
        if(!NetworkUtils.isPortAvailable(PORT)){
            List<Book> books = bookService.createBooks(Lists.newArrayList());
            assertNotNull(books);
            assertEquals(0, books.size());
        }
    }

//    @Disabled
    @RepeatedTest(value = 1000, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("testBooksIsNotEmpty")
    void testBooksIsNotEmpty() throws TException {
        List<Book> books = Lists.newArrayList(
                new Book().setAuthor("B")
                        .setKeyword(Lists.newArrayList("ahah", "adfasdf"))
                        .setPage(1)
        );
        if (!NetworkUtils.isPortAvailable(PORT)) {
            assertNotNull(bookService.createBooks(books));
            assertEquals(1, bookService.createBooks(books).size());
            assertEquals(books.get(0).getTitle(), bookService.createBooks(books).get(0).getTitle());
            assertNotEquals(books.get(0).getISBN(), bookService.createBooks(books).get(0).getISBN());
        }
    }

//    @Disabled
    @RepeatedTest(value = 1000, name = "testGetBook {currentRepetition} / {totalRepetitions}")
    void testGetBook() throws TException {
        if (!NetworkUtils.isPortAvailable(PORT)) {
            Book book = bookService.getBook("1234");
            assertEquals("1234", book.getISBN());
        }
    }
}


