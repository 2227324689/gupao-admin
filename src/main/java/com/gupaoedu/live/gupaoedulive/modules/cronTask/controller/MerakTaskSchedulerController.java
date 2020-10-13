package com.gupaoedu.live.gupaoedulive.modules.cronTask.controller;

import com.gupaoedu.live.gupaoedulive.modules.cronTask.utils.MerakTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/cron")
public class MerakTaskSchedulerController {
    
    @Autowired
    MerakTaskScheduler taskScheduler;
    
    @RequestMapping("/schedule")
    public String schedule(@RequestParam String cron) {
        if(cron == null) {
             cron = "0/5 * * * * *";
        }
        Runnable runnable = new Runnable() {
            public void run() {
                String time = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
                System.out.println("Test GETaskScheduler Success at "  + time);
            }
        };

        taskScheduler.schedule(runnable, cron);
        return "Test TaskScheduler Interface.";
    }
}