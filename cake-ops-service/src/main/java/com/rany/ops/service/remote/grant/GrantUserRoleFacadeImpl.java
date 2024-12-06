package com.rany.ops.service.remote.grant;


import com.cake.framework.common.exception.BusinessException;
import com.rany.ops.api.command.grant.DisGrantUserRoleCommand;
import com.rany.ops.api.command.grant.GrantUserRoleCommand;
import com.rany.ops.api.facade.grant.GrantUserRoleFacade;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.UserRoleSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Role;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.pk.RoleId;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.domain.service.UserRoleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author zhongshengwang
 */
@Slf4j
@Service
@AllArgsConstructor
public class GrantUserRoleFacadeImpl implements GrantUserRoleFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final UserRoleDomainService userRoleDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    public Boolean grantUserRole(GrantUserRoleCommand grantUserRoleCommand) {
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
        return Boolean.TRUE;
    }

    @Override
    public Boolean disGrantUserRole(DisGrantUserRoleCommand disGrantUserRoleCommand) {
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
        return Boolean.TRUE;
    }
}
