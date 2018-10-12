package com.github.tonydeng.demo.rpc.armeria.consumer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.linecorp.armeria.client.ClientBuilder;
import com.linecorp.armeria.client.metric.MetricCollectingClient;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.metric.MeterIdPrefixFunction;
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


    static final BookService.Iface bookService = new ClientBuilder(BOOK_URI)
            .decorator(HttpRequest.class, HttpResponse.class,
                    MetricCollectingClient.newDecorator(
                            MeterIdPrefixFunction.ofDefault("/book-metric")
                    )).build(BookService.Iface.class);

    @Override
    public Book getBook(String isbn) throws TException {
        return bookService.getBook(isbn);
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return bookService.createBooks(books);
    }

}
