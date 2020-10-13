
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.PostService;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.RoleService;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.UserService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleSelectVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;

/**
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysUser/list");
    }

    @RequestMapping(value = "/form/{deptId}/{id}",method = RequestMethod.GET)
    public ModelAndView form(@PathVariable("deptId")Integer deptId,@PathVariable("id")Integer id) {
        ModelAndView modelAndView=new ModelAndView("/modules/sys/sysUser/form");
        modelAndView.addObject("deptId",deptId);
        List<SysRoleSelectVo> sysRoleSelectVos=roleService.getAllRoles();
        String checkedRole="[]";
        if(id!=null&&id.intValue()!=0){//表示编辑状态
            List<Integer> list=roleService.findByUserId(id);
            if(list!=null&&!list.isEmpty()){
                checkedRole=JSON.toJSONString(list);
            }
        }
        modelAndView.addObject("postList",postService.getAllPost());
        modelAndView.addObject("roleList", JSON.toJSONString(sysRoleSelectVos));
        modelAndView.addObject("checkedRole", checkedRole);
        modelAndView.addObject("defaultRole",checkedRole.replace("[","").replace("]",""));
        return modelAndView;
    }


    @RequestMapping(value = "/userlist/{roleId}",method = RequestMethod.GET)
    public PageResult<SysUser> userlist(@PathVariable("roleId")Integer roleId){
        List<SysUser> users=userService.selectUserListByRoleId(roleId);
        return new PageResult(new PageInfo<SysUser>(users)).setCode(0);
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public PageResult<SysUser> getAll(SysUser sysUser,String keyword) {
        List<SysUser> userInfoList = userService.getAll(sysUser,keyword);
        return new PageResult(new PageInfo<SysUser>(userInfoList)).setCode(0);
    }

    @RequiresPermissions("sys:user:batchDel")
    @SysLog("批量删除用户")
    @PostMapping(value = "/batchDelete")
    public ProcessResult batchDelete(@RequestParam("ids[]") Integer[] ids) {
        try {
            //----start----防止测试用户胡乱删除数据
            if(ids!=null && ids.length>0){
                for (Integer id:ids){
                    SysUser user = userService.getById(id);
                    if(user!=null && "0".equals(user.getIsSysUser())){
                        throw new Exception("请高抬贵手！验证【删除功能】可自己新增用户再删除！");
                    }
                }
            }
           //-----end-----防止测试用户胡乱删除数据
            userService.batchDelete(ids);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @RequiresPermissions(value = {"sys:user:edit","sys:user:add"},logical = Logical.OR)
    @SysLog("保存用户信息")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate(SysUser sysUser) {
        try {
            userService.saveOrUpdate(sysUser);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view( @PathVariable Integer id) {
        SysUser userInfo = userService.getById(id);
        ProcessResult processResult=new ProcessResult();
        processResult.setData(userInfo);
        return processResult;
    }

    @RequiresPermissions("sys:user:delete")
    @SysLog("删除用户")
    @DeleteMapping(value = "/delete/{id}")
    public ProcessResult delete( @PathVariable Integer id) {
        try {
            userService.deleteById(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }
}
