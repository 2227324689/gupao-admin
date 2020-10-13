
package com.gupaoedu.live.gupaoedulive.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysRoleMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserRoleMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUserRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.RoleService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysRoleSelectVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<Integer> findByUserId(Integer userId) {
        List<SysRole> sysRoles=sysRoleMapper.findByUserId(userId);
        if(sysRoles!=null&&!sysRoles.isEmpty()) {
            List<Integer> roleIds = sysRoles.stream().map(sysRole -> sysRole.getId()).collect(Collectors.toList());
            return  roleIds;
        }
        return null;
    }

    @Override
    public List<SysRole> getAll(SysRole sysRole, String keyword) {
        PageHelper.startPage(sysRole.getPage(),sysRole.getLimit());
        Example example=new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(keyword)){
            keyword="%"+keyword+"%";
            criteria.andLike("roleName",keyword);
            criteria.orLike("remark",keyword);
        }
        example.orderBy("roleType");
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public void batchDelete(Integer[] ids) {
        if(ids.length>0){
            for (Integer id:ids){
                SysRole sysRole=new SysRole();
                sysRole.setId(id);
                sysRoleMapper.delete(sysRole);
            }
        }
    }

    @Override
    public void saveOrUpdate(SysRole sysRole) {
        if(sysRole.getId()!=null){//update
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        }else{//insert
            sysRole.setCreateTime(new Date());
            sysRole.setRoleType(Byte.valueOf("1"));
            sysRoleMapper.insert(sysRole);
        }
    }

    @Override
    public SysRole getById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        Example example=new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("roleId",id);
        List<SysUserRole> list=sysUserRoleMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            throw new RuntimeException("当前角色存在关联用户,无法删除");
        }
        sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysRoleSelectVo> getAllRoles() {
        Example example=new Example(SysRole.class);
        example.createCriteria().andEqualTo("status",0);
        List<SysRole> sysRoles=sysRoleMapper.selectByExample(example);
        return convertToRoleSelectVo(sysRoles);
    }

    private List<SysRoleSelectVo> convertToRoleSelectVo(List<SysRole> sysRoles){
        List<SysRoleSelectVo> sysRoleSelectVos=new ArrayList<>();
        sysRoles.parallelStream().forEach(sysRole -> {
            SysRoleSelectVo srsv=new SysRoleSelectVo();
            srsv.setName(sysRole.getRoleName());
            srsv.setValue(sysRole.getId());
            sysRoleSelectVos.add(srsv);
        });
        return sysRoleSelectVos;
    }
}
