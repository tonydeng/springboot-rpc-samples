package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.Note;
import com.github.tonydeng.demo.rpc.thrift.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/thrift")
@Component
public class NoteResources {
    @Resource
    private NoteService.Iface noteService;

    @Path("notes/hello/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@PathParam("name") String name) throws TException{
        return String.format("Hello %s!", StringUtils.capitalize(name));
    }

    @Path("notes/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNote(@PathParam("title") String title) throws TException{
        return noteService.getNote(title);
    }
}
