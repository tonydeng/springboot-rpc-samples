package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService.Iface;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootConfiguration
@Service("BookService")
public class BookServiceProducer implements Iface {
    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return Lists.newArrayList();
    }
}
