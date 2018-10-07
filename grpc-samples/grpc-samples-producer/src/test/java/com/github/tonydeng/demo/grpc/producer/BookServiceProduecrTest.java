package com.github.tonydeng.demo.grpc.producer;

import com.github.tonydeng.demo.rpc.proto.Book;
import com.github.tonydeng.demo.rpc.proto.BookList;
import com.github.tonydeng.demo.rpc.proto.BookServiceGrpc;
import com.github.tonydeng.demo.rpc.proto.BookType;
import com.google.common.collect.Lists;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceProduecrTest {

    @Resource
    private BookServiceGrpc.BookServiceImplBase grpcBookService;

    private static BookList bookList;

    private static StreamObserver<BookList> response;

    @BeforeAll
    public static void setUp() {
        bookList = BookList.getDefaultInstance();

        bookList.toBuilder().addAllBook(Lists.newArrayList(
                Book.newBuilder()
                        .setAuthor("A1")
                        .setPage(1)
                        .setBookType(BookType.BOOK)
                        .setTitle("T1")
                        .addAllKeyword(Arrays.asList("k", "e", "y"))
                        .build(),
                Book.newBuilder()
                        .setAuthor("A2")
                        .setPage(2)
                        .setBookType(BookType.BOOK)
                        .setTitle("T2")
                        .addAllKeyword(Arrays.asList("k", "e", "y"))
                        .build()
        ));

    }

    @Test
    void testSpringBoot() {
        assertNotNull(grpcBookService);
    }

    @Test
    void createBooksTest() {
        grpcBookService.createBooks(bookList, response);
        assertNotNull(bookList);
        assertNotNull(response);
    }
}
