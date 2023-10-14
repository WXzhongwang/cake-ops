package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.acl.api.command.grant.GrantRoleMenusCommand;
import com.rany.acl.api.facade.GrantRoleMenuFacade;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.service.RoleDomainService;
import com.rany.acl.domain.service.RoleMenuDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class GrantRoleMenuRemoteServiceProvider implements GrantRoleMenuFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final RoleMenuDomainService roleMenuDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    public PojoResult<Boolean> grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand) {

        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand) {
        return PojoResult.succeed(Boolean.TRUE);
    }
}
