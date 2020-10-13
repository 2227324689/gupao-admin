package com.gupaoedu.live.gupaoedulive.modules.job.task;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class WelcomeJob implements Job{
	/*@Autowired
	SimpMessagingTemplate template;*/

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("welcomeJob");
    }

}