package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService.Iface;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class BookServiceProducer implements Iface {
	@Override
	public List<Book> createBooks(List<Book> books) throws TException {
		return new ArrayList<>();
	}
}
