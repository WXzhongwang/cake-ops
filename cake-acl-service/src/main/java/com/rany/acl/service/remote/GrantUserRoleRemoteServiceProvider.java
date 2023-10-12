package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.GrantUserRoleCommand;
import com.rany.acl.api.facade.GrantUserRoleFacade;
import com.rany.acl.common.util.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class GrantUserRoleRemoteServiceProvider implements GrantUserRoleFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Boolean> grantUserRole(GrantUserRoleCommand grantUserRoleCommand) {
        return null;
    }
}
