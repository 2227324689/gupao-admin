package com.gupaoedu.live.gupaoedulive.modules.sys.mapper;


import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.BscDicCodeType;

public interface BscDicCodeTypeMapper extends MyMapper<BscDicCodeType> {
    int myInsertUseGeneratedKeys(BscDicCodeType bscDicCodeType);
}