package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;

import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SysDictMapper extends MyMapper<SysDict> {
    SysDict getDictValueChild(@Param("param") Map<String, Object> param);
}