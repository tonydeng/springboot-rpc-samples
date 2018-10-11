package com.github.tonydeng.demo.rpc.gateway.resources;


import com.github.tonydeng.demo.thrift.swift.User;
import com.github.tonydeng.demo.thrift.swift.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/swift")
@Component
public class UserResources {


    @Resource
    private UserService userService;

    @Path("user/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public User getUser(@PathParam("id") int id) throws TException {

        return userService.getUser(id);
   }
}
