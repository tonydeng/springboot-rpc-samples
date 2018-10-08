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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceProduecrTest {

    @Resource
    private BookServiceGrpc.BookServiceImplBase grpcBookService;

    private static BookList bookList;

    private static StreamObserver<BookList> response;
    private static final CountDownLatch finishLatch = new CountDownLatch(1);

    @BeforeAll
    public static void setUp() {
        bookList = BookList.newBuilder().addAllBook(Lists.newArrayList(
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
        )).build();

        response = new StreamObserver<BookList>() {
            @Override
            public void onNext(BookList bookList) {
                log.info("BookServiceProducer服务端返回:'{}'", bookList);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("BookServiceProducer Failed!");
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                log.info("BookServiceProducer finish!");
                finishLatch.countDown();
            }
        };
    }

    @Test
    void testSpringBoot() {
        assertNotNull(grpcBookService);
    }

    @Test
    void createBooksTest() throws InterruptedException {
        grpcBookService.createBooks(bookList, response);
        assertNotNull(bookList);
        assertNotNull(response);
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            log.info("bookService can not finish within 1 minutes");
        }
        assertNotNull(bookList.getBookList());
        bookList.getBookList().forEach(b -> assertNotNull(b.getISBN()));
    }
}
