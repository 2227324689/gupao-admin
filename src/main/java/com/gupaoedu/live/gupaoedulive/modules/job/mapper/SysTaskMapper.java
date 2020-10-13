package com.gupaoedu.live.gupaoedulive.modules.job.mapper;


import com.gupaoedu.live.gupaoedulive.core.mapper.MyMapper;
import com.gupaoedu.live.gupaoedulive.modules.job.model.SysTask;

public interface SysTaskMapper extends MyMapper<SysTask> {
    int batchRemove(Long[] ids);
}