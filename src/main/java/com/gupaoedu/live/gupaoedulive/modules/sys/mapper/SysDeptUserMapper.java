package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;

import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDeptUser;
import org.apache.ibatis.annotations.Param;

public interface SysDeptUserMapper extends MyMapper<SysDeptUser> {
    void clearMasterByDept(@Param("userId") Integer userId, @Param("deptId") Integer deptId);

    void setMasterUserByDept(@Param("userId") Integer userId, @Param("deptId") Integer deptId);
}