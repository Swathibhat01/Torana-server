package snmp.service;


import snmp.domain.Interface;

/**
 * Created by swathi on 8/6/2015.
 */
public class InterfaceService  extends GenericService<snmp.domain.Interface> {
    @Override
    public Class<snmp.domain.Interface> getEntityType() {
        return snmp.domain.Interface.class;
    }
}
