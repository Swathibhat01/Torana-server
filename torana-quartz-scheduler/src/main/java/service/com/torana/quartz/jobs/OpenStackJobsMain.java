package com.torana.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.openstack4j.model.compute.Server;

import com.torana.openstack.persistence.impl.OpenStackPersistenceImpl;

/**
 * @author Torana
 * */
public class OpenStackJobsMain {
	List<? extends Server> servers;
	List<? extends Server>[] servers_array;
	//Map<String, Object>[] myArray = (Map<String, Object>[]) new Map[10];
	//Map<String, Object>[] myArray1 = new Map<String, Object>[10];
	List[] arrayQ = new List[9];
	ArrayList<Integer> elements = new ArrayList<>();
	Map m = new HashMap();
	List l = new ArrayList();
	int i = 0;
	private SessionFactory sessionFactory = null;

	public OpenStackJobsMain(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		//this.session=sessionFactory.openSession();
	}
	public void getOrderStatus() {}

	public void openStackToNeo4jData() {
		OpenStackPersistenceImpl openStackPersistence =new OpenStackPersistenceImpl();
		openStackPersistence.dumpOpenStackToNeo4j();
//		Session session 	= null;
//		//System.out.println("\nserverarrayssizetestbefore:::"+servers_array.length);
//		try {
//			session 	= sessionFactory.openSession();
//			String hqlString = "from Cloud where type=:type"; 
//			Query query = session.createQuery(hqlString);
//			query.setParameter("type","openstack");
//			List<Cloud> clouds = query.list();
//			servers_array = new ArrayList[clouds.size()];
//			for (Cloud cloud : clouds) {
//				//System.out.println("Name--->"+cloud.getName()+"-----Ip------>"+cloud.getIpAddress()+"----UserName----->"+cloud.getUserName()+"---Password--->"+cloud.getPassword());
//				//TO-do
//				
//				OSClient os = OSFactory.builder().endpoint(cloud.getIpAddress())
//						.credentials(cloud.getUserName(),cloud.getPassword())
//						.tenantName(cloud.getUserName())
//						.authenticate();
//				servers = os.compute().servers().list();
//				//System.out.println("Ttest:::"+servers);
//				servers_array[i] = servers;
//				i = i+1;
//			}
//			System.out.println("\nserverarrayssizetestafter:::"+servers_array.length);
//			System.out.println("i value:::"+i);
//			System.out.println("\nserverssize:::"+servers.size()+"\nserverarrayssize:::"+servers_array.length);
//			for(int i=0;servers_array.length>i;i++){
//				System.out.println("81");
//				servers = servers_array[i];
//				int n = servers.size();
//			for(int i1=0;n>i1;i1++)
//				{System.out.println("83");
//				Server s = servers.get(i1);
//				//List<String>  a = Arrays.asList(s.getInstanceName().split("="));
//				//System.out.println("\nLIST:::"+a);
//				Map tempMap = new HashMap();
//				System.out.println("\ns.getName():::"+s.getName());
//				tempMap.put("ImageID",s.getId());
//				tempMap.put("Name",s.getName());
//				tempMap.put("ImageID",s.getImageId());
//				tempMap.put("AccessIPv4",s.getAccessIPv4());
//				tempMap.put("AccessIPv6",s.getAccessIPv6());
//				tempMap.put("Addresses",s.getAddresses());
//				tempMap.put("AdminPass",s.getAdminPass());
//				tempMap.put("AvailabilityZone",s.getAvailabilityZone());
//				tempMap.put("Created",s.getCreated());
//				tempMap.put("DiskConfig",s.getDiskConfig());
//				tempMap.put("Fault",s.getFault());
//				tempMap.put("FlavorID",s.getFlavorId());
//				tempMap.put("Host",s.getHost());
//				tempMap.put("HostID",s.getHostId());
//				tempMap.put("HypervisorHostname",s.getHypervisorHostname());
//				tempMap.put("Image",s.getImage());
//				tempMap.put("HypervisorHostname",s.getHypervisorHostname());
//				tempMap.put("HypervisorHostname",s.getTenantId());
//				System.out.println("\ntempMap:::"+tempMap);
//				m.put("map"+i1, tempMap);
//				
//			}
//		}System.out.println("\nSIZE123:::"+m.size());
//		}catch(Exception exception){
//			exception.printStackTrace();
//			
//		}finally{
//			if(session != null){
//				session.clear();
//				session.close();
//			}
//		}
//		

	}

	public String addOneDay(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return dateFormat.format(cal.getTime());
	}

}
