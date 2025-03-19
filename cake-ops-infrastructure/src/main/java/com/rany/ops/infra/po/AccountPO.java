package com.rany.ops.infra.po;

import java.util.Date;

public class AccountPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.account_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String accountName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.phone
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.email
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.is_admin
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String isAdmin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.account_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String accountType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.last_login_ip
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String lastLoginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.last_login_time
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.feature
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String feature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.head_image
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String headImage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.dingding
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String dingding;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.qq
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String qq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.wechat
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String wechat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.birthday
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.tags
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String tags;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.ding_union_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String dingUnionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.ding_user_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String dingUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.work_no
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String workNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id
     *
     * @return the value of account.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id
     *
     * @param id the value for account.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.account_name
     *
     * @return the value of account.account_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.account_name
     *
     * @param accountName the value for account.account_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.phone
     *
     * @return the value of account.phone
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.phone
     *
     * @param phone the value for account.phone
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.email
     *
     * @return the value of account.email
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.email
     *
     * @param email the value for account.email
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.tenant_id
     *
     * @return the value of account.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.tenant_id
     *
     * @param tenantId the value for account.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.is_admin
     *
     * @return the value of account.is_admin
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.is_admin
     *
     * @param isAdmin the value for account.is_admin
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.account_type
     *
     * @return the value of account.account_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.account_type
     *
     * @param accountType the value for account.account_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.status
     *
     * @return the value of account.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.status
     *
     * @param status the value for account.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.is_deleted
     *
     * @return the value of account.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.is_deleted
     *
     * @param isDeleted the value for account.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.last_login_ip
     *
     * @return the value of account.last_login_ip
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.last_login_ip
     *
     * @param lastLoginIp the value for account.last_login_ip
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.last_login_time
     *
     * @return the value of account.last_login_time
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.last_login_time
     *
     * @param lastLoginTime the value for account.last_login_time
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.feature
     *
     * @return the value of account.feature
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getFeature() {
        return feature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.feature
     *
     * @param feature the value for account.feature
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.gmt_create
     *
     * @return the value of account.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.gmt_create
     *
     * @param gmtCreate the value for account.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.gmt_modified
     *
     * @return the value of account.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.gmt_modified
     *
     * @param gmtModified the value for account.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.head_image
     *
     * @return the value of account.head_image
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.head_image
     *
     * @param headImage the value for account.head_image
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.dingding
     *
     * @return the value of account.dingding
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getDingding() {
        return dingding;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.dingding
     *
     * @param dingding the value for account.dingding
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setDingding(String dingding) {
        this.dingding = dingding == null ? null : dingding.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.qq
     *
     * @return the value of account.qq
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getQq() {
        return qq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.qq
     *
     * @param qq the value for account.qq
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.wechat
     *
     * @return the value of account.wechat
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.wechat
     *
     * @param wechat the value for account.wechat
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.birthday
     *
     * @return the value of account.birthday
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.birthday
     *
     * @param birthday the value for account.birthday
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.tags
     *
     * @return the value of account.tags
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.tags
     *
     * @param tags the value for account.tags
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.ding_union_id
     *
     * @return the value of account.ding_union_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getDingUnionId() {
        return dingUnionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.ding_union_id
     *
     * @param dingUnionId the value for account.ding_union_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setDingUnionId(String dingUnionId) {
        this.dingUnionId = dingUnionId == null ? null : dingUnionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.ding_user_id
     *
     * @return the value of account.ding_user_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getDingUserId() {
        return dingUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.ding_user_id
     *
     * @param dingUserId the value for account.ding_user_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setDingUserId(String dingUserId) {
        this.dingUserId = dingUserId == null ? null : dingUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.work_no
     *
     * @return the value of account.work_no
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getWorkNo() {
        return workNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.work_no
     *
     * @param workNo the value for account.work_no
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo == null ? null : workNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.creator
     *
     * @return the value of account.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.creator
     *
     * @param creator the value for account.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.modifier
     *
     * @return the value of account.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.modifier
     *
     * @param modifier the value for account.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}