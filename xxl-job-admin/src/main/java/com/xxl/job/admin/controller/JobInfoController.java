package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.core.thread.JobTriggerPoolHelper;
import com.xxl.job.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * index controller
 * @author chenguiqi 修改接口为开放接口
 */
@Controller
@RequestMapping("/jobinfo")

public class JobInfoController {

	@Resource
	private XxlJobGroupDao xxlJobGroupDao;
	@Resource
	private XxlJobService xxlJobService;
	
	@RequestMapping
	public String index(Model model, @RequestParam(required = false, defaultValue = "-1") int jobGroup) {

		// 枚举-字典
		model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());	// 路由策略-列表
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());								// Glue类型-字典
		model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());	// 阻塞处理策略-字典

		// 任务组
		List<XxlJobGroup> jobGroupList =  xxlJobGroupDao.findAll();
		model.addAttribute("JobGroupList", jobGroupList);
		model.addAttribute("jobGroup", jobGroup);

		return "jobinfo/jobinfo.index";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	@PermessionLimit(limit = false)
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,  
			@RequestParam(required = false, defaultValue = "10") int length,
			int jobGroup, String jobDesc, String executorHandler, String filterTime) {
		return xxlJobService.pageList(start, length, jobGroup, jobDesc, executorHandler, filterTime);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> add(XxlJobInfo jobInfo) {
		return xxlJobService.add(jobInfo);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> update(XxlJobInfo jobInfo) {
		return xxlJobService.update(jobInfo);
	}
	
	/**
	 * 批量更新
	 * @author chenguiqi
	 * @param list
	 * @return
	 */
	@RequestMapping("/updateBatch")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> updateBatch(@RequestBody List<XxlJobInfo> list) throws Exception{
		return xxlJobService.updateBatch(list);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> remove(int id) {
		return xxlJobService.remove(id);
	}
	
	@RequestMapping("/pause")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> pause(int id) {
		return xxlJobService.pause(id);
	}
	
	@RequestMapping("/resume")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> resume(int id) {
		return xxlJobService.resume(id);
	}
	
	@RequestMapping("/trigger")
	@ResponseBody
	@PermessionLimit(limit = false)
	public ReturnT<String> triggerJob(int id, String executorParam) {
		// force cover job param
		if (executorParam == null) {
			executorParam = "";
		}

		JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam);
		return ReturnT.SUCCESS;
	}
	
}
