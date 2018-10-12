package com.github.tonydeng.demo.rpc.armeria.consumer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.linecorp.armeria.client.Clients;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.linecorp.armeria.common.thrift.ThriftSerializationFormats.COMPACT;

@Slf4j
@Service("BookService")
@SpringBootConfiguration
public class BookServiceConsumer implements BookService.Iface {
    private static final String BOOK_URI = COMPACT.uriText()
            + "+http://127.0.0.1:9000/book";


    private static final BookService.Iface bookService =
            Clients.newClient(BOOK_URI, BookService.Iface.class);

    @Override
    public Book getBook(String isbn) throws TException {
        return bookService.getBook(isbn);
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return bookService.createBooks(books);
    }

}
