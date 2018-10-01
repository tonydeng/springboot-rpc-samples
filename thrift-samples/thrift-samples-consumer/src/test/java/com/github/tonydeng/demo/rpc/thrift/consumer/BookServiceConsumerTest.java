package com.github.tonydeng.demo.rpc.thrift.consumer;

import com.github.tonydeng.demo.rpc.thrift.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceConsumerTest {

    @Resource
    private BookService.Iface bookService;

    @Test
    public void test() {
        log.info("test");
    }
}
