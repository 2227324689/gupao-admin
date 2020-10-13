package com.gupaoedu.live.gupaoedulive.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gupaoedu.live.gupaoedulive.core.annotation.SysLog;
import com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult;
import com.gupaoedu.live.gupaoedulive.core.result.PageResult;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysPost;
import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;
import com.gupaoedu.live.gupaoedulive.modules.sys.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.gupaoedu.live.gupaoedulive.core.entity.ProcessResult.ERROR;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
@RequestMapping("/sys/position")
public class PositionController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("/modules/sys/sysPosition/list");
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public PageResult<SysPost> getAll(SysPost sysUser) {
        List<SysPost> postList = postService.getAllWithPage(sysUser);
        return new PageResult(new PageInfo<>(postList)).setCode(0);
    }

    @SysLog("保存岗位")
    @PostMapping(value = "/saveOrUpdate")
    public ProcessResult saveOrUpdate(SysPost sysPost, HttpServletRequest request) {
        try {
            SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
            if(sysPost.getPostId()!=null){
                sysPost.setUpdateTime(new Date());
                sysPost.setUpdateBy(sysUser.getUsername());
            }else{
                sysPost.setCreateBy(sysUser.getUsername());
                sysPost.setCreateTime(new Date());
            }
            postService.saveOrUpdate(sysPost);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

    @GetMapping(value = "/view/{id}")
    public ProcessResult view(@PathVariable Long id) {
        SysPost sysPost = postService.getById(id);
        ProcessResult processResult=new ProcessResult();
        processResult.setData(sysPost);
        return processResult;
    }

    @SysLog("删除岗位")
    @DeleteMapping(value = "/delete/{id}")
    public ProcessResult delete(@PathVariable Long id) {
        try {
            postService.deleteById(id);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }
    @GetMapping("/form")
    public ModelAndView form() {
        return new ModelAndView("/modules/sys/sysPosition/form");
    }

    @SysLog("批量删除用户")
    @DeleteMapping(value = "/batchDelete")
    public ProcessResult batchDelete(@RequestParam("ids[]") Long[] ids) {
        try {
            postService.batchDelete(ids);
            return new ProcessResult();
        }catch (Exception e){
            return new ProcessResult(ERROR,e.getMessage().toString());
        }
    }

}
