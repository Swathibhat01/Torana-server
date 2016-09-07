import com.torana.cloud.biz.ToranaCloudElementsService;
import com.torana.cloud.resources.ToranaCloudElementsResource;
import com.torana.cloudelements.CloudElements;
import com.torana.util.ApplicationContextProvider;
import snmp.DeviceInfoCollector;
import snmp.service.SystemService;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.torana.cloud.util.CloudConstants.CLOUD_ELEMENTS_SERVICE;
import static com.torana.cloud.util.CloudConstants.CLOUD_SERVICE;

/**
 * Created by root on 2/21/16.
 */
public class snmpSchedulerJob   {
    private static final Logger LOGGER = LoggerFactory.getLogger(snmpSchedulerJob.class);

    DeviceInfoCollector deviceInfoCollector;


    ToranaCloudElementsResource toranaCloudElementsResource;

    public void snmpSwitchInfoCollectingAndPersistingToNeo4j() throws Exception {
        LOGGER.info("Talking to SNMP switch and updating the neo4j switch data");
        ToranaCloudElementsService  cloudElementsService = (ToranaCloudElementsService) ApplicationContextProvider.getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);

        ToranaCloudElementsService toranaCloudElementsService = (ToranaCloudElementsService) ApplicationContextProvider.getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
        List<CloudElements> retrivedCloudElements = toranaCloudElementsService.getAllCloudElementsService();
        for(CloudElements cloudElements : retrivedCloudElements){
            if(cloudElements.getType().equals("SNMP-Switch")){
                deviceInfoCollector = new DeviceInfoCollector(cloudElements.getIpAddress(), cloudElements.getSnmpPort(), cloudElements.getSnmpCommunity(), cloudElements.getSnmpVersion());
                try {
                    new SystemService().createOrUpdate(deviceInfoCollector.setValuesToSystem(cloudElements.getName()));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}