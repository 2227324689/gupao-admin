package com.gupaoedu.live.gupaoedulive.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysPostMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserMapper;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysPost;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUserRole;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    SysPostMapper sysPostMapper;

    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public List<SysPost> getAllWithPage(SysPost sysPost) {
        PageHelper.startPage(sysPost.getPage(),sysPost.getLimit());
        Example example=new Example(SysPost.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(sysPost.getPostCode())){
            criteria.andLike("postCode","%"+sysPost.getPostCode()+"%");
        }
        if(StringUtils.isNotBlank(sysPost.getPostName())){
            criteria.andLike("postName","%"+sysPost.getPostName()+"%");
        }
        if(StringUtils.isNotBlank(sysPost.getStatus())){
            criteria.andEqualTo("status",sysPost.getStatus());
        }
        example.orderBy("createTime").desc();
        return sysPostMapper.selectByExample(example);
    }

    @Override
    public List<SysPost> getAllPost() {
        Example example=new Example(SysPost.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",0);
        return sysPostMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(SysPost sysPost) {
        if(sysPost.getPostId()!=null){//update
            sysPostMapper.updateByPrimaryKeySelective(sysPost);
        }else{//insert
            sysPostMapper.insert(sysPost);
        }
    }

    @Override
    public SysPost getById(Long id) {
        return sysPostMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Long id) {
        Example example=new Example(SysUser.class);
        example.createCriteria().andEqualTo("postId",id);
        List<SysUser> list=sysUserMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            throw new RuntimeException("当前岗位存在关联用户,无法删除");
        }
        sysPostMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(Long[] list) {
        for (Long id:list){
            SysPost sysPost=new SysPost();
            sysPost.setPostId(id);
            sysPostMapper.delete(sysPost);
        }
    }
}
