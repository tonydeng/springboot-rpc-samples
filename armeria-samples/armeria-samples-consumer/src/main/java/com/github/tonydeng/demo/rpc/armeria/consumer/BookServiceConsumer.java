package com.github.tonydeng.demo.rpc.armeria.consumer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.linecorp.armeria.client.Clients;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("BookService")
@SpringBootConfiguration
public class BookServiceConsumer implements BookService.Iface {
    private static final int PORT = 9000;
    private static final String PATH = "/book";
    private static final BookService.Iface bookService = Clients.newClient("tbinary+http://127.0.0.1:" + PORT + PATH, BookService.Iface.class);

    @Override
    public Book getBook(String ISBN) throws TException {
        return bookService.getBook(ISBN);
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return bookService.createBooks(books);
    }

}
