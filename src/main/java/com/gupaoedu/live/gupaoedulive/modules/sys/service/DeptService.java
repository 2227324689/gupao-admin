package com.gupaoedu.live.gupaoedulive.modules.sys.service;

import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDept;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysDeptSelectVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysDeptVo;

import java.util.List;

public interface DeptService {

    List<SysDept> getAll();

    List<SysDeptVo> treeList();

    List<SysDeptSelectVo> treeSelectList();

    SysDept getDeptById(Integer id);

    void saveOrUpdate(SysDept sysDept) throws Exception;

    void deleteById(Integer id);

}