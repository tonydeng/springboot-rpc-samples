package com.github.tonydeng.demo.rpc.armeria.facade;

import java.util.List;

public interface BookService {

    Book getBook(String ISBN);

    List<Book> createBooks(List<Book> books);
}
