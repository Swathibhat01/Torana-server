package com.torana.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by root on 8/28/15.
 */
public class SnmpCallsJobs extends QuartzJobBean {

    public SnmpCallsJobs() {
    }

    public SnmpCallsJobs(String ipAddress, String snmpPort, String snmpCommunity, int snmpVersion, String name) {
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       /* String ipAddress = (String)jobExecutionContext.getJobDetail().getJobDataMap().get("ipAddress");
        String snmpPort = (String)jobExecutionContext.getJobDetail().getJobDataMap().get("snmpPort");
        String snmpCommunity = (String)jobExecutionContext.getJobDetail().getJobDataMap().get("snmpCommunity");
        int snmpVersion = (Integer)jobExecutionContext.getJobDetail().getJobDataMap().get("snmpVersion");
        String name = (String)jobExecutionContext.getJobDetail().getJobDataMap().get("name");

        */
    }
}
