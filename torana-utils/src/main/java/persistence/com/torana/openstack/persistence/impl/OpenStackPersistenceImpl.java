package com.torana.openstack.persistence.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.hibernate.core.dao.HibernateBaseDAO;
import com.torana.openstack.persistence.OpenStackPersistence;
import com.torana.utils.Util;
import com.torana.ws.entities.CloudElements;

public class OpenStackPersistenceImpl extends HibernateBaseDAO implements OpenStackPersistence{
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenStackPersistenceImpl.class);
	List<? extends Server> servers;
	List<? extends Server>[] servers_array;
	List[] arrayQ = new List[9];
	ArrayList<Integer> elements = new ArrayList<>();
	Map m = new HashMap();
	List l = new ArrayList();
	int i = 0;
	Util util = new Util();
	public void dumpOpenStackToNeo4j() {
		LOGGER.info("------In OpenStackPersistenceImpl Class------------------");
		Session session 	= null;
		try {
			String hqlString = "from Cloud where type=:type"; 
			Query query = getSession().createQuery(hqlString);
			query.setParameter("type","openstack");
			List<CloudElements> clouds = query.list();
			servers_array = new ArrayList[clouds.size()];
			for (CloudElements cloud : clouds) {
				//Connect to OpenStack				
				OSClient os = OSFactory.builder().endpoint(cloud.getIpAddress())
						.credentials(cloud.getUserName(),cloud.getPassword())
						.tenantName(cloud.getUserName())
						.authenticate();
				servers = os.compute().servers().list();
				servers_array[i] = servers;
				i = i+1;
			}
			for(int i=0;servers_array.length>i;i++){
				System.out.println("81");
				servers = servers_array[i];
				int n = servers.size();
				for(int i1=0;n>i1;i1++)
				{System.out.println("83");
				Server s = servers.get(i1);
				Map<String, Serializable> tempMap = new HashMap<String, Serializable>();
				System.out.println("\ns.getName():::"+s.getName());
				tempMap.put("ImageID",s.getId());
				tempMap.put("Name",s.getName());
				tempMap.put("ImageID",s.getImageId());
				tempMap.put("AccessIPv4",s.getAccessIPv4());
				tempMap.put("AccessIPv6",s.getAccessIPv6());
				tempMap.put("Addresses",s.getAddresses());
				tempMap.put("AdminPass",s.getAdminPass());
				tempMap.put("AvailabilityZone",s.getAvailabilityZone());
				tempMap.put("Created",s.getCreated());
				tempMap.put("DiskConfig",s.getDiskConfig());
				tempMap.put("Fault",s.getFault());
				tempMap.put("FlavorID",s.getFlavorId());
				tempMap.put("Host",s.getHost());
				tempMap.put("HostID",s.getHostId());
				tempMap.put("HypervisorHostname",s.getHypervisorHostname());
				tempMap.put("Image",s.getImage());
				tempMap.put("HypervisorHostname",s.getHypervisorHostname());
				tempMap.put("HypervisorHostname",s.getTenantId());
				System.out.println("\ntempMap:::"+tempMap);
				m.put("map"+i1, tempMap);



				Iterator iterator = m.entrySet().iterator();
				if(m.size()>0){
					while (iterator.hasNext()) {
						Map.Entry pair = (Map.Entry)iterator.next();
						Iterator iterator1 = ((Map) pair.getValue()).entrySet().iterator();
						StringBuilder strBuilder=new StringBuilder("CREATE (n {");
						int count=0;
						while (iterator1.hasNext()) {
							Map.Entry mapEntry = (Map.Entry) iterator1.next();
							if(mapEntry.getKey() != "id" ){
								strBuilder.append(""+mapEntry.getKey()+":'"+mapEntry.getValue()+"',");
							}
						}
						strBuilder.setLength(strBuilder.length() - 1);
						strBuilder.append("}) return n");
						util.executeQuery(strBuilder.toString());



					}
				}

				}
			}
		}catch(Exception exception){
			exception.printStackTrace();

		}finally{
			if(session != null){
				session.clear();
				session.close();
			}
		}


	}

}
