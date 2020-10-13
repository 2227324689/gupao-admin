package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;


import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {
    List<SysRole> findByUserId(Integer userId);
}