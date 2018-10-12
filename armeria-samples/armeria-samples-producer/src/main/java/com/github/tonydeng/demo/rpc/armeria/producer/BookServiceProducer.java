package com.github.tonydeng.demo.rpc.armeria.producer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.github.tonydeng.demo.rpc.utils.BookUtils.generateIsbn;

@Service
public class BookServiceProducer implements BookService.Iface {
    @Override
    public Book getBook(String isbn) {
        if (Strings.isNullOrEmpty(isbn)) {
            isbn = generateIsbn();
        }

        return new Book(isbn, "Armeria Samples", "Tony", 100,
                Lists.newArrayList("RPC", "Armeria"));
    }

    @Override
    public List<Book> createBooks(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            books = Lists.newArrayList();
        }

        for (Book b : books) {
            b.setISBN(generateIsbn());
        }
        return books;
    }
}
