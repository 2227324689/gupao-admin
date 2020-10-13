package com.gupaoedu.live.gupaoedulive.modules.job.quartz.listenner;


import com.gupaoedu.live.gupaoedulive.modules.job.quartz.utils.QuartzManager;
import com.gupaoedu.live.gupaoedulive.modules.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

	@Autowired
	JobService scheduleJobService;

	@Autowired
	QuartzManager quartzManager;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}