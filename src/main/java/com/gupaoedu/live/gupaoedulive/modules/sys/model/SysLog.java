package com.gupaoedu.live.gupaoedulive.modules.sys.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_log")
public class SysLog {
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * debug(10000),info(20000),warn(30000),error(40000)
     */
    @Column(name = "log_level")
    private Integer logLevel;

    private String url;

    @Column(name = "user_id")
    private Integer userId;

    private String username;

    @Column(name = "create_time")
    private Date createTime;

    private String result;

    /**
     * @return log_id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取debug(10000),info(20000),warn(30000),error(40000)
     *
     * @return log_level - debug(10000),info(20000),warn(30000),error(40000)
     */
    public Integer getLogLevel() {
        return logLevel;
    }

    /**
     * 设置debug(10000),info(20000),warn(30000),error(40000)
     *
     * @param logLevel debug(10000),info(20000),warn(30000),error(40000)
     */
    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }
}