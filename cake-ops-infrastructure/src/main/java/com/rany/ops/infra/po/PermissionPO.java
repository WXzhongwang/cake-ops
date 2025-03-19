package com.rany.ops.infra.po;

import java.util.Date;

public class PermissionPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.resource_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String resourceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.resource_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String resourceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.resource_path
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String resourcePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.ref_menu_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private Long refMenuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acl_permission.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.id
     *
     * @return the value of acl_permission.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.id
     *
     * @param id the value for acl_permission.id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.app_code
     *
     * @return the value of acl_permission.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.app_code
     *
     * @param appCode the value for acl_permission.app_code
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.tenant_id
     *
     * @return the value of acl_permission.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.tenant_id
     *
     * @param tenantId the value for acl_permission.tenant_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.resource_type
     *
     * @return the value of acl_permission.resource_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.resource_type
     *
     * @param resourceType the value for acl_permission.resource_type
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.resource_name
     *
     * @return the value of acl_permission.resource_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.resource_name
     *
     * @param resourceName the value for acl_permission.resource_name
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.resource_path
     *
     * @return the value of acl_permission.resource_path
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.resource_path
     *
     * @param resourcePath the value for acl_permission.resource_path
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.creator
     *
     * @return the value of acl_permission.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.creator
     *
     * @param creator the value for acl_permission.creator
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.modifier
     *
     * @return the value of acl_permission.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.modifier
     *
     * @param modifier the value for acl_permission.modifier
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.gmt_create
     *
     * @return the value of acl_permission.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.gmt_create
     *
     * @param gmtCreate the value for acl_permission.gmt_create
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.gmt_modified
     *
     * @return the value of acl_permission.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.gmt_modified
     *
     * @param gmtModified the value for acl_permission.gmt_modified
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.is_deleted
     *
     * @return the value of acl_permission.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.is_deleted
     *
     * @param isDeleted the value for acl_permission.is_deleted
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.ref_menu_id
     *
     * @return the value of acl_permission.ref_menu_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public Long getRefMenuId() {
        return refMenuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.ref_menu_id
     *
     * @param refMenuId the value for acl_permission.ref_menu_id
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setRefMenuId(Long refMenuId) {
        this.refMenuId = refMenuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acl_permission.status
     *
     * @return the value of acl_permission.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acl_permission.status
     *
     * @param status the value for acl_permission.status
     *
     * @mbggenerated Wed Mar 19 22:48:19 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}