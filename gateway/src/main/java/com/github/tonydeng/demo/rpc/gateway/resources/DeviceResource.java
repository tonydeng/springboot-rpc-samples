package com.github.tonydeng.demo.rpc.gateway.resources;

import com.github.tonydeng.demo.rpc.thrift.Device;
import com.github.tonydeng.demo.rpc.thrift.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/devices")
@Component
public class DeviceResource {

    @Resource
    private DeviceService.Iface deviceService;

    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Device getDevice() throws TException {
        return deviceService.getDevice();
    }


}
