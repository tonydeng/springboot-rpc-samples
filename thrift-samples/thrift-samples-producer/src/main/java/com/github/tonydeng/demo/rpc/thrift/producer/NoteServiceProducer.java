package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Note;
import com.github.tonydeng.demo.rpc.thrift.NoteService;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("NoteService")
public class NoteServiceProducer implements NoteService.Iface {

    @Override
    public Note getNote(String isbn) throws TException {
        return new Note()
                .setTitle("Thrift Demo")
                .setAuthor("afra")
                .setTag(Lists.newArrayList(
                        "thrift"
                ));
    }

    @Override
    public List<Note> createNotes(List<Note> notes) throws TException {
        if (CollectionUtils.isEmpty(notes)) {
            return Lists.newArrayList();
        }
        return notes.stream().map(
                note -> note.setTitle("Demo")
        ).collect(Collectors.toList());
    }
}
