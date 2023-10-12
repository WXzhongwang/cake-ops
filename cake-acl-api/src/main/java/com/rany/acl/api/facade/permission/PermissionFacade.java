package com.rany.acl.api.facade.permission;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.permission.*;
import com.rany.acl.api.query.permission.PermissionBasicQuery;
import com.rany.acl.common.dto.permission.PermissionDTO;

public interface PermissionFacade {


    /**
     * 创建权限
     *
     * @param createPermissionCommand
     * @return
     */
    PojoResult<Long> createPermission(CreatePermissionCommand createPermissionCommand);

    /**
     * 获取权限信息
     *
     * @param PermissionBasicQuery
     * @return
     */
    PojoResult<PermissionDTO> getPermission(PermissionBasicQuery PermissionBasicQuery);


    /**
     * 权限禁用
     *
     * @param disablePermissionCommand
     * @return
     */
    PojoResult<Boolean> disablePermission(DisablePermissionCommand disablePermissionCommand);

    /**
     * 启用权限
     *
     * @param enablePermissionCommand
     * @return
     */
    PojoResult<Boolean> enablePermission(EnablePermissionCommand enablePermissionCommand);

    /**
     * 删除权限
     *
     * @param deletePermissionCommand
     * @return
     */
    PojoResult<Boolean> deletePermission(DeletePermissionCommand deletePermissionCommand);

    /**
     * 更新权限基本信息
     *
     * @param modifyPermissionCommand
     * @return
     */
    PojoResult<Boolean> modifyPermission(ModifyPermissionCommand modifyPermissionCommand);
}

