package com.gupaoedu.live.gupaoedulive.modules.sys.model;

import com.gupaoedu.live.gupaoedulive.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_post")
public class SysPost  extends BaseEntity {
    /**
     * 岗位ID
     */
    @Id
    @Column(name = "post_id")
    private Long postId;

    /**
     * 岗位编码
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 岗位名称
     */
    @Column(name = "post_name")
    private String postName;

    /**
     * 显示顺序
     */
    @Column(name = "post_sort")
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取岗位ID
     *
     * @return post_id - 岗位ID
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 设置岗位ID
     *
     * @param postId 岗位ID
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * 获取岗位编码
     *
     * @return post_code - 岗位编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置岗位编码
     *
     * @param postCode 岗位编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 获取岗位名称
     *
     * @return post_name - 岗位名称
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 设置岗位名称
     *
     * @param postName 岗位名称
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * 获取显示顺序
     *
     * @return post_sort - 显示顺序
     */
    public Integer getPostSort() {
        return postSort;
    }

    /**
     * 设置显示顺序
     *
     * @param postSort 显示顺序
     */
    public void setPostSort(Integer postSort) {
        this.postSort = postSort;
    }

    /**
     * 获取状态（0正常 1停用）
     *
     * @return status - 状态（0正常 1停用）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0正常 1停用）
     *
     * @param status 状态（0正常 1停用）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新者
     *
     * @return update_by - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}