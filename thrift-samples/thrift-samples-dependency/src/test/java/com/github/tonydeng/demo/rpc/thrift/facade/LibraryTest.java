package com.github.tonydeng.demo.rpc.thrift.facade;

import com.github.tonydeng.demo.rpc.thrift.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class LibraryTest {
    @CsvSource({"foo, 1", "bar, 2", "'baz, qux', 3"})
    @ParameterizedTest
    @Disabled
    void writeToJson(String title, int page) {
        log.info("{}, {}", title, page);

        //实现一个LibraryService.Iface
        LibraryService.Iface libraryService = new LibraryService.Iface() {
            public Book getBookByIsbn(java.lang.String isbn) throws org.apache.thrift.TException {
                return new Book().setTitle(title).setPage(page).setISBN(isbn);
            }

            public Book getBookByKeyword(java.lang.String keyword) throws org.apache.thrift.TException {
                return new Book().setTitle(title).setPage(page).setKeyword(Arrays.asList(keyword));
            }

            public List<Book> addBooks(java.util.List<Book> books) throws org.apache.thrift.TException {
                return books;
            }
        };

        try {
            Book book = libraryService.getBookByIsbn("a");
            assertEquals("a", book.getISBN());
            assertEquals(title, book.getTitle());
            assertEquals(page, book.getPage());

            book = libraryService.getBookByKeyword("key");
            assertEquals("key", book.getKeyword().get(0));
            assertEquals(title, book.getTitle());
            assertEquals(page, book.getPage());

            assertEquals(Arrays.asList(book), libraryService.addBooks(Arrays.asList(book)));

        } catch (TException te) {
        }
    }

}
