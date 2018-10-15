package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Device;
import com.github.tonydeng.demo.rpc.thrift.DeviceService.Iface;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service("DeviceService")
public class DeviceServiceProducer implements Iface {
    @Override
    public Device getDevice() throws TException {
        return new Device()
                .setHostname("H3C switch")
                .setType("switch")
                .setFirm("h3c")
                .setKeyword(Lists.newArrayList(
                        "h3c", "cisco", "hillstone"
                ))
                .setPage(100);
    }

}
