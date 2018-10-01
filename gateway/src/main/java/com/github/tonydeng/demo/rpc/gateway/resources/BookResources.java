package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.Book;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
//@Path("/book")
//@Component
public class BookResources {
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> createBooks() {
        return Lists.newArrayList();
    }
}
