package com.gupaoedu.live.gupaoedulive.modules.sys.model;


import com.gupaoedu.live.gupaoedulive.core.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 路径
     */
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序号
     */
    private Byte sort;

    /**
     * 是否显示  0显示 1不显示
     */
    @Column(name = "is_show")
    private Byte isShow;

    private String permission;

    private String menuType;

    private String isSysMenu;//是否是系统菜单 0 是  1不是  系统菜单不可删除

    private String isLink; //是否外链

    @Transient
    private boolean open=true;

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取路径
     *
     * @return url - 路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置路径
     *
     * @param url 路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序号
     *
     * @return sort - 排序号
     */
    public Byte getSort() {
        return sort;
    }

    /**
     * 设置排序号
     *
     * @param sort 排序号
     */
    public void setSort(Byte sort) {
        this.sort = sort;
    }

    /**
     * 获取是否显示  0显示 1不显示
     *
     * @return is_show - 是否显示  0显示 1不显示
     */
    public Byte getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示  0显示 1不显示
     *
     * @param isShow 是否显示  0显示 1不显示
     */
    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getIsSysMenu() {
        return isSysMenu;
    }

    public void setIsSysMenu(String isSysMenu) {
        this.isSysMenu = isSysMenu;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getIsLink() {
        return isLink;
    }

    public void setIsLink(String isLink) {
        this.isLink = isLink;
    }
}