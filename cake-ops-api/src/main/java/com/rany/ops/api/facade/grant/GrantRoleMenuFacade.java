package com.rany.ops.api.facade.grant;

import com.rany.ops.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.ops.api.command.grant.GrantRoleMenusCommand;

/**
 * 角色菜单授权
 *
 * @author zhongshengwang
 * @description 角色菜单授权
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantRoleMenuFacade {


    /**
     * 角色菜单绑定
     *
     * @param grantRoleMenusCommand 授权请求
     * @return 是否成功
     */
    Boolean grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand);


    /**
     * 解除角色菜单绑定
     *
     * @param disGrantRoleMenusCommand 解除授权请求
     * @return 是否成功
     */
    Boolean disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand);
}
