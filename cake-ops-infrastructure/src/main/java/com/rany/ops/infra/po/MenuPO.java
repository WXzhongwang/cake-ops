package com.rany.ops.infra.po;

import java.util.Date;

public class MenuPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String appCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.path
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.level
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Integer level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.icon
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.hidden
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String hidden;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.sort
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.menu_type
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    private String menuType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.id
     *
     * @return the value of menu.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.id
     *
     * @param id the value for menu.id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.app_code
     *
     * @return the value of menu.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.app_code
     *
     * @param appCode the value for menu.app_code
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.tenant_id
     *
     * @return the value of menu.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.tenant_id
     *
     * @param tenantId the value for menu.tenant_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.name
     *
     * @return the value of menu.name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.name
     *
     * @param name the value for menu.name
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.path
     *
     * @return the value of menu.path
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.path
     *
     * @param path the value for menu.path
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.parent_id
     *
     * @return the value of menu.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.parent_id
     *
     * @param parentId the value for menu.parent_id
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.level
     *
     * @return the value of menu.level
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.level
     *
     * @param level the value for menu.level
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.icon
     *
     * @return the value of menu.icon
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.icon
     *
     * @param icon the value for menu.icon
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.hidden
     *
     * @return the value of menu.hidden
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getHidden() {
        return hidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.hidden
     *
     * @param hidden the value for menu.hidden
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setHidden(String hidden) {
        this.hidden = hidden == null ? null : hidden.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.creator
     *
     * @return the value of menu.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.creator
     *
     * @param creator the value for menu.creator
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.modifier
     *
     * @return the value of menu.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.modifier
     *
     * @param modifier the value for menu.modifier
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.gmt_create
     *
     * @return the value of menu.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.gmt_create
     *
     * @param gmtCreate the value for menu.gmt_create
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.gmt_modified
     *
     * @return the value of menu.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.gmt_modified
     *
     * @param gmtModified the value for menu.gmt_modified
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.is_deleted
     *
     * @return the value of menu.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.is_deleted
     *
     * @param isDeleted the value for menu.is_deleted
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.sort
     *
     * @return the value of menu.sort
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.sort
     *
     * @param sort the value for menu.sort
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.status
     *
     * @return the value of menu.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.status
     *
     * @param status the value for menu.status
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.menu_type
     *
     * @return the value of menu.menu_type
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.menu_type
     *
     * @param menuType the value for menu.menu_type
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }
}