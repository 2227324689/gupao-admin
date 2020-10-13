package com.gupaoedu.live.gupaoedulive.modules.cronTask.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component("cronTask")
public class CronTask {
    Logger logger= LoggerFactory.getLogger(CronTask.class);

    @Scheduled(cron = "0 0/30 * * * ?")  // 每秒执行一次
    public void getTask() {
        logger.info("cron任务进行中...");
    } 
}