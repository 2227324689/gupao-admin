
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.MenuService;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;

/**
 */
@Api(value ="角色管理模块", description = "角色管理Api",tags = {"角色管理操作接口"})
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysRole/list");
    }

    @GetMapping
    public PageResult<SysRole> getAll(SysRole sysRole, String keyword, HttpServletRequest request) {
        List<SysRole> roleList = roleService.getAll(sysRole,keyword);
        return new PageResult(roleList);
    }

    @RequiresPermissions(value = {"sys:role:update","sys:role:add"},logical = Logical.OR)
    @SysLog("保存角色")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate( SysRole sysRole) {
        try {
            roleService.saveOrUpdate(sysRole);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        SysRole sysRole = roleService.getById(id);
        ProcessResult processResult=new ProcessResult();
        processResult.setData(sysRole);
        return processResult;
    }

    @RequiresPermissions("sys:role:delete")
    @SysLog("删除角色")
    @DeleteMapping(value = "/delete/{id}")
    public ProcessResult delete(@PathVariable Integer id) {
        try {
            roleService.deleteById(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @GetMapping("/form")
    public ModelAndView form() {
        return new ModelAndView("/modules/sys/sysRole/form");
    }


    @GetMapping("/auth/{roleId}")
    public ModelAndView auth(@PathVariable("roleId")Integer roleId) {
        ModelAndView modelAndView=new ModelAndView("/modules/sys/sysRole/authmanage");
        modelAndView.addObject("roleId",roleId);
        List<Integer> checkedIds=menuService.selectMenuListByRoleId(roleId);
        modelAndView.addObject("checkedKeys",checkedIds.toString());
        return modelAndView;
    }

    @RequiresPermissions("sys:role:userlist")
    @RequestMapping(value = "/form/user/{roleId}",method = RequestMethod.GET)
    public ModelAndView userForm(@PathVariable("roleId")Integer roleId) {
        ModelAndView modelAndView=new ModelAndView("/modules/sys/sysRole/userlist");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }
}
