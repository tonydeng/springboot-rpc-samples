package com.github.tonydeng.demo.grpc.producer.utils;

import com.github.tonydeng.demo.grpc.facade.Book;
import com.github.tonydeng.demo.grpc.facade.BookType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class BookUtilsTest {

	private static List<Book> books;

	@BeforeAll
	public static void setUp() {
		books = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			books.add(Book.newBuilder()
					.setAuthor("a" + i)
					.setBookType(BookType.forNumber(i % 3))
					.setTitle("t" + i)
					.setPage(i)
					.build());
		}
	}

	@Test
	void testAssignIsbn() {
		List<Book> bs = BookUtils.assignIsbn(books);
		assertEquals(10, bs.size());
		assertEquals("t9", bs.get(bs.size() - 1).getTitle());
		assertEquals("t0", bs.get(0).getTitle());
	}

	@RepeatedTest(1000)
	@DisplayName("generateIsbn 1000")
	@Test
	void testGenerateIsbn() {
		assertNotNull(BookUtils.generateIsbn(), BookUtils.generateIsbn());
	}
}
