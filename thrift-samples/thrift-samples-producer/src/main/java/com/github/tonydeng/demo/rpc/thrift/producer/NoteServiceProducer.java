package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Note;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import static com.github.tonydeng.demo.rpc.thrift.NoteService.Iface;

@Service("NoteService")
public class NoteServiceProducer implements Iface {

    @Override
    public Note getNote(String title) throws TException {
        return new Note()
                .setTitle(title)
                .setAuthor("afra")
                .setTag(Lists.newArrayList(
                        "thrift"
                ))
                .setDesc("testContent");
    }

    @Override
    public Note createNote(String content) throws TException {
        return new Note()
                .setTitle("testTitle")
                .setAuthor("afra")
                .setTag(Lists.newArrayList(
                        "thrift"
                ))
                .setDesc(content);
    }
}
