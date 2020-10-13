package com.gupaoedu.live.gupaoedulive.modules.sys.model;

import javax.persistence.*;

@Table(name = "sys_dict")
public class SysDict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据字典名称
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 数据字典值
     */
    @Column(name = "dict_value")
    private String dictValue;

    /**
     * 数据字典类型
     */
    @Column(name = "dict_level")
    private Integer dictLevel;

    /**
     * 数据字典状态
     */
    @Column(name = "dict_state")
    private Integer dictState;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序号
     */
    @Column(name = "dict_sort")
    private Integer dictSort;

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
     * 获取数据字典名称
     *
     * @return dict_name - 数据字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置数据字典名称
     *
     * @param dictName 数据字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取数据字典值
     *
     * @return dict_value - 数据字典值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 设置数据字典值
     *
     * @param dictValue 数据字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    /**
     * 获取数据字典类型
     *
     * @return dict_level - 数据字典类型
     */
    public Integer getDictLevel() {
        return dictLevel;
    }

    /**
     * 设置数据字典类型
     *
     * @param dictLevel 数据字典类型
     */
    public void setDictLevel(Integer dictLevel) {
        this.dictLevel = dictLevel;
    }

    /**
     * 获取数据字典状态
     *
     * @return dict_state - 数据字典状态
     */
    public Integer getDictState() {
        return dictState;
    }

    /**
     * 设置数据字典状态
     *
     * @param dictState 数据字典状态
     */
    public void setDictState(Integer dictState) {
        this.dictState = dictState;
    }

    /**
     * 获取父级ID
     *
     * @return parent_id - 父级ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序号
     *
     * @return dict_sort - 排序号
     */
    public Integer getDictSort() {
        return dictSort;
    }

    /**
     * 设置排序号
     *
     * @param dictSort 排序号
     */
    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }
}