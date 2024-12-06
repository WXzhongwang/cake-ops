package com.rany.ops.api.facade.permission;

import com.rany.ops.api.command.permission.*;
import com.rany.ops.api.query.permission.MenuPermissionQuery;
import com.rany.ops.api.query.permission.PermissionBasicQuery;
import com.rany.ops.common.dto.permission.PermissionDTO;

import java.util.List;


/**
 * 权限点管理，权限点关联页面
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface PermissionFacade {


    /**
     * 创建权限
     *
     * @param createPermissionCommand 创建权限
     * @return 权限ID
     */
    Long createPermission(CreatePermissionCommand createPermissionCommand);

    /**
     * 获取权限信息
     *
     * @param permissionBasicQuery 权限点查询
     * @return 权限信息
     */
    PermissionDTO getPermission(PermissionBasicQuery permissionBasicQuery);


    /**
     * 获取菜单下的权限信息
     *
     * @param menuPermissionQuery 权限点查询
     * @return 权限信息
     */
    List<PermissionDTO> listMenuPermission(MenuPermissionQuery menuPermissionQuery);


    /**
     * 权限禁用
     *
     * @param disablePermissionCommand 权限禁用
     * @return Boolean
     */
    Boolean disablePermission(DisablePermissionCommand disablePermissionCommand);

    /**
     * 启用权限
     *
     * @param enablePermissionCommand 启用
     * @return Boolean
     */
    Boolean enablePermission(EnablePermissionCommand enablePermissionCommand);

    /**
     * 删除权限
     *
     * @param deletePermissionCommand 启用
     * @return Boolean
     */
    Boolean deletePermission(DeletePermissionCommand deletePermissionCommand);

    /**
     * 更新权限基本信息
     *
     * @param modifyPermissionCommand 更新
     * @return Boolean
     */
    Boolean modifyPermission(ModifyPermissionCommand modifyPermissionCommand);
}

