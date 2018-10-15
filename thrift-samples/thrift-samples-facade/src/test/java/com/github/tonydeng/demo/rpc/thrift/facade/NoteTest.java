package com.github.tonydeng.demo.rpc.thrift.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tonydeng.demo.rpc.thrift.Note;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Slf4j
public class NoteTest {

    @CsvSource({"20181015, Saturday", "20181016, Tuesday", "20181017, Wednesday"})
    @ParameterizedTest
    public void writeToJson(String title, String content) {
        log.info("{}, {}", title, content);

        Note note = new Note().setTitle(title).setContent(content);

        Assertions.assertEquals(title, note.getTitle());
        Assertions.assertEquals(content, note.getContent());

        JSONObject jo = JSON.parseObject(JSON.toJSONString(note));

        Assertions.assertEquals(title, jo.getString("title"));
        Assertions.assertEquals(content, jo.getString("content"));
        Assertions.assertNull(note.getTag());
        Assertions.assertNull(jo.getString("tag"));
    }

}
