package com.torana.cloud.snmp;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by swathi on 7/14/2015.
 */
public class SnmpClientService {

    String address;
    String community;
    int snmpVersion;
    Snmp snmp;


    public SnmpClientService(String address ,  String community , int snmpVersion) {
        this.address = address;
        this.community = community;
        this.snmpVersion = snmpVersion;
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close() throws IOException {
        snmp.close();
    }

    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[]{oid});

        return event.getResponse().get(0).getVariable().toString();
    }

    private ResponseEvent get(OID[] oids) throws IOException {
        ResponseEvent event = snmp.send(getPDU(oids), getTarget(), null);
        if (event != null && event.getResponse() != null) {
            return event;
        }
        throw new RuntimeException("GET timed out. It may be caused because of not simulating SNMP agent ");

    }


    private PDU getPDU(OID[] oids) {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        return pdu;

    }

    public List<String> getSubTreeAsString(String oid) {
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(getTarget(), new OID(oid));
        List<String> subtree = new ArrayList<String>();
        if (events == null || events.size() == 0) {
            throw new RuntimeException("No result returned.");
        }

        for (TreeEvent event : events) {
            if (event != null) {
                if (event.isError()) {
                    throw new RuntimeException("oid [" + oid + "] " + event.getErrorMessage());
                }

                VariableBinding[] varBindings = event.getVariableBindings();
                if (varBindings == null || varBindings.length == 0) {
                    System.out.println("No result returned.");
                }
                for (VariableBinding varBinding : varBindings) {
                    subtree.add(varBinding.getVariable().toString());

                }
            }
        }
        return subtree;
    }

    private Target getTarget() {

        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget
                target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(getSnmpVersion());
        return target;
    }

    private int getSnmpVersion() {
        if (snmpVersion == 1){
            return SnmpConstants.version1;
        }
        if (snmpVersion == 3 ){
            return SnmpConstants.version3;
        }
        else
            return SnmpConstants.version2c;
    }


}
