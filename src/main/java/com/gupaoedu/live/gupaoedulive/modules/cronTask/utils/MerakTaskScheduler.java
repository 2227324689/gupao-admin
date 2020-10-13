package com.gupaoedu.live.gupaoedulive.modules.cronTask.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class MerakTaskScheduler {
     
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
     
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    public void schedule(Runnable task, String cron){
        if(cron == null || "".equals(cron)) {
            cron = "0 * * * * *";
        }
        threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
    }
     
    /**
     * shutdown and init
     */
    public void reset(){
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
    }
    
    /**
     * shutdown before a new schedule operation
     * @param task
     * @param cron
     */
    public void resetSchedule(Runnable task, String cron){
        shutdown();
        threadPoolTaskScheduler.initialize();
        schedule(task, cron);
    }
    
    /**
     * shutdown
     */
    public void shutdown(){
        threadPoolTaskScheduler.shutdown();
    }
}