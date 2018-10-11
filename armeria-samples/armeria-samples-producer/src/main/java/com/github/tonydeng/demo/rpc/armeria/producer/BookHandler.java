package com.github.tonydeng.demo.rpc.armeria.producer;

import com.alibaba.fastjson.JSON;
import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.RequestObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component("BookHandler")
public class BookHandler {

    @Resource
    private BookService.Iface bookService;

    @Get("/book")
    public HttpResponse getBook(@Param("ISBN") String ISBN) throws TException {
        log.info("get book ISBN {}", ISBN);
        return HttpResponse.of(JSON.toJSONString(bookService.getBook(ISBN)));
    }

    @Post("/books")
    public HttpResponse createBooks(@RequestObject List<Book> books) throws TException {
        log.info("create book books {}", books);
        return HttpResponse.of(JSON.toJSONString(bookService.createBooks(books)));
    }
}
