package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRolePermissionsCommand;
import com.rany.acl.api.command.grant.GrantRolePermissionsCommand;

/**
 * 角色权限授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantRolePermissionFacade {

    /**
     * 角色权限绑定
     *
     * @param grantRolePermissionsCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantRolePermissions(GrantRolePermissionsCommand grantRolePermissionsCommand);


    /**
     * 解除角色权限绑定
     *
     * @param disGrantRolePermissionsCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantRolePermissions(DisGrantRolePermissionsCommand disGrantRolePermissionsCommand);
}
