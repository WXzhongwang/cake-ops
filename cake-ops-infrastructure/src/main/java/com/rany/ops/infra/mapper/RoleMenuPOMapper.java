package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.RoleMenuPO;

public interface RoleMenuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int insert(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int insertSelective(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    RoleMenuPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int updateByPrimaryKeySelective(RoleMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Wed Mar 26 09:52:05 CST 2025
     */
    int updateByPrimaryKey(RoleMenuPO record);
}