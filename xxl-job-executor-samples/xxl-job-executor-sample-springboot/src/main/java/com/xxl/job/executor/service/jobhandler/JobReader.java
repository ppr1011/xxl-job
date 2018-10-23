package com.xxl.job.executor.service.jobhandler;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * @author chenguiqi
 *
 */

@JobHandler(value="jobReader")
@Component
public class JobReader extends IJobHandler{
	
	String PREFIX = "xxl-job.jobs[";
	String POSTFIX = "].";
	
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		return SUCCESS;
	}

}
