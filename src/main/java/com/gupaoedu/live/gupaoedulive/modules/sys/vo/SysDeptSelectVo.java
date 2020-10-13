package com.gupaoedu.live.gupaoedulive.modules.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Data
public class SysDeptSelectVo {

    private Integer id;
    private String name;
    private boolean open;
    private List<SysDeptSelectVo> children;

}
