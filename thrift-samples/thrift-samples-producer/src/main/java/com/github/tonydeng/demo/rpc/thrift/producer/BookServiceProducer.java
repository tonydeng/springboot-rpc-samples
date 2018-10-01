package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService.Iface;
import com.github.tonydeng.demo.rpc.utils.BookUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BookService")
public class BookServiceProducer implements Iface {
    @Override
    public Book getBook(String isbn) throws TException {
        if (Strings.isNullOrEmpty(isbn)) {
            isbn = BookUtils.generateIsbn();
        }
        return new Book()
                .setTitle("Maven & Git")
                .setAuthor("Tony")
                .setKeyword(Lists.newArrayList(
                        "Git", "Maven"
                ))
                .setPage(100)
                .setISBN(isbn);
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return books.stream().map(
                book -> book.setISBN(BookUtils.generateIsbn())
        ).collect(Collectors.toList());
    }
}
