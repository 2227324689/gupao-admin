package com.gupaoedu.live.gupaoedulive.modules.sys.service;


import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleSelectVo;

import java.util.List;

public interface RoleService {

    List<Integer> findByUserId(Integer id);

    List<SysRole> getAll(SysRole sysRole, String keyword);

    void batchDelete(Integer[] ids);

    void saveOrUpdate(SysRole sysUser);

    SysRole getById(Integer id);

    void deleteById(Integer id);

    List<SysRoleSelectVo> getAllRoles();
}