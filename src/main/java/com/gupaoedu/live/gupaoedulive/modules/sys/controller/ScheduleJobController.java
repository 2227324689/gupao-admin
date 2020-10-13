
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.job.model.SysTask;
import com.gupaoedu.live.gupaoedulive.modules.job.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;
import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.SUCCESS;

/**
 * 定时任务
 *
 */
@Api(value ="定时任务", description = "定时任务Api",tags = {"定时任务操作接口"})
@RestController
@RequestMapping("/data/scheduler")
public class ScheduleJobController {
	@Autowired
	private JobService scheduleJobService;

	@ApiOperation(value = "定时任务列表视图",notes = "定时任务列表视图")
	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("/modules/task/list");
	}

	@ApiOperation(value = "定时任务表单视图",notes = "定时任务表单视图")
	@GetMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("/modules/task/form");
	}
	
	/**
	 * 定时任务列表
	 */
	@ApiOperation(value = "获取定时任务列表",notes = "获取定时任务列表")
	@GetMapping("/pages")
	public PageResult list(@ApiParam(name = "SysTask",value="任务实体",required = true) SysTask sysTask,
						   @ApiParam(name = "keyword",value="查询字段",required = false) String keyword){
		List<SysTask> page = scheduleJobService.queryPage(sysTask,keyword);
		return new PageResult(page);
	}
	
	/**
	 * 定时任务信息
	 */
	@ApiOperation(value = "获取定时任务信息",notes = "获取定时任务信息")
	@PostMapping("/info/{id}")
	public ProcessResult info(@ApiParam(name = "id",value="任务ID",required = true) @PathVariable("id") Long id){
		SysTask schedule = scheduleJobService.get(id);
		return new ProcessResult(schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@ApiOperation(value = "保存定时任务",notes = "保存定时任务")
	@SysLog("保存定时任务")
	@PostMapping("/saveOrUpdate")
	public ProcessResult saveOrUpdate(@ApiParam(name = "scheduleJob",value="任务实体",required = true) SysTask scheduleJob){
		if(scheduleJob.getId()!=null){
			scheduleJobService.update(scheduleJob);
		}else{
			scheduleJobService.save(scheduleJob);
		}
		return new ProcessResult();
	}

	/**
	 * 批量删除定时任务
	 */
	@ApiOperation(value = "批量删除定时任务",notes = "批量删除定时任务")
	@SysLog("批量删除定时任务")
	@PostMapping("/delete")
	public ProcessResult delete(@ApiParam(name = "ids",value="任务ID数组",required = true)@RequestParam("ids[]")Long[] ids){
		scheduleJobService.batchRemove(ids);
		return new ProcessResult();
	}

	/**
	 * 删除定时任务
	 */
	@ApiOperation(value = "删除定时任务",notes = "删除定时任务")
	@SysLog("删除定时任务")
	@PostMapping("/deleteOnlyOne/{id}")
	public ProcessResult deleteOnlyOne(@ApiParam(name = "id",value="任务ID",required = true)@PathVariable Long id){
		scheduleJobService.remove(id);

		return new ProcessResult();
	}

	@ApiOperation(value = "启动或停止定时任务",notes = "启动或停止定时任务")
	@SysLog("启动或停止定时任务")
	@PostMapping(value = "/changeJobStatus")
	public ProcessResult changeJobStatus(@ApiParam(name = "id",value="任务ID",required = true)Long id,
										 @ApiParam(name = "cmd",value="操作类型：start 启动,stop 停止",required = true)String cmd ) {
		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		}
		try {
			scheduleJobService.changeStatus(id, cmd);
			return new ProcessResult(SUCCESS,"任务" + label + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProcessResult(ERROR,"任务" + label + "失败");
	}

}
