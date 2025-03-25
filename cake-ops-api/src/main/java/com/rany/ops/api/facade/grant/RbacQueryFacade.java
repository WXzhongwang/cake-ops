package com.rany.ops.api.facade.grant;

import com.rany.ops.api.query.grant.*;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.dto.permission.PermissionDTO;

import java.util.List;

/**
 * 查询用户角色权限模型接口
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface RbacQueryFacade {


    /**
     * 获取单一用户应用下权限集合
     *
     * @param query 查询条件
     * @return 单一用户应用下权限集合
     */
    UserRoleMenuDTO getUserRbacModel(UserRoleMenuPermissionQuery query);


    /**
     * 单一角色菜单权限集合
     *
     * @param query 查询条件
     * @return 单一角色菜单权限集合
     */
    List<MenuTreeDTO> getRoleMenuPermissions(RoleMenuPermissionQuery query);

    /**
     * 单一角色菜单树查询
     *
     * @param query 查询条件
     * @return 单一角色菜单树查询
     */
    List<MenuTreeDTO> getRoleMenus(RoleMenuTreeQuery query);

    /**
     * 单一角色菜单列表查询
     *
     * @param query 查询条件
     * @return 菜单列表
     */
    List<MenuDTO> listRoleMenus(RoleMenuListQuery query);


    /**
     * 单一角色权限列表查询
     *
     * @param query 查询条件
     * @return 菜单列表
     */
    List<PermissionDTO> listRolePermissions(RolePermissionListQuery query);
}
