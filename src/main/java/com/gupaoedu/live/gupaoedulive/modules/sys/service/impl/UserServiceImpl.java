
package com.gupaoedu.live.gupaoedulive.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.util.MD5Utils;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysRoleMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserRoleMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenu;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUserRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.MailService;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.MenuService;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MailService mailService;

    @Override
    public List<SysUser> getAll(SysUser sysUser, String keyword) {
        PageHelper.startPage(sysUser.getPage(),sysUser.getLimit());
        Map<String,Object> map=new HashMap<>();
        map.put("deptId",sysUser.getDeptId());
        map.put("keyword",keyword);
        return sysUserMapper.selectUserByDeptId(map);
    }

    @Override
    public SysUser getById(Integer id) {
        return sysUserMapper.selectByPK(id);
    }

    @Override
    public int deleteById(Integer id) {
        SysUser user =sysUserMapper.selectByPK(id);
        if(user!=null && "0".equals(user.getIsSysUser())){
            throw new RuntimeException("系统用户无法被删除");
        }
        //删除用户关联的角色
        Example example=new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("userId",id);
        sysUserRoleMapper.deleteByExample(example);
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysUser sysUser) {
        if (sysUser.getId() != null) {
            return sysUserMapper.updateByPrimaryKey(sysUser);
        } else {
            return sysUserMapper.insert(sysUser);
        }
    }

    @Override
    public SysUser getUser(SysUser sysUser) {
        return sysUserMapper.selectOne(sysUser);
    }

    @Override
    //@Async
    public ProcessResult showVerify(String email) {
        ProcessResult result=new ProcessResult();
        String str = mailService.registerNotify(email);
        if("".equals(str.trim())){
            result.setResultStat(ProcessResult.ERROR);
            result.setMess("请求验证码失败！");
        }else{
            result.setMess("获取验证码成功，请登陆邮箱"+email+"查看");
            result.setResultStat(ProcessResult.SUCCESS);
        }
        return result;
    }

    @Override
    public ProcessResult saveUser(SysUser vo) {
        ProcessResult processResult=new ProcessResult();
        String ver=vo.getVerify();
        String verify=mailService.getEmailVerify(vo.getEmail());
        if(!ver.equalsIgnoreCase(verify)){
            processResult.setResultStat(ProcessResult.ERROR);
            processResult.setMess("验证码输入错误！");
            return processResult;
        }
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setStatus(1);
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(MD5Utils.md5(vo.getPassword()));
        int i=sysUserMapper.insertSelective(sysUser);
        processResult.setMess("注册成功");
        processResult.setResultStat(ProcessResult.SUCCESS);
        processResult.setData(i);
        return processResult;
    }

    @Override
    public void saveOrUpdate(SysUser sysUser) {
        if(sysUser.getId()==null){
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(MD5Utils.md5("123456"));//初始密码
            sysUser.setIsSysUser("1");
            sysUserMapper.insert(sysUser);
        }else{
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
            }
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
            Example example=new Example(SysUserRole.class);
            example.createCriteria().andEqualTo("userId",sysUser.getId());
            sysUserRoleMapper.deleteByExample(example);
        }
        for (int i = 0; i < sysUser.getRoleIds().length; i++) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(sysUser.getRoleIds()[i]);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }

    @Override
    public void batchDelete(Integer[] list) {
        for (Integer id:list){
            SysUser sysUser=new SysUser();
            sysUser.setId(id);
            sysUserMapper.delete(sysUser);
        }
    }



    @Override
    public SysUser findByUserName(String username) {
        SysUser sysUser=new SysUser();
        sysUser.setUsername(username);
        SysUser user = sysUserMapper.selectOne(sysUser);
        if(user!=null){
            List<SysRole> roles = sysRoleMapper.findByUserId(user.getId());
            if(roles.size()>0){
                //本项目暂为每个用户只具有一个角色
                Integer roleId = roles.get(0).getId();
                Set<SysMenu> menus =new HashSet<SysMenu>(menuService.getSelMenuPermission(roleId));
                roles.get(0).setResources(menus);
            }
            user.setRoles(new HashSet<SysRole>(roles));
        }
        return user;
    }

    @Override
    public void saveAvatar(SysUser sysUser){
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public List<SysUser> selectUserListByRoleId(Integer roleId) {
        return sysUserMapper.selectUserByRoleId(roleId);
    }


}
