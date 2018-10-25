package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Note;
import com.github.tonydeng.demo.rpc.thrift.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@Slf4j
public class NoteServiceProducerTest extends BaseTest{
    @Resource
    NoteService.Iface noteService;

    @Test
    void testGetNote() throws TException {
        Note note = noteService.getNote("testTitle");
        Assertions.assertNotNull(note);
        Assertions.assertEquals("testTitle", note.getTitle());
    }

    @Test
    void testCreateNote() throws TException {
        Note note = noteService.createNote("testContent");
        Assertions.assertNotNull(note);
        Assertions.assertEquals("testContent", note.getDesc());
    }
}
