package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.ApplicationPO;

public interface ApplicationPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int insert(ApplicationPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int insertSelective(ApplicationPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    ApplicationPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int updateByPrimaryKeySelective(ApplicationPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table application
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int updateByPrimaryKey(ApplicationPO record);
}