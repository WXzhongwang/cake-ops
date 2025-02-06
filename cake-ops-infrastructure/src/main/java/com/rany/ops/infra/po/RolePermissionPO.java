package com.rany.ops.infra.po;

import java.util.Date;

public class RolePermissionPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.app_code
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.tenant_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Long tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.role_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.permission_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Long permissionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.creator
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.modifier
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.gmt_create
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.gmt_modified
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_permission.id_deleted
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    private String idDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.id
     *
     * @return the value of role_permission.id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.id
     *
     * @param id the value for role_permission.id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.app_code
     *
     * @return the value of role_permission.app_code
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.app_code
     *
     * @param appCode the value for role_permission.app_code
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.tenant_id
     *
     * @return the value of role_permission.tenant_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.tenant_id
     *
     * @param tenantId the value for role_permission.tenant_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.role_id
     *
     * @return the value of role_permission.role_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.role_id
     *
     * @param roleId the value for role_permission.role_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.permission_id
     *
     * @return the value of role_permission.permission_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.permission_id
     *
     * @param permissionId the value for role_permission.permission_id
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.creator
     *
     * @return the value of role_permission.creator
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.creator
     *
     * @param creator the value for role_permission.creator
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.modifier
     *
     * @return the value of role_permission.modifier
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.modifier
     *
     * @param modifier the value for role_permission.modifier
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.gmt_create
     *
     * @return the value of role_permission.gmt_create
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.gmt_create
     *
     * @param gmtCreate the value for role_permission.gmt_create
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.gmt_modified
     *
     * @return the value of role_permission.gmt_modified
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.gmt_modified
     *
     * @param gmtModified the value for role_permission.gmt_modified
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_permission.id_deleted
     *
     * @return the value of role_permission.id_deleted
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public String getIdDeleted() {
        return idDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_permission.id_deleted
     *
     * @param idDeleted the value for role_permission.id_deleted
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    public void setIdDeleted(String idDeleted) {
        this.idDeleted = idDeleted == null ? null : idDeleted.trim();
    }
}