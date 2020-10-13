package com.gupaoedu.live.gupaoedulive.modules.sys.vo;


import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysMenu;

import java.util.List;

public class SysMenuVo extends SysMenu {
    private List<SysMenuVo> children;

    public List<SysMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVo> children) {
        this.children = children;
    }
}
