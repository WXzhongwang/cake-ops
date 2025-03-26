package com.rany.ops.infra.po;

import java.util.Date;

public class RolePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.role_name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.role_key
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String roleKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.role_desc
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String roleDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_role.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.id
     *
     * @return the value of acl_role.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.id
     *
     * @param id the value for acl_role.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.app_code
     *
     * @return the value of acl_role.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.app_code
     *
     * @param appCode the value for acl_role.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.tenant_id
     *
     * @return the value of acl_role.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.tenant_id
     *
     * @param tenantId the value for acl_role.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.role_name
     *
     * @return the value of acl_role.role_name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.role_name
     *
     * @param roleName the value for acl_role.role_name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.role_key
     *
     * @return the value of acl_role.role_key
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.role_key
     *
     * @param roleKey the value for acl_role.role_key
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.role_desc
     *
     * @return the value of acl_role.role_desc
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.role_desc
     *
     * @param roleDesc the value for acl_role.role_desc
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.creator
     *
     * @return the value of acl_role.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.creator
     *
     * @param creator the value for acl_role.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.modifier
     *
     * @return the value of acl_role.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.modifier
     *
     * @param modifier the value for acl_role.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.gmt_create
     *
     * @return the value of acl_role.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.gmt_create
     *
     * @param gmtCreate the value for acl_role.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.gmt_modified
     *
     * @return the value of acl_role.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.gmt_modified
     *
     * @param gmtModified the value for acl_role.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.is_deleted
     *
     * @return the value of acl_role.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.is_deleted
     *
     * @param isDeleted the value for acl_role.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.parent_id
     *
     * @return the value of acl_role.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.parent_id
     *
     * @param parentId the value for acl_role.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_role.status
     *
     * @return the value of acl_role.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_role.status
     *
     * @param status the value for acl_role.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}