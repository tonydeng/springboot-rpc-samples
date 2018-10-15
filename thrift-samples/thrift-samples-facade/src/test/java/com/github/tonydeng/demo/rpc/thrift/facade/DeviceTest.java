package com.github.tonydeng.demo.rpc.thrift.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tonydeng.demo.rpc.thrift.Device;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DeviceTest {
    @CsvSource({"华为, huawei, switch, 4, 1", "锐捷, ruijie, switch, 3, 2", "山石, hillstone, firewall, 5, 3"})
    @ParameterizedTest
    public void writeToJson(String hostName,String firm, String type, int id, int page) {
        log.info("{}, {}, {}", firm, id, page);

        Device device = new Device().setHostname(hostName).setType(type).setFirm(firm).setId(id).setPage(page);

        assertEquals(hostName, device.getHostname());
        assertEquals(type, device.getType());
        assertEquals(firm, device.getFirm());
        assertEquals(id, device.getId());
        assertEquals(page, device.getPage());

        JSONObject deviceJson = JSON.parseObject(JSON.toJSONString(device));

        log.info("{}",deviceJson);
        assertEquals(device.getHostname(),deviceJson.getString("hostname"));
        assertEquals(device.getPage(),deviceJson.getIntValue("page"));
        assertEquals(deviceJson.get("type"), device.getType());
    }
}
