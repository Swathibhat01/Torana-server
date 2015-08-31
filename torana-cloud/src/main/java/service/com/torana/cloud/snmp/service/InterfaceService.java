package com.torana.cloud.snmp.service;
import com.torana.cloud.snmp.domain.Interface;

/**
 * Created by swathi on 8/6/2015.
 */
public class InterfaceService  extends  GenericService<Interface>{
    @Override
    public Class<Interface> getEntityType() {
        return Interface.class;
    }
}
