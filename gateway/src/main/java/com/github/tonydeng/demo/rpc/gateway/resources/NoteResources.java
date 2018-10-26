package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.facade.Note;
import com.github.tonydeng.demo.rpc.thrift.facade.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/note")
@Component
public class NoteResources {
    @Resource
    private NoteService.Iface noteService;

    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@PathParam("name") String name) throws TException{
        return String.format("Hello %s!", StringUtils.capitalize(name));
    }

    @Path("/gets/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNote(@PathParam("title") String title) throws TException{
        return noteService.getNote(title);
    }

    @Path("/create/{content}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Note createNote(@PathParam("content") String content) throws TException{
        return noteService.createNote(content);
    }
}
