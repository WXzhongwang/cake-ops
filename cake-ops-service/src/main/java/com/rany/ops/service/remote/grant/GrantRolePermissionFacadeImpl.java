package com.rany.ops.service.remote.grant;

import com.rany.ops.api.command.grant.DisGrantRolePermissionsCommand;
import com.rany.ops.api.command.grant.GrantRolePermissionsCommand;
import com.rany.ops.api.command.grant.GrantRolePermissionsV2Command;
import com.rany.ops.api.facade.grant.GrantRolePermissionFacade;
import com.rany.ops.common.params.RolePermissionSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.domain.service.RolePermissionDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhongshengwang
 */
@Slf4j
@Service
@AllArgsConstructor
public class GrantRolePermissionFacadeImpl implements GrantRolePermissionFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final RolePermissionDomainService rolePermissionDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantRolePermissions(GrantRolePermissionsCommand grantRolePermissionsCommand) {
        RolePermissionSearchParam roleMenuSearchParam = new RolePermissionSearchParam();
        roleMenuSearchParam.setAppCode(grantRolePermissionsCommand.getAppCode());
        roleMenuSearchParam.setRoleId(grantRolePermissionsCommand.getRoleId());
        if (grantRolePermissionsCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(grantRolePermissionsCommand.getTenantId());
        }
        // 当前已绑定菜单
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentPermissions)) {
            // 删除既往绑定
            for (RolePermission currentPermission : currentPermissions) {
                currentPermission.delete(grantRolePermissionsCommand.getUser());
                rolePermissionDomainService.update(currentPermission);
            }
        }
        for (Long permissionId : grantRolePermissionsCommand.getPermissionIds()) {
            RolePermission roleMenu = new RolePermission(grantRolePermissionsCommand.getAppCode(),
                    grantRolePermissionsCommand.getTenantId(), grantRolePermissionsCommand.getRoleId(), permissionId);
            roleMenu.save(grantRolePermissionsCommand.getUser());
            rolePermissionDomainService.save(roleMenu);
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantRolePermissionsV2(GrantRolePermissionsV2Command grantRolePermissionsCommand) {
        RolePermissionSearchParam roleMenuSearchParam = new RolePermissionSearchParam();
        roleMenuSearchParam.setAppCode(grantRolePermissionsCommand.getAppCode());
        roleMenuSearchParam.setRoleId(grantRolePermissionsCommand.getRoleId());
        if (grantRolePermissionsCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(grantRolePermissionsCommand.getTenantId());
        }
        // 当前已绑定权限点
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(roleMenuSearchParam);
        List<Long> addPermissionIds = grantRolePermissionsCommand.getAddPermissionIds();
        List<Long> removePermissionIds = grantRolePermissionsCommand.getRemovePermissionIds();
        if (CollectionUtils.isNotEmpty(addPermissionIds)) {
            for (Long permissionId : addPermissionIds) {
                RolePermission roleMenu = new RolePermission(grantRolePermissionsCommand.getAppCode(),
                        grantRolePermissionsCommand.getTenantId(), grantRolePermissionsCommand.getRoleId(), permissionId);
                roleMenu.save(grantRolePermissionsCommand.getUser());
                rolePermissionDomainService.save(roleMenu);
            }
        }
        if (CollectionUtils.isNotEmpty(removePermissionIds)) {
            List<RolePermission> removeInPermissions = currentPermissions.stream().filter(rolePermission ->
                    removePermissionIds.contains(rolePermission.getPermissionId())).collect(Collectors.toList());
            for (RolePermission rolePermission : removeInPermissions) {
                rolePermission.delete(grantRolePermissionsCommand.getUser());
                rolePermissionDomainService.update(rolePermission);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disGrantRolePermissions(DisGrantRolePermissionsCommand disGrantRolePermissionsCommand) {
        RolePermissionSearchParam roleMenuSearchParam = new RolePermissionSearchParam();
        roleMenuSearchParam.setAppCode(disGrantRolePermissionsCommand.getAppCode());
        roleMenuSearchParam.setRoleId(disGrantRolePermissionsCommand.getRoleId());
        if (disGrantRolePermissionsCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(disGrantRolePermissionsCommand.getTenantId());
        }
        // 当前已绑定权限点
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentPermissions)) {
            // 删除既往绑定
            for (RolePermission currentPermission : currentPermissions) {
                currentPermission.delete(disGrantRolePermissionsCommand.getUser());
                rolePermissionDomainService.update(currentPermission);
            }
        }
        return Boolean.TRUE;
    }
}
