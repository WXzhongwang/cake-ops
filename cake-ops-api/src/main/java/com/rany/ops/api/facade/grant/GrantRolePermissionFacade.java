package com.rany.ops.api.facade.grant;

import com.rany.ops.api.command.grant.DisGrantRolePermissionsCommand;
import com.rany.ops.api.command.grant.GrantRolePermissionsCommand;
import com.rany.ops.api.command.grant.GrantRolePermissionsV2Command;

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
    Boolean grantRolePermissions(GrantRolePermissionsCommand grantRolePermissionsCommand);


    /**
     * 角色权限绑定
     *
     * @param grantRolePermissionsCommand 授权请求
     * @return 是否成功
     */
    Boolean grantRolePermissionsV2(GrantRolePermissionsV2Command grantRolePermissionsCommand);


    /**
     * 解除角色权限绑定
     *
     * @param disGrantRolePermissionsCommand 解除授权请求
     * @return 是否成功
     */
    Boolean disGrantRolePermissions(DisGrantRolePermissionsCommand disGrantRolePermissionsCommand);
}
