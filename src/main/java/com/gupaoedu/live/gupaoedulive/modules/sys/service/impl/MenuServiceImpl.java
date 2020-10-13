
package com.gupaoedu.live.gupaoedulive.modules.sys.service.impl;


import com.gupaoedu.live.gupaoedulive.conf.SysUserContext;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysMenuMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysMenuRoleMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenu;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenuRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.MenuService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysMenuSelectVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysMenuVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleAuthVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;


    @Override
    public List<SysMenuSelectVo> getTreeList() {
        List<SysMenu> topList=sysMenuMapper.getTopList();
        return convertList(topList);
    }

    @Override
    public List<SysMenu> getAll() {
        return sysMenuMapper.selectAllMenu();
    }

    @Override
    public SysMenu getById(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(SysMenu sysMenu) throws Exception{
        if(sysMenu.getMenuType().equals("api")){
            byte show=0;
            sysMenu.setIsShow(show);
        }
        if(sysMenu.getId()!=null){//修改
            if("0".equals(sysMenu.getIsSysMenu())){
                AtomicBoolean isPermission= new AtomicBoolean(true);
                SysUser sysUser= SysUserContext.getUser();
                sysUser.getRoles().forEach(v->{
                    if(0==v.getRoleType()){
                        isPermission.set(false);
                    }
                });
                if(isPermission.get()){
                    throw new Exception("请高抬贵手！验证【编辑功能】可自己新增菜单再编辑！");
                }
            }
            sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        }else{
            sysMenu.setIsSysMenu("1");//非系统菜单
            sysMenuMapper.insert(sysMenu);
        }

    }

    @Override
    public void deleteById(Integer id) {
        //删除菜单的同时，也删除角色对应的菜单权限
        sysMenuMapper.deleteByPrimaryKey(id);
        Example example=new Example(SysMenuRole.class);
        example.createCriteria().andEqualTo("menuId",id);
        sysMenuRoleMapper.deleteByExample(example);

    }

    @Override
    public List<SysMenu> getSelMenuPermission(Integer roleId) {
        return sysMenuMapper.getSelMenuPermission(roleId);
    }

    @Override
    public void saveMenuPermission(Integer roleId, Integer[] ids) {
        //先删除改角色的权限，再保存权限
        sysMenuRoleMapper.deleteByRoleId(roleId);
        if(ids.length>0){
           for (int i=0;i<ids.length;i++){
               Integer id=ids[i];
               if(id!=null){
                   SysMenuRole sysMenuRole=new SysMenuRole();
                   sysMenuRole.setRoleId(roleId);
                   sysMenuRole.setMenuId(ids[i]);
                   sysMenuRoleMapper.insert(sysMenuRole);
               }
           }
        }
    }

    @Override
    public List<SysMenuVo> treeListPermission(SysUser sysUser) {

        SysRole sysRole = sysUser.getRoles().iterator().next();
        Integer roleId = sysRole.getId();
        if(roleId!=null){
            List<SysMenu> permission = getSelMenuPermission(roleId);
            List<SysMenu> topList=sysMenuMapper.getOperationMenuTop();
            topList=hasPermission(topList,permission);
            return convertListPermission(topList,permission);
        }
        return new ArrayList<>();
    }

    @Override
    public List<SysRoleAuthVo> selectMenuList() {
        List<SysMenu> sysMenuList=sysMenuMapper.getTopList();
        return convertToRoleAuthList(sysMenuList);
    }

    @Override
    public List<Integer> selectMenuListByRoleId(Integer roleId) {
        return sysMenuMapper.selectMenuListByRoleId(roleId);
    }

    private List<SysMenuVo> convertListPermission(List<SysMenu> toplist, List<SysMenu> permission) {
        List<SysMenuVo> result=new ArrayList<>();
        if(toplist.size()>0){
            for (SysMenu sysMenu:toplist){
                SysMenuVo vo=new SysMenuVo();
                BeanUtils.copyProperties(sysMenu,vo);
                List<SysMenu> list=sysMenuMapper.getChildDeptList(sysMenu.getId());
                list=hasPermission(list,permission);
                if(list.size()>0){
                    vo.setChildren(convertListPermission(list,permission));
                }
                result.add(vo);
            }
        }
        return result;
    }

    private List<SysMenu> hasPermission(List<SysMenu> menus,List<SysMenu> permission){
        List<SysMenu> results=new ArrayList<>();
        if(menus.size()>0 && permission.size()>0){
            for (SysMenu menu:menus){
                if(hasPermission(permission,menu)){
                    results.add(menu);
                }
            }
        }
        return results;
    }


    private boolean hasPermission(List<SysMenu> permission,SysMenu sysMenu){
        if(permission.size()>0){
            for (SysMenu menu:permission){
                if(sysMenu.getId().equals(menu.getId())){
                    return true;
                }
            }
        }
        return false;
    }


    private List<SysMenuSelectVo> convertList(List<SysMenu> toplist){
        List<SysMenuSelectVo> result=new ArrayList<>();
        if(toplist.size()>0){
            for (SysMenu sysMenu:toplist){
                SysMenuSelectVo vo=new SysMenuSelectVo();
                vo.setName(sysMenu.getMenuName());
                vo.setId(sysMenu.getId());
                vo.setOpen(false);
                List<SysMenu> list=sysMenuMapper.getChildDeptList(sysMenu.getId());
                if(list.size()>0){
                    vo.setChildren(convertList(list));
                }
                result.add(vo);
            }
        }
        return result;
    }

    private List<SysRoleAuthVo> convertToRoleAuthList(List<SysMenu> topList){
        List<SysRoleAuthVo> result=new ArrayList<>();
        if(topList.size()>0){
            for (SysMenu sysMenu:topList){
                SysRoleAuthVo vo=new SysRoleAuthVo();
                vo.setLabel(sysMenu.getMenuName());
                vo.setId(sysMenu.getId());
                vo.setOpen(false);
                List<SysMenu> list=sysMenuMapper.getChildDeptList(sysMenu.getId());
                if(list.size()>0){
                    vo.setChildren(convertToRoleAuthList(list));
                }
                result.add(vo);
            }
        }
        return result;
    }
}
