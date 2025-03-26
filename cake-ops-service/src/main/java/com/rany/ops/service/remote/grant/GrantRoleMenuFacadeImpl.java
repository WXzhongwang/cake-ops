package com.rany.ops.service.remote.grant;

import com.rany.ops.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.ops.api.command.grant.GrantRoleMenusCommand;
import com.rany.ops.api.facade.grant.GrantRoleMenuFacade;
import com.rany.ops.common.params.PermissionSearchParam;
import com.rany.ops.common.params.RoleMenuSearchParam;
import com.rany.ops.common.params.RolePermissionSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Permission;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.service.PermissionDomainService;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.domain.service.RoleMenuDomainService;
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
@AllArgsConstructor
@Service
public class GrantRoleMenuFacadeImpl implements GrantRoleMenuFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final RoleMenuDomainService roleMenuDomainService;
    private final RolePermissionDomainService rolePermissionDomainService;
    private final PermissionDomainService permissionDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand) {
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setAppCode(grantRoleMenusCommand.getAppCode());
        roleMenuSearchParam.setRoleId(grantRoleMenusCommand.getRoleId());
        roleMenuSearchParam.setTenantId(grantRoleMenusCommand.getTenantId());
        // 当前已绑定菜单
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        // 分别获取待添加部分和待删除部分
        List<Long> existedMenuIds = currentMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Long> menuIds = grantRoleMenusCommand.getMenuIds();
        List<Long> addMenusIds = menuIds.stream().filter(menuId -> !existedMenuIds.contains(menuId)).collect(Collectors.toList());
        List<Long> deleteMenusIds = existedMenuIds.stream().filter(menuId -> !menuIds.contains(menuId)).collect(Collectors.toList());
        List<RoleMenu> deleteMenus = currentMenus.stream().filter(menu -> !menuIds.contains(menu.getMenuId())).collect(Collectors.toList());

        for (Long menuId : addMenusIds) {
            RoleMenu roleMenu = new RoleMenu(grantRoleMenusCommand.getAppCode(),
                    grantRoleMenusCommand.getTenantId(), menuId, grantRoleMenusCommand.getRoleId());
            roleMenu.save(grantRoleMenusCommand.getUser());
            roleMenuDomainService.save(roleMenu);
        }
        for (RoleMenu roleMenu : deleteMenus) {
            roleMenu.delete(grantRoleMenusCommand.getUser());
            roleMenuDomainService.update(roleMenu);
        }

        // 获取删除菜单下绑定的权限
        PermissionSearchParam searchParam = new PermissionSearchParam();
        searchParam.setAppCode(grantRoleMenusCommand.getAppCode());
        searchParam.setRefMenuIds(deleteMenusIds);
        searchParam.setTenantId(grantRoleMenusCommand.getTenantId());
        List<Permission> permissions = permissionDomainService.selectPermissionList(searchParam);
        List<Long> permissionIds = permissions.stream().map(p -> p.getId().getId()).collect(Collectors.toList());
        // 将这些权限点与角色解绑
        RolePermissionSearchParam rolePermissionSearchParam = new RolePermissionSearchParam();
        rolePermissionSearchParam.setPermissionIds(permissionIds);
        rolePermissionSearchParam.setAppCode(grantRoleMenusCommand.getAppCode());
        rolePermissionSearchParam.setRoleId(grantRoleMenusCommand.getRoleId());
        rolePermissionSearchParam.setTenantId(grantRoleMenusCommand.getTenantId());
        List<RolePermission> rolePermissions = rolePermissionDomainService.getRolePermissions(rolePermissionSearchParam);
        if (CollectionUtils.isNotEmpty(rolePermissions)) {
            for (RolePermission rolePermission : rolePermissions) {
                rolePermission.delete(grantRoleMenusCommand.getUser());
                rolePermissionDomainService.update(rolePermission);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand) {
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setAppCode(disGrantRoleMenusCommand.getAppCode());
        roleMenuSearchParam.setRoleId(disGrantRoleMenusCommand.getRoleId());
        roleMenuSearchParam.setTenantId(disGrantRoleMenusCommand.getTenantId());
        //当前已绑定菜单
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentMenus)) {
            // 删除既往绑定
            for (RoleMenu currentMenu : currentMenus) {
                currentMenu.delete(disGrantRoleMenusCommand.getUser());
                roleMenuDomainService.update(currentMenu);
            }
        }
        // 角色菜单被清除时，对应的角色权限一并清除
        RolePermissionSearchParam rolePermissionSearchParam = new RolePermissionSearchParam();
        rolePermissionSearchParam.setAppCode(disGrantRoleMenusCommand.getAppCode());
        rolePermissionSearchParam.setRoleId(disGrantRoleMenusCommand.getRoleId());
        rolePermissionSearchParam.setTenantId(disGrantRoleMenusCommand.getTenantId());
        List<RolePermission> rolePermissions = rolePermissionDomainService.getRolePermissions(rolePermissionSearchParam);
        if (CollectionUtils.isNotEmpty(rolePermissions)) {
            for (RolePermission rolePermission : rolePermissions) {
                rolePermission.delete(disGrantRoleMenusCommand.getUser());
                rolePermissionDomainService.update(rolePermission);
            }
        }
        return Boolean.TRUE;
    }
}
