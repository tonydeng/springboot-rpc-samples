package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import com.github.tonydeng.demo.rpc.utils.BookUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("BookAsyncService")
public class BookServiceAsyncProducer implements BookService.AsyncIface {
    @Override
    public void getBook(String isbn, AsyncMethodCallback<Book> resultHandler) throws TException {
        if (Strings.isNullOrEmpty(isbn)) {
            isbn = BookUtils.generateIsbn();
        }
        Book book = new Book()
                .setTitle("Maven & Git")
                .setAuthor("Tony")
                .setKeyword(Lists.newArrayList(
                        "Git", "Maven"
                ))
                .setPage(100)
                .setISBN(isbn);
        resultHandler.onComplete(book);
    }

    @Override
    public void createBooks(List<Book> books, AsyncMethodCallback<List<Book>> resultHandler) throws TException {
        if (CollectionUtils.isEmpty(books)) {
            books=  Lists.newArrayList();
        }
        resultHandler.onComplete(books.stream().map(
                book -> book.setISBN(BookUtils.generateIsbn())
        ).collect(Collectors.toList()));
    }
}
