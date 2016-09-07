package snmp.service;

import org.testng.annotations.Test;
import snmp.DeviceInfoCollector;


import java.io.IOException;

/**
 * Created by root on 8/18/15.
 */
public class ServiceTest {

    @Test(enabled = false)
    public void createNodes() throws IOException, IllegalAccessException {
        SystemService systemService = new SystemService();
        DeviceInfoCollector deviceInfoCollector = new DeviceInfoCollector("192.168.1.69" , "1161" , "public" , 2);
        systemService.createOrUpdate(deviceInfoCollector.setValuesToSystem("Quisk Switch"));



    }

}