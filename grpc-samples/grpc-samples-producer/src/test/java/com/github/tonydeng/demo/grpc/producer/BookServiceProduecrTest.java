package com.github.tonydeng.demo.grpc.producer;

import com.github.tonydeng.demo.rpc.proto.BookServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceProduecrTest {

    @Resource
    private BookServiceGrpc.BookServiceImplBase grpcBookService;

    @Test
    void testSpringBoot() {
        assertNotNull(grpcBookService);
    }
}
