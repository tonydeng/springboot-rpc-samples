package com.github.tonydeng.demo.rpc.armeria.producer;

import com.github.tonydeng.demo.rpc.armeria.facade.Book;
import com.github.tonydeng.demo.rpc.armeria.facade.BookService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.tonydeng.demo.rpc.utils.BookUtils.generateIsbn;

@Service("BookService")
@SpringBootConfiguration
public class BookServiceProducer implements BookService {
    @Override
    public Book getBook(String ISBN) {
        if (Strings.isNullOrEmpty(ISBN)) {
            ISBN = generateIsbn();
        }
        return Book.builder()
                .title("Armeria Samples")
                .author("Tony")
                .ISBN(ISBN)
                .keys(Lists.newArrayList("RPC", "Armeria"))
                .page(100)
                .build();
    }

    @Override
    public List<Book> createBooks(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            books = Lists.newArrayList();
        }

        for (Book b : books) {
            b.setISBN(generateIsbn());
        }
        return books;
    }
}
