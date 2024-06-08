package com.rany.ops.service.remote.grant;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.grant.DisGrantRolePermissionsCommand;
import com.rany.ops.api.command.grant.GrantRolePermissionsCommand;
import com.rany.ops.api.facade.grant.GrantRolePermissionFacade;
import com.rany.ops.common.params.RolePermissionSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.domain.service.RolePermissionDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GrantRolePermissionRemoteServiceProvider implements GrantRolePermissionFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final RolePermissionDomainService rolePermissionDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    public PojoResult<Boolean> grantRolePermissions(GrantRolePermissionsCommand grantRolePermissionsCommand) {
        RolePermissionSearchParam roleMenuSearchParam = new RolePermissionSearchParam();
        roleMenuSearchParam.setAppCode(grantRolePermissionsCommand.getAppCode());
        roleMenuSearchParam.setRoleId(grantRolePermissionsCommand.getRoleId());
        if (grantRolePermissionsCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(grantRolePermissionsCommand.getTenantId());
        }
        //当前已绑定菜单
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentPermissions)) {
            //删除既往绑定
            for (RolePermission currentPermission : currentPermissions) {
                currentPermission.delete();
                rolePermissionDomainService.update(currentPermission);
            }
        }
        for (Long permissionId : grantRolePermissionsCommand.getPermissionIds()) {
            RolePermission roleMenu = new RolePermission(grantRolePermissionsCommand.getAppCode(),
                    grantRolePermissionsCommand.getTenantId(), grantRolePermissionsCommand.getRoleId(), permissionId);
            rolePermissionDomainService.save(roleMenu);
        }
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> disGrantRolePermissions(DisGrantRolePermissionsCommand disGrantRolePermissionsCommand) {
        RolePermissionSearchParam roleMenuSearchParam = new RolePermissionSearchParam();
        roleMenuSearchParam.setAppCode(disGrantRolePermissionsCommand.getAppCode());
        roleMenuSearchParam.setRoleId(disGrantRolePermissionsCommand.getRoleId());
        if (disGrantRolePermissionsCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(disGrantRolePermissionsCommand.getTenantId());
        }
        //当前已绑定菜单
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentPermissions)) {
            //删除既往绑定
            for (RolePermission currentPermission : currentPermissions) {
                currentPermission.delete();
                rolePermissionDomainService.update(currentPermission);
            }
        }
        return PojoResult.succeed(Boolean.TRUE);
    }
}
