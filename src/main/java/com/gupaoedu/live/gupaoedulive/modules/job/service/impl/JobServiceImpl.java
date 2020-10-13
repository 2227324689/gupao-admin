package com.gupaoedu.live.gupaoedulive.modules.job.service.impl;


import com.github.pagehelper.PageHelper;
import com.gupaoedu.live.gupaoedulive.modules.job.constants.Constant;
import com.gupaoedu.live.gupaoedulive.modules.job.mapper.SysTaskMapper;
import com.gupaoedu.live.gupaoedulive.modules.job.model.SysTask;
import com.gupaoedu.live.gupaoedulive.modules.job.quartz.utils.QuartzManager;
import com.gupaoedu.live.gupaoedulive.modules.job.service.JobService;
import com.gupaoedu.live.gupaoedulive.modules.job.utils.ScheduleJobUtils;
import com.gupaoedu.live.gupaoedulive.modules.job.vo.ScheduleJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

	@Autowired
	private SysTaskMapper taskScheduleJobMapper;

	@Autowired
	QuartzManager quartzManager;

	@Override
	public SysTask get(Long id) {
		return taskScheduleJobMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SysTask> queryPage(SysTask sysTask,String keyword) {
		PageHelper.startPage(sysTask.getPage(),sysTask.getLimit());
		//return taskScheduleJobMapper.select(sysTask);
		Example example=new Example(SysTask.class);
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(keyword)){
			keyword="%"+keyword+"%";
			criteria.andLike("methodName",keyword);
			criteria.orLike("beanClass",keyword);
			criteria.orLike("jobGroup",keyword);
		}
		return taskScheduleJobMapper.selectByExample(example);
	}


	@Override
	public int save(SysTask taskScheduleJob) {
		return taskScheduleJobMapper.insert(taskScheduleJob);
	}

	@Override
	public int update(SysTask taskScheduleJob) {
		return taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
	}

	@Override
	public int remove(Long id) {
		try {
			SysTask scheduleJob = get(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			return taskScheduleJobMapper.deleteByPrimaryKey(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int batchRemove(Long[] ids) {
		for (Long id : ids) {
			try {
				SysTask scheduleJob = get(id);
				quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			} catch (SchedulerException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskScheduleJobMapper.batchRemove(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<SysTask> jobList = taskScheduleJobMapper.selectAll();
		for (SysTask scheduleJob : jobList) {
			if ("1".equals(scheduleJob.getJobStatus())) {
				ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
				quartzManager.addJob(job);
			}

		}
	}

	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		SysTask scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
		} else {
			if (!Constant.STATUS_RUNNING_START.equals(cmd)) {
			} else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
		}
		update(scheduleJob);
	}

	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		SysTask scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		update(scheduleJob);
	}



}
