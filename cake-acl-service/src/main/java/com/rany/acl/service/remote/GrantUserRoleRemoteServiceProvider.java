package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantUserRoleCommand;
import com.rany.acl.api.command.grant.GrantUserRoleCommand;
import com.rany.acl.api.facade.GrantUserRoleFacade;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.exception.enums.BusinessErrorMessage;
import com.rany.acl.common.params.UserRoleSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.entity.UserRole;
import com.rany.acl.domain.pk.RoleId;
import com.rany.acl.domain.service.RoleDomainService;
import com.rany.acl.domain.service.UserRoleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Service
@AllArgsConstructor
public class GrantUserRoleRemoteServiceProvider implements GrantUserRoleFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final UserRoleDomainService userRoleDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    public PojoResult<Boolean> grantUserRole(GrantUserRoleCommand grantUserRoleCommand) {
        UserRoleSearchParam userRoleSearchParam = new UserRoleSearchParam();
        userRoleSearchParam.setUserId(userRoleSearchParam.getUserId());
        userRoleSearchParam.setAppCode(userRoleSearchParam.getAppCode());
        userRoleSearchParam.setRoleId(userRoleSearchParam.getRoleId());
        if (grantUserRoleCommand.getTenantId() != null) {
            userRoleSearchParam.setTenantId(userRoleSearchParam.getTenantId());
        }
        UserRole current = userRoleDomainService.getUserRole(userRoleSearchParam);
        if (current != null) {
            throw new BusinessException(BusinessErrorMessage.USER_ROLE_EXIST);
        }
        Role role = roleDomainService.findById(new RoleId(userRoleSearchParam.getRoleId()));
        if (role == null) {
            throw new BusinessException(BusinessErrorMessage.ROLE_NOT_FOUND);
        }
        if (StringUtils.equals(role.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DISABLED);
        }
        if (StringUtils.equals(role.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DELETED);
        }
        UserRole userRole = new UserRole(userRoleSearchParam.getAppCode(), userRoleSearchParam.getTenantId(),
                userRoleSearchParam.getUserId(), userRoleSearchParam.getRoleId());
        userRoleDomainService.save(userRole);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> disGrantUserRole(DisGrantUserRoleCommand disGrantUserRoleCommand) {
        UserRoleSearchParam userRoleSearchParam = new UserRoleSearchParam();
        userRoleSearchParam.setUserId(userRoleSearchParam.getUserId());
        userRoleSearchParam.setAppCode(userRoleSearchParam.getAppCode());
        userRoleSearchParam.setRoleId(userRoleSearchParam.getRoleId());
        if (disGrantUserRoleCommand.getTenantId() != null) {
            userRoleSearchParam.setTenantId(userRoleSearchParam.getTenantId());
        }
        UserRole userRole = userRoleDomainService.getUserRole(userRoleSearchParam);
        if (userRole == null) {
            throw new BusinessException(BusinessErrorMessage.USER_ROLE_NOT_EXIST);
        }
        Role role = roleDomainService.findById(new RoleId(userRoleSearchParam.getRoleId()));
        if (role == null) {
            throw new BusinessException(BusinessErrorMessage.ROLE_NOT_FOUND);
        }
        if (StringUtils.equals(role.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DISABLED);
        }
        if (StringUtils.equals(role.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DELETED);
        }
        userRole.delete();
        userRoleDomainService.update(userRole);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
