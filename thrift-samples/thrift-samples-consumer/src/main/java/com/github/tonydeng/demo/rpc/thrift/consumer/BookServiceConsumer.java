package com.github.tonydeng.demo.rpc.thrift.consumer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@SpringBootConfiguration
@Service("BookService")
public class BookServiceConsumer implements BookService.Iface {

    @Override
    public Book getBook(String isbn) throws TException {
        try (TTransport transport = new TSocket("localhost", 9000, 500)) {
            TProtocol protocol = new TBinaryProtocol(transport);

            BookService.Client client = new BookService.Client(protocol);

            transport.open();

            return client.getBook(isbn);
        }
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {

        try (TTransport transport = new TSocket("localhost", 9000, 500)) {
            TProtocol protocol = new TBinaryProtocol(transport);

            BookService.Client client = new BookService.Client(protocol);

            transport.open();

            return client.createBooks(books);
        }
    }

}
