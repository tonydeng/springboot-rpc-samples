package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.gateway.BaseTest;
import com.github.tonydeng.demo.rpc.thrift.Note;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.thrift.TException;

import javax.annotation.Resource;

@Slf4j
public class NoteResourcesTest extends BaseTest {

    private static Note note;

    @Resource
    NoteResources noteResources;

    @BeforeAll
    static void setUp() {
        note = new Note()
                .setTitle("testTitle")
                .setAuthor("afra")
                .setTag(Lists.newArrayList("thrift"))
                .setContent("testContent");

    }

    @Test
    void testGetNote() throws TException {
        Assertions.assertEquals(note, noteResources.getNote("testTitle"));
    }

    @Test
    void testCreateNote() throws TException {
        Assertions.assertEquals(note, noteResources.createNote("testContent"));
    }


}
