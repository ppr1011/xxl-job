package com.xxl.job.executor.service.jobhandler;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @author chenguiqi
 *
 */
@JobHandler(value="serialParallelChild11")
@Component
public class SerialParallelChild11 extends IJobHandler {

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("串并行子任务11");
      	for (int i = 0; i < 5; i++) {
			XxlJobLogger.log("beat at:" + i);
			TimeUnit.SECONDS.sleep(2);
			System.out.println("串并行子任务11 "+i);
		}
      	System.out.println("串并行子任务11");
		return ReturnT.SUCCESS;
	}

}