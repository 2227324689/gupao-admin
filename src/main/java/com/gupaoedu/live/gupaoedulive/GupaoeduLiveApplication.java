package com.gupaoedu.live.gupaoedulive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.gupaoedu.live.gupaoedulive.modules.*.mapper","com.gupaoedu.live.gupaoedulive.openapi.*.mapper"})
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class GupaoeduLiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(GupaoeduLiveApplication.class, args);
    }

}


