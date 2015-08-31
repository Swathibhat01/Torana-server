package com.torana.cloud.snmp.service;
import com.torana.cloud.snmp.domain.System;

/**
 * Created by swathi on 8/6/2015.
 */
public class SystemService extends GenericService<com.torana.cloud.snmp.domain.System> {
    @Override
    public Class<System> getEntityType()
    {
        return System.class;
    }
}
