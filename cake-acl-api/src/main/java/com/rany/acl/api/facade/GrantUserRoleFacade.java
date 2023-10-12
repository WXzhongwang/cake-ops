package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.GrantUserRoleCommand;

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
     * 用户角色授权是否成功
     *
     * @param grantUserRoleCommand
     * @return
     */
    PojoResult<Boolean> grantUserRole(GrantUserRoleCommand grantUserRoleCommand);
}
