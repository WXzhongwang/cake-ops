package com.rany.ops.infra.po;

import java.util.Date;

public class ApplicationPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.app_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.auth_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String authType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column application.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.id
     *
     * @return the value of application.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.id
     *
     * @param id the value for application.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.app_name
     *
     * @return the value of application.app_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.app_name
     *
     * @param appName the value for application.app_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.app_code
     *
     * @return the value of application.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.app_code
     *
     * @param appCode the value for application.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.auth_type
     *
     * @return the value of application.auth_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.auth_type
     *
     * @param authType the value for application.auth_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.creator
     *
     * @return the value of application.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.creator
     *
     * @param creator the value for application.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.modifier
     *
     * @return the value of application.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.modifier
     *
     * @param modifier the value for application.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.gmt_create
     *
     * @return the value of application.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.gmt_create
     *
     * @param gmtCreate the value for application.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.gmt_modified
     *
     * @return the value of application.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.gmt_modified
     *
     * @param gmtModified the value for application.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.is_deleted
     *
     * @return the value of application.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.is_deleted
     *
     * @param isDeleted the value for application.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column application.status
     *
     * @return the value of application.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column application.status
     *
     * @param status the value for application.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}