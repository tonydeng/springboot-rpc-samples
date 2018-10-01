package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.github.tonydeng.demo.rpc.thrift.BookService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Path("/book")
@Component
public class BookResources {
    @Resource
    private BookService.Iface bookService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> createBooks() {
        return Lists.newArrayList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook() throws TException {
        return bookService.getBook("");
    }
}
