package com.gupaoedu.live.gupaoedulive.modules.sys.model;


import com.gupaoedu.live.gupaoedulive.core.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "bsc_dic_code_item")
public class BscDicCodeItem extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型ID
     */
    @Column(name = "TYPE_ID")
    private Integer typeId;

    /**
     * 编号
     */
    @Column(name = "ITEM_CODE")
    private String itemCode;

    /**
     * 字典名称
     */
    @Column(name = "ITEM_NAME")
    private String itemName;

    /**
     * 是否激活
     */
    @Column(name = "IS_ACTIVE")
    private String isActive;

    /**
     * 排序号
     */
    @Column(name = "SORT_NO")
    private Integer sortNo;


    /**
     * 备注
     */
    @Column(name = "ITEM_MEMO")
    private String itemMemo;

    /**
     * @return ID
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
     * 获取类型ID
     *
     * @return TYPE_ID - 类型ID
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型ID
     *
     * @param typeId 类型ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取编号
     *
     * @return ITEM_CODE - 编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置编号
     *
     * @param itemCode 编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取字典名称
     *
     * @return ITEM_NAME - 字典名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置字典名称
     *
     * @param itemName 字典名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取是否激活
     *
     * @return IS_ACTIVE - 是否激活
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取排序号
     *
     * @return SORT_NO - 排序号
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * 设置排序号
     *
     * @param sortNo 排序号
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    /**
     * 获取备注
     *
     * @return ITEM_MEMO - 备注
     */
    public String getItemMemo() {
        return itemMemo;
    }

    /**
     * 设置备注
     *
     * @param itemMemo 备注
     */
    public void setItemMemo(String itemMemo) {
        this.itemMemo = itemMemo;
    }
}