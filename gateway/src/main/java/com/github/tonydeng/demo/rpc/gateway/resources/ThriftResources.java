package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.facade.Book;
import com.github.tonydeng.demo.rpc.thrift.facade.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Path("/thrift")
@Component
public class ThriftResources {
    @Resource
    private BookService.Iface bookService;

    @Path("books")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> createBooks(List<Book> books) throws TException {
        return bookService.createBooks(books);
    }

    @Path("books/{isbn}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("isbn") String isbn) throws TException {
        return bookService.getBook(isbn);
    }
}
