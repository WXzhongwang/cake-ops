package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.TenantPO;

public interface TenantPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int insert(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int insertSelective(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    TenantPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int updateByPrimaryKeySelective(TenantPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenant
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int updateByPrimaryKey(TenantPO record);
}