package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.PermissionPO;

public interface PermissionPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int insert(PermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int insertSelective(PermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    PermissionPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int updateByPrimaryKeySelective(PermissionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acl_permission
     *
     * @mbggenerated Thu Mar 20 22:08:45 CST 2025
     */
    int updateByPrimaryKey(PermissionPO record);
}