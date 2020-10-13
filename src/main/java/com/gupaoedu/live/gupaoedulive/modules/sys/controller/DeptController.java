
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDept;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.DeptService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysDeptSelectVo;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;

/**
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysDept/list");
    }

    @PostMapping
    public List<SysDept> getAll() {
        return deptService.getAll();
    }

    @GetMapping("/treeList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ProcessResult treeList() {
        ProcessResult result=new ProcessResult(deptService.treeList());
        return result;
    }

    @GetMapping("/treeSelectList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<SysDeptSelectVo> treeSelectList(){
        return deptService.treeSelectList();
    }

    @GetMapping("/getDeptById/{id}")
    public ProcessResult getDeptById(@ApiParam(name = "id",value="部门ID",required = true) @PathVariable Integer id){
        ProcessResult result=new ProcessResult();
        result.setData(deptService.getDeptById(id));
        return result;
    }

    @RequiresPermissions(value = {"sys:dept:update","sys:dept:add"},logical = Logical.OR)
    @SysLog("保存部门信息")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate(@ApiParam(name = "sysDept",value="部门实体",required = true) SysDept sysDept, HttpServletRequest request) {
        try {
            SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
            if(sysDept.getId()!=null){
                sysDept.setUpdateTime(new Date());
                sysDept.setUpdateBy(sysUser.getUsername());
            }else{
                sysDept.setCreateBy(sysUser.getUsername());
                sysDept.setCreateTime(new Date());
            }
            deptService.saveOrUpdate(sysDept);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @SysLog("删除部门")
    @PostMapping(value = "/delete/{id}")
    public ProcessResult delete(@ApiParam(name = "id",value="部门ID",required = true) @PathVariable Integer id) {
        try {
            deptService.deleteById(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }
}
