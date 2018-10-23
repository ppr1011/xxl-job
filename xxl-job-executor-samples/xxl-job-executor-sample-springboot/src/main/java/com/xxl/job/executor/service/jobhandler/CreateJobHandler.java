package com.xxl.job.executor.service.jobhandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @author chenguiqi
 *
 */

@JobHandler(value="createJobHandler")
@Component
public class CreateJobHandler extends IJobHandler {

	

	
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("XXL-JOB, This is Job Creater");		
		

		return SUCCESS;
	}

}
