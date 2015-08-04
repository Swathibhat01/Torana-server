package com.torana.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * @author Torana
 * */
public class OpenStackCallsJobs  extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		String group 	= 	(String) arg0.getJobDetail().getJobDataMap().get("group");

		prtMsg("Quartz Job****..Start.****"+group);

		try{
			QuartzJobTracker jobTracker = new QuartzJobTracker();
			jobTracker.processOpenStackCalls(group);
		}catch(Exception ex){
			ex.printStackTrace();		
		}
		prtMsg("Quartz Job****..END.****"+group);

	}
	public  void prtMsg(String msg) {
		System.out.println(""+msg);
	}
}


