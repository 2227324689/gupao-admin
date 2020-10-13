package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.gupaoedu.live.gupaoedulive.modules.monitor.model.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 */
@Api(value ="服务器监控", description = "服务器监控Api",tags = {"服务器监控操作接口"})
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    private String prefix = "modules/monitor";

    @ApiOperation(value = "获取服务器监控信息",notes = "获取服务器监控信息")
    @GetMapping("/server")
    public ModelAndView server(ModelMap modelMap){
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.put("server", server);
        return new ModelAndView("/modules/monitor/server");
    }

    @ApiOperation(value = "获取actuator监控信息视图",notes = "获取actuator监控信息视图")
    @GetMapping("/actuator")
    public ModelAndView health(){
        return new ModelAndView("/modules/monitor/actuator");
    }


}
