package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.RolePermissionPO;

public interface RolePermissionPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    int insert(RolePermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    int insertSelective(RolePermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    RolePermissionPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    int updateByPrimaryKeySelective(RolePermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbggenerated Tue Mar 25 21:33:20 CST 2025
     */
    int updateByPrimaryKey(RolePermissionPO record);
}