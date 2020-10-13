package com.gupaoedu.live.gupaoedulive.modules.sys.service;


import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;

import java.util.List;

public interface UserService {
    List<SysUser> getAll(SysUser sysUser, String keyword);

    SysUser getById(Integer id);

    int deleteById(Integer id);

    int save(SysUser sysUser);

    SysUser getUser(SysUser sysUser);

    ProcessResult showVerify(String email);

    ProcessResult saveUser(SysUser vo);

    void saveOrUpdate(SysUser sysUser);

    void batchDelete(Integer[] list);

    SysUser findByUserName(String username);

    void saveAvatar(SysUser sysUser);

    List<SysUser> selectUserListByRoleId(Integer roleId);
}