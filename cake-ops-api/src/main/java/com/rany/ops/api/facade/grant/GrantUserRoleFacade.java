package com.rany.ops.api.facade.grant;

import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.grant.DisGrantUserRoleCommand;
import com.rany.ops.api.command.grant.GrantUserRoleCommand;

/**
 * 用户角色授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantUserRoleFacade {

    /**
     * 用户角色授权
     *
     * @param grantUserRoleCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantUserRole(GrantUserRoleCommand grantUserRoleCommand);


    /**
     * 解除用户角色授权
     *
     * @param disGrantUserRoleCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantUserRole(DisGrantUserRoleCommand disGrantUserRoleCommand);
}
