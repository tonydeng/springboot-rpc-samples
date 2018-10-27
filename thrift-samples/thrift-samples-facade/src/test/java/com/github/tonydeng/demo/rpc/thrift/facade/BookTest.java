package com.github.tonydeng.demo.rpc.thrift.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tonydeng.demo.rpc.thrift.parent.BookType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class BookTest {
    @CsvSource({"foo, 1, 0", "bar, 2, 1", "'baz, qux', 3, 2"})
    @ParameterizedTest
    public void writeToJson(String title, int page, int type) {
        log.info("{}, {}, {}", title, page, type);

        Book book = new Book().setTitle(title).setPage(page).setType(BookType.findByValue(type));

        assertEquals(title, book.getTitle());
        assertEquals(page, book.getPage());
        assertEquals(BookType.findByValue(type), book.getType());

        JSONObject jo = JSON.parseObject(JSON.toJSONString(book));

        assertEquals(book.getTitle(), jo.getString("title"));
        assertEquals(book.getPage(), jo.getIntValue("page"));
        assertEquals(book.getType().name(), jo.getString("type"));
        assertNull(book.getISBN());
        assertNull(jo.get("ISBN"));
    }

}
