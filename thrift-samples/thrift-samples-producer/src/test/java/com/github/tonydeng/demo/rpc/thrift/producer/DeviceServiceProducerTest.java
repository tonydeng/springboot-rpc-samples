package com.github.tonydeng.demo.rpc.thrift.producer;

import com.github.tonydeng.demo.rpc.thrift.Device;
import com.github.tonydeng.demo.rpc.thrift.DeviceService;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeviceServiceProducerTest {
    @Resource
    private DeviceService.Iface deviceService;

    @Test
    void testGetDevice() throws TException {
        Device device = deviceService.getDevice(123);

        assertNotNull(device);
    }

    @Test
    void devicesIsEmpytTest() throws TException {
        try {
            deviceService.getDevice(0);
        } catch (TException e) {
            assertEquals("设备ID不能为空",
                    e.getMessage());
        }
    }
}
