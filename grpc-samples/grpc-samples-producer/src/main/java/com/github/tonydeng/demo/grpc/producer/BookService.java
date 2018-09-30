package com.github.tonydeng.demo.grpc.producer;

import com.github.tonydeng.demo.grpc.facade.BookList;
import com.github.tonydeng.demo.grpc.facade.BookServiceGrpc.BookServiceImplBase;
import com.github.tonydeng.demo.grpc.producer.utils.BookUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookService extends BookServiceImplBase {

	@Override
	public void createBooks(BookList request, StreamObserver<BookList> responseObserver) {
		log.info("request: '{}'", request);

		BookList.Builder responseBuilder = BookList.newBuilder();

		BookUtils.assignIsbn(request.getBookList()).forEach(responseBuilder::addBook);

		BookList bookListResponse = responseBuilder.build();

		responseObserver.onNext(bookListResponse);
		responseObserver.onCompleted();
	}
}
