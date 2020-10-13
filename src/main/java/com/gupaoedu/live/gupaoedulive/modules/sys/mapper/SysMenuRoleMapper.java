package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;


import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenuRole;

public interface SysMenuRoleMapper extends MyMapper<SysMenuRole> {
    void deleteByRoleId(Integer roleId);
}