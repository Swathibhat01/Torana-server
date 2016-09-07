package snmp.service;




/**
 * Created by swathi on 8/6/2015.
 */
public class SystemService extends GenericService<snmp.domain.System> {
    @Override
    public Class<snmp.domain.System> getEntityType()
    {
        return snmp.domain.System.class;
    }
}
