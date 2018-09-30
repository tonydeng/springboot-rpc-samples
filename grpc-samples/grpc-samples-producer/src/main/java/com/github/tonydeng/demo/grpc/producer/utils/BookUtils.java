package com.github.tonydeng.demo.grpc.producer.utils;

import com.github.tonydeng.demo.grpc.facade.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookUtils {
	private BookUtils() {
	}

	public static List<Book> assignIsbn(List<Book> books) {
		return books.stream().map(
				book -> Book.newBuilder(book).setISBN(generateIsbn()).build()
		).collect(Collectors.toList());
	}

	public static String generateIsbn() {
		return UUID.randomUUID().toString().replaceAll("/", "")
				.substring(0, 12);
	}
}
