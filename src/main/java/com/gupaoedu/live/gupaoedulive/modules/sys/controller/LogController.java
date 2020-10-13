
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gupaoedu.live.gupaoedulive.core.result.JsonResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.core.util.FileUtil;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDealLog;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 */
@Api(value ="系统日志", description = "系统日志Api",tags = {"系统日志操作接口"})
@RestController
@RequestMapping("/sys/log")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "日志列表视图",notes = "日志列表视图")
    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysLog/list");
    }

    @ApiOperation(value = "获取日志列表",notes = "获取日志列表")
    @GetMapping
    public PageResult<SysDealLog> getAll(@ApiParam(name = "sysDealLog",value="日志实体",required = true) SysDealLog sysDealLog,
                                         @ApiParam(name = "logDate",value="日志时间",required = false) @Param("logDate") String logDate,
                                         @ApiParam(name = "keyword",value="查询字段:如用户名，操作",required = false) String keyword,
                                         HttpServletRequest request) {
        SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
        if(sysUser==null){
            new PageResult();
        }
        List<SysDealLog> logList = sysLogService.getListByPage(sysDealLog,keyword, logDate,sysUser);
        return new PageResult(new PageInfo<SysDealLog>(logList));
    }

    @ApiOperation(value = "导出日志",notes = "导出日志")
    @GetMapping(value="/export")
    public JsonResult<Integer> downLoginLogExcel(HttpServletResponse response,
                                                 @ApiParam(name = "logDate",value="日志时间",required = false) @Param("logDate") String logDate,
                                                 @ApiParam(name = "keyword",value="查询字段:如用户名，操作",required = false) String keyword,
                                                 HttpServletRequest request){
        try{
            SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
            if(sysUser==null){
                new JsonResult<Integer>(1,"用户未登录！");
            }
            List<SysDealLog> logList = sysLogService.getList(null,keyword,logDate,sysUser);
            FileUtil.downloadFile(response, sysLogService.getExcel(logList));
            return new JsonResult<>(0);
        }catch (Exception e){
            return new JsonResult<>(e);
        }
    }


}
