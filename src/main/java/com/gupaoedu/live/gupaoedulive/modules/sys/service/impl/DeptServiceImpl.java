
package com.gupaoedu.live.gupaoedulive.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysDeptMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysDeptUserMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDept;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysDeptUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.DeptService;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysDeptSelectVo;
import com.gupaoedu.live.gupaoedulive.modules.sys.vo.SysDeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author qjp
 * @since 2016-01-31 21:42
 */
@Service("deptService")
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysDeptUserMapper sysDeptUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysDept> getAll() {
        return sysDeptMapper.selectAll();
    }

    @Override
    public List<SysDeptVo> treeList() {
        List<SysDept> toplist=sysDeptMapper.ListTopDept();
        return convertVo(toplist);
    }

    @Override
    public List<SysDeptSelectVo> treeSelectList(){
        List<SysDept> toplist=sysDeptMapper.ListTopDept();
        return convertSelectVo(toplist);
    }

    @Override
    public SysDept getDeptById(Integer id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(SysDept sysDept) throws Exception {
        if(sysDept.getId()!=null){
            SysDept newParentDept=sysDeptMapper.selectDeptById(sysDept.getParentId());
            SysDept oldDept = sysDeptMapper.selectDeptById(sysDept.getId());
            if (newParentDept!=null && oldDept!=null){
                String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getId();
                String oldAncestors = oldDept.getAncestors();
                sysDept.setAncestors(newAncestors);
                updateDeptChildren(sysDept.getId(), newAncestors, oldAncestors);
            }
            sysDeptMapper.updateByPrimaryKey(sysDept);
        }else{
            SysDept query=new SysDept();
            query.setDeptName(sysDept.getDeptName());
            query.setParentId(sysDept.getParentId());
            List<SysDept> sysDepts = sysDeptMapper.select(query);
            if(sysDepts.size()>0){
                throw new Exception("部门【"+sysDept.getDeptName()+"】已存在");
            }
            SysDept dept=sysDeptMapper.selectDeptById(sysDept.getParentId());
            sysDept.setAncestors(dept.getAncestors()+","+sysDept.getParentId());
            sysDeptMapper.insert(sysDept);
        }
    }

    public void updateDeptChildren(Integer deptId, String newAncestors, String oldAncestors){
        List<SysDept> children = sysDeptMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children){
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0){
            sysDeptMapper.updateDeptChildren(children);
        }
    }
    @Override
    public void deleteById(Integer id) {
        sysDeptMapper.deleteByPrimaryKey(id);
    }



    private Map<String,Object> convertTree(List<SysDept> toplist) {
        Map<String,Object> map=new HashMap<>();
        map.put("data",convertVo(toplist));
        return map;
    }

    private List<SysDeptVo> convertVo(List<SysDept> toplist){
        List<SysDeptVo> result=new ArrayList<>();
        if(toplist.size()>0){
            for (SysDept sysDept:toplist){
                SysDeptVo vo=new SysDeptVo();
                vo.setId(sysDept.getId());
                vo.setLabel(sysDept.getDeptName());
                vo.setSpread(true);
                List<SysDept> list=sysDeptMapper.getChildDeptList(sysDept.getId());
                if(list.size()>0){
                    vo.setChildren(convertVo(list));
                }
                result.add(vo);
            }
        }
        return result;
    }
    private List<SysDeptSelectVo> convertSelectVo(List<SysDept> toplist){
        List<SysDeptSelectVo> result=new ArrayList<>();
        if(toplist.size()>0){
            for (SysDept sysDept:toplist){
                SysDeptSelectVo vo=new SysDeptSelectVo();
                vo.setId(sysDept.getId());
                vo.setName(sysDept.getDeptName());
                vo.setOpen(true);
                List<SysDept> list=sysDeptMapper.getChildDeptList(sysDept.getId());
                if(list.size()>0){
                    vo.setChildren(convertSelectVo(list));
                }
                result.add(vo);
            }
        }
        return result;
    }
}
