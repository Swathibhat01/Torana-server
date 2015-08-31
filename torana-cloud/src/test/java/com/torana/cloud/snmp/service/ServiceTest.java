package com.torana.cloud.snmp.service;

import com.torana.cloud.snmp.DeviceInfoCollector;
import com.torana.cloud.snmp.domain.System;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by root on 8/18/15.
 */
public class ServiceTest {

    @Test
    public void createNodes() throws IOException, IllegalAccessException {
        SystemService systemService = new SystemService();
        DeviceInfoCollector deviceInfoCollector = new DeviceInfoCollector("192.168.1.64" , "161" , "public" , 2);
        systemService.createOrUpdate(deviceInfoCollector.setValuesToSystem("Quisk Switch"));



    }

}