package com.rany.ops.infra.po;

import java.util.Date;

public class IsvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.id
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.short_name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String shortName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.status
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.phone
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.email
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.country
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.url
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.register_ip
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String registerIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.creator
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.modifier
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.gmt_create
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.gmt_modified
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.is_deleted
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.max_tenants
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private Integer maxTenants;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column isv.address
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    private String address;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.id
     *
     * @return the value of isv.id
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.id
     *
     * @param id the value for isv.id
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.name
     *
     * @return the value of isv.name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.name
     *
     * @param name the value for isv.name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.short_name
     *
     * @return the value of isv.short_name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.short_name
     *
     * @param shortName the value for isv.short_name
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.status
     *
     * @return the value of isv.status
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.status
     *
     * @param status the value for isv.status
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.phone
     *
     * @return the value of isv.phone
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.phone
     *
     * @param phone the value for isv.phone
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.email
     *
     * @return the value of isv.email
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.email
     *
     * @param email the value for isv.email
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.country
     *
     * @return the value of isv.country
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.country
     *
     * @param country the value for isv.country
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.url
     *
     * @return the value of isv.url
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.url
     *
     * @param url the value for isv.url
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.register_ip
     *
     * @return the value of isv.register_ip
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.register_ip
     *
     * @param registerIp the value for isv.register_ip
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.creator
     *
     * @return the value of isv.creator
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.creator
     *
     * @param creator the value for isv.creator
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.modifier
     *
     * @return the value of isv.modifier
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.modifier
     *
     * @param modifier the value for isv.modifier
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.gmt_create
     *
     * @return the value of isv.gmt_create
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.gmt_create
     *
     * @param gmtCreate the value for isv.gmt_create
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.gmt_modified
     *
     * @return the value of isv.gmt_modified
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.gmt_modified
     *
     * @param gmtModified the value for isv.gmt_modified
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.is_deleted
     *
     * @return the value of isv.is_deleted
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.is_deleted
     *
     * @param isDeleted the value for isv.is_deleted
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.max_tenants
     *
     * @return the value of isv.max_tenants
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public Integer getMaxTenants() {
        return maxTenants;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.max_tenants
     *
     * @param maxTenants the value for isv.max_tenants
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setMaxTenants(Integer maxTenants) {
        this.maxTenants = maxTenants;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column isv.address
     *
     * @return the value of isv.address
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column isv.address
     *
     * @param address the value for isv.address
     *
     * @mbggenerated Tue Dec 17 10:31:03 CST 2024
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}