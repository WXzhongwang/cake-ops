package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.UserRolePO;

public interface UserRolePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int insert(UserRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int insertSelective(UserRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    UserRolePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int updateByPrimaryKeySelective(UserRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int updateByPrimaryKey(UserRolePO record);
}