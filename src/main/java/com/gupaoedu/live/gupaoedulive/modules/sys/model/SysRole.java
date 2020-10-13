package com.gupaoedu.live.gupaoedulive.modules.sys.model;


import com.gupaoedu.live.gupaoedulive.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "sys_role")
public class SysRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 角色类型
     *  0系统角色  1其他角色
     */
    @Column(name = "role_type")
    private Byte roleType;

    @Transient
   /* @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_menu_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })*/
    private Set<SysMenu> resources;

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
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SysRole setDefaultRole() {
        SysRole sysRole=new SysRole();
        sysRole.setRoleName("暂无角色");
        return sysRole;
    }

    public Set<SysMenu> getResources() {
        return resources;
    }

    public void setResources(Set<SysMenu> resources) {
        this.resources = resources;
    }
}