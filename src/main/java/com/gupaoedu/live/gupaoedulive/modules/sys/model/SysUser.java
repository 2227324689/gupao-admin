package com.gupaoedu.live.gupaoedulive.modules.sys.model;


import com.gupaoedu.live.gupaoedulive.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer postId;

    private String username;

    private String password;

    @Column(name = "real_name")
    private String realName;

    /**
     * 1启用，0停用
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    private String email;

    private String phone;

    private Integer sex;

    private String avatar;

    private String city;//城市

    private Date birth;

    private String address;

    private String isSysUser;//是否系统用户 0 是 1 否 系统用户不可删除

    private Integer deptId; //部门id

    @Transient
    private Integer ismaster;//0 是管理员  1不是管理员

    @Transient
   /* @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })*/
    private Set<SysRole> roles;

    @Transient
    private Integer[] roleIds;

    @Transient
    private String verify;  //注册时，校验的验证码


    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取1启用，0停用
     *
     * @return status - 0启用，1停用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1启用，0停用
     *
     * @param status 0启用，1停用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsmaster() {
        return ismaster;
    }

    public void setIsmaster(Integer ismaster) {
        this.ismaster = ismaster;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public String getIsSysUser() {
        return isSysUser;
    }

    public void setIsSysUser(String isSysUser) {
        this.isSysUser = isSysUser;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}