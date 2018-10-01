package com.github.tonydeng.demo.rpc.thrift.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tonydeng.demo.rpc.thrift.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class BookTest {
    @CsvSource({"foo, 1", "bar, 2", "'baz, qux', 3"})
    @ParameterizedTest
    public void writeToJson(String title, int page) {
        log.info("{}, {}", title, page);

        Book book = new Book().setTitle(title).setPage(page);

        assertEquals(title, book.getTitle());
        assertEquals(page, book.getPage());

        JSONObject jo = JSON.parseObject(JSON.toJSONString(book));

        assertEquals(book.getTitle(),jo.getString("title"));
        assertEquals(book.getPage(),jo.getIntValue("page"));
        assertNull(book.getISBN());
        assertNull(jo.get("ISBN"));
    }

}
