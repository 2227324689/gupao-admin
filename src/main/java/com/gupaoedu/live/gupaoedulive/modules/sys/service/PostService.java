package com.gupaoedu.live.gupaoedulive.modules.sys.service;

import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysPost;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface PostService {

    List<SysPost> getAllWithPage(SysPost sysPost);

    void saveOrUpdate(SysPost sysPost);

    SysPost getById(Long id);

    void deleteById(Long id);

    void batchDelete(Long[] list);

    List<SysPost> getAllPost();

}
