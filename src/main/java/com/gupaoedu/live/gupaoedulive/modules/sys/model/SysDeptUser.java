package com.gupaoedu.live.gupaoedulive.modules.sys.model;

import javax.persistence.*;

@Table(name = "sys_dept_user")
public class SysDeptUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 是否是主管 0是 1否
     */
    private Integer ismaster;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return dept_id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取是否是主管 0是 1否
     *
     * @return ismaster - 是否是主管 0是 1否
     */
    public Integer getIsmaster() {
        return ismaster;
    }

    /**
     * 设置是否是主管 0是 1否
     *
     * @param ismaster 是否是主管 0是 1否
     */
    public void setIsmaster(Integer ismaster) {
        this.ismaster = ismaster;
    }
}