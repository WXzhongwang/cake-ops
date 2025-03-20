package com.rany.ops.infra.po;

import java.util.Date;

public class TenantPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.isv_id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private Long isvId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.short_name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String shortName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.email
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.source
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.phone
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.is_deleted
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.creator
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.modifier
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.gmt_create
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.gmt_modified
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.address
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant.status
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.id
     *
     * @return the value of tenant.id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.id
     *
     * @param id the value for tenant.id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.isv_id
     *
     * @return the value of tenant.isv_id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public Long getIsvId() {
        return isvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.isv_id
     *
     * @param isvId the value for tenant.isv_id
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setIsvId(Long isvId) {
        this.isvId = isvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.name
     *
     * @return the value of tenant.name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.name
     *
     * @param name the value for tenant.name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.short_name
     *
     * @return the value of tenant.short_name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.short_name
     *
     * @param shortName the value for tenant.short_name
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.email
     *
     * @return the value of tenant.email
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.email
     *
     * @param email the value for tenant.email
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.source
     *
     * @return the value of tenant.source
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.source
     *
     * @param source the value for tenant.source
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.phone
     *
     * @return the value of tenant.phone
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.phone
     *
     * @param phone the value for tenant.phone
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.is_deleted
     *
     * @return the value of tenant.is_deleted
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.is_deleted
     *
     * @param isDeleted the value for tenant.is_deleted
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.creator
     *
     * @return the value of tenant.creator
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.creator
     *
     * @param creator the value for tenant.creator
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.modifier
     *
     * @return the value of tenant.modifier
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.modifier
     *
     * @param modifier the value for tenant.modifier
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.gmt_create
     *
     * @return the value of tenant.gmt_create
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.gmt_create
     *
     * @param gmtCreate the value for tenant.gmt_create
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.gmt_modified
     *
     * @return the value of tenant.gmt_modified
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.gmt_modified
     *
     * @param gmtModified the value for tenant.gmt_modified
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.address
     *
     * @return the value of tenant.address
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.address
     *
     * @param address the value for tenant.address
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant.status
     *
     * @return the value of tenant.status
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant.status
     *
     * @param status the value for tenant.status
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}