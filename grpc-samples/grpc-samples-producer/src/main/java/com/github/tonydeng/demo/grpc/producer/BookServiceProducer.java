package com.github.tonydeng.demo.grpc.producer;

import com.github.tonydeng.demo.rpc.proto.Book;
import com.github.tonydeng.demo.rpc.proto.BookList;
import com.github.tonydeng.demo.rpc.proto.BookServiceGrpc;
import com.github.tonydeng.demo.rpc.utils.BookUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootConfiguration
@Service("GrpcBookService")
public class BookServiceProducer extends BookServiceGrpc.BookServiceImplBase {

    @Override
    public void createBooks(BookList request, StreamObserver<BookList> responseObserver) {
        log.info("request: '{}'", request);

        BookList.Builder responseBuilder = BookList.newBuilder();

        assignIsbn(request.getBookList()).forEach(responseBuilder::addBook);

        BookList bookListResponse = responseBuilder.build();

        responseObserver.onNext(bookListResponse);
        responseObserver.onCompleted();
    }

    private List<Book> assignIsbn(List<Book> books) {
        return books.stream().map(
                book -> Book.newBuilder(book).setISBN(BookUtils.generateIsbn()).build()
        ).collect(Collectors.toList());
    }
}
