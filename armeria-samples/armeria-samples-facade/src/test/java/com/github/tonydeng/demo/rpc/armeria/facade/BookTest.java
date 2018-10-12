package com.github.tonydeng.demo.rpc.armeria.facade;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class BookTest {
    private static Book book;

    @BeforeEach
    void befor() {
        book = null;
    }

    @Test
    void testCreateBook() {
        assertNull(book);
        book = new Book();
        assertNotNull(book);
        assertFalse(Objects.isNull(book));
        assertEquals(0, book.getPage());
    }

    @Test
    void testBuildBook() {
        book = new Book();
        book.setTitle("Title");
        assertNotNull(book);
        assertNotNull(book.getTitle());
        assertNull(book.getAuthor());
        assertEquals(new Book(null, "Title", null, 0, null), book);
    }
}
