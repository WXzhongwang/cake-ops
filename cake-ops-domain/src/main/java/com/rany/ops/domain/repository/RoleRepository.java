package com.rany.ops.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.params.*;
import com.rany.ops.domain.aggregate.Role;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.pk.RoleId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface RoleRepository extends Repository<Role, RoleId> {

    /**
     * 角色更新
     *
     * @param role
     * @return
     */
    int update(Role role);


    Role findByRoleKey(String appCode, Long tenantId, String roleKey);

    /**
     * 按应用搜索角色列表，全部层级平铺
     *
     * @param roleSearchParam
     * @return
     */
    List<RoleDTO> findRoles(RoleSearchParam roleSearchParam);

    /**
     * 查询下级菜单列表，包含未启用的
     *
     * @param subRoleSearchParam
     * @return
     */
    List<Role> findSubRoles(SubRoleSearchParam subRoleSearchParam);

    /**
     * 查询单个用户角色
     *
     * @param userRoleSearchParam
     * @return
     */
    UserRole findUserRole(UserRoleSearchParam userRoleSearchParam);

    /**
     * 查询一组用户角色
     *
     * @param userRoleSearchParam
     * @return
     */
    List<UserRole> findUserRoles(UserRoleSearchParam userRoleSearchParam);


    /**
     * 查询一组用户角色
     *
     * @param roleMenuSearchParam
     * @return
     */
    List<RoleMenu> findRoleMenus(RoleMenuSearchParam roleMenuSearchParam);


    /**
     * 查询一组角色权限点关系
     *
     * @param rolePermissionSearchParam
     * @return
     */
    List<RolePermission> findRolePermissions(RolePermissionSearchParam rolePermissionSearchParam);


    /**
     * 保存单条用户角色关系
     *
     * @param userRole
     * @return
     */
    Boolean saveUserRole(UserRole userRole);

    /**
     * 更新单条用户角色关系
     *
     * @param userRole
     * @return
     */
    Boolean updateUserRole(UserRole userRole);


    /**
     * 保存单条角色菜单关系
     *
     * @param roleMenu
     * @return
     */
    Boolean saveRoleMenu(RoleMenu roleMenu);


    /**
     * 更新单条角色菜单关系
     *
     * @param roleMenu
     * @return
     */
    Boolean updateRoleMenu(RoleMenu roleMenu);


    /**
     * 保存单条角色权限点关系
     *
     * @param rolePermission
     * @return
     */
    Boolean saveRolePermission(RolePermission rolePermission);


    /**
     * 更新单条角色权限点关系
     *
     * @param rolePermission
     * @return
     */
    Boolean updateRolePermission(RolePermission rolePermission);
}
