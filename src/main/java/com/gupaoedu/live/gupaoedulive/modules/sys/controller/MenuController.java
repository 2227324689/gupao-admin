
package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenu;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.MenuService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysMenuSelectVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleAuthVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;
import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.SUCCESS;

/**
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysMenu/list");
    }


    @RequestMapping(value = "/form/{operation}/{id}",method = RequestMethod.GET)
    public ModelAndView form(@PathVariable("operation")String operation,@PathVariable("id")Integer id,HttpServletRequest request) {

        int parentId=-1;
        String icon="layui-icon-star-fill";
        int isShow=0;
        String permission="";
        String path="";
        String type="direc";
        ModelAndView modelAndView=new ModelAndView("/modules/sys/sysMenu/form");
        if(operation.equals("edit")) {
            SysMenu sysMenu = menuService.getById(id);
            if (sysMenu != null) {
                if (sysMenu.getParentId().intValue() > 0) {
                    parentId = sysMenu.getParentId().intValue();
                }
                if (StringUtils.isNotBlank(sysMenu.getIcon())) {
                    icon = sysMenu.getIcon();
                }
                isShow = sysMenu.getIsShow();
                permission = sysMenu.getPermission();
                path = sysMenu.getUrl();
                type = sysMenu.getMenuType();
            }
        }else if(operation.equals("addChild")){
            parentId=id;
            type=(request.getParameter("type").equalsIgnoreCase("direc")?"menu":"api");
        }
        modelAndView.addObject("defaultIcon",icon);
        modelAndView.addObject("parentId",parentId+"");
        modelAndView.addObject("isShow",isShow);
        modelAndView.addObject("permission",permission);
        modelAndView.addObject("path",path);
        modelAndView.addObject("type",type);
        return modelAndView;
    }

    @GetMapping("/treeList")
    public SysMenuSelectVo treeList(){
        SysMenuSelectVo sysMenuSelectVo=new SysMenuSelectVo();
        sysMenuSelectVo.setOpen(true);
        sysMenuSelectVo.setId(-1);
        sysMenuSelectVo.setName("主目录");
        sysMenuSelectVo.setChildren(menuService.getTreeList());
        return sysMenuSelectVo;
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ProcessResult roleMenuTreeselect(@PathVariable("roleId") Integer roleId){
        List<SysRoleAuthVo> menus = menuService.selectMenuList();
        ProcessResult result=new ProcessResult();
        result.setData(menus);
        return result;
    }

    @GetMapping("/treeListPermission")
    public ProcessResult treeListPermission(HttpServletRequest request){
        SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
        ProcessResult processResult = new ProcessResult();
        try {
            processResult.setResultStat(SUCCESS);
            processResult.setData(menuService.treeListPermission(sysUser));
        }catch (Exception e){
            processResult.setResultStat(ERROR);
            processResult.setMess(e.getMessage());
        }
        return processResult;
    }

    @GetMapping
    public PageResult<List<SysMenu>> getAll() {
        List<SysMenu> sysMenuList = menuService.getAll();
       return new PageResult(sysMenuList);
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Integer id) {
        if(id!=null){
            SysMenu sysMenu = menuService.getById(id);
            ProcessResult processResult=new ProcessResult();
            processResult.setData(sysMenu);
            return processResult;
        }
        return null;
    }

    @RequiresPermissions(value = {"sys:menu:update","sys:menu:add"},logical = Logical.OR)
    @SysLog("新增或修改菜单")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate(SysMenu sysMenu) {
        try {
            menuService.saveOrUpdate(sysMenu);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @RequiresPermissions("sys:menu:delete")
    @SysLog("删除菜单")
    @DeleteMapping(value = "/delete/{id}")
    public ProcessResult delete( @PathVariable Integer id) {
        try {
            SysMenu menu = menuService.getById(id);
            if(menu!=null && "0".equals(menu.getIsSysMenu())){
                throw new Exception("系统菜单，无法删除！");
            }
            menuService.deleteById(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @RequiresPermissions("sys:role:auth")
    @ApiOperation(value = "保存菜单权限",notes = "保存菜单权限")
    @SysLog("保存菜单权限")
    @PostMapping("/saveMenuPermission")
    public ProcessResult saveMenuPermission(Integer roleId,@RequestParam("ids[]") Integer[] ids){
        try {
            menuService.saveMenuPermission(roleId,ids);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

}
