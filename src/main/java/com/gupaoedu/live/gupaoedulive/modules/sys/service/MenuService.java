package com.gupaoedu.live.gupaoedulive.modules.sys.service;


import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenu;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysMenuSelectVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysMenuVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleAuthVo;

import java.util.List;

public interface MenuService {
    List<SysMenuSelectVo> getTreeList();

    List<SysMenu> getAll();

    SysMenu getById(Integer id);

    void saveOrUpdate(SysMenu sysMenu) throws Exception;

    void deleteById(Integer id);

    List<SysMenu> getSelMenuPermission(Integer roleId);

    void saveMenuPermission(Integer roleId, Integer[] ids);

    List<SysMenuVo> treeListPermission(SysUser sysUser);

    /**
     * 根据用户查询系统菜单列表
     *
     * @return 菜单列表
     */
    List<SysRoleAuthVo> selectMenuList();

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Integer> selectMenuListByRoleId(Integer roleId);
}