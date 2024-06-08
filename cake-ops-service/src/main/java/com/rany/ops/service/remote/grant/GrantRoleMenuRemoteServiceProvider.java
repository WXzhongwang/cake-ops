package com.rany.ops.service.remote.grant;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.ops.api.command.grant.GrantRoleMenusCommand;
import com.rany.ops.api.facade.grant.GrantRoleMenuFacade;
import com.rany.ops.common.params.RoleMenuSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.domain.service.RoleMenuDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GrantRoleMenuRemoteServiceProvider implements GrantRoleMenuFacade {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final RoleMenuDomainService roleMenuDomainService;
    private final RoleDomainService roleDomainService;

    @Override
    public PojoResult<Boolean> grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand) {
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setAppCode(grantRoleMenusCommand.getAppCode());
        roleMenuSearchParam.setRoleId(grantRoleMenusCommand.getRoleId());
        if (grantRoleMenusCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(grantRoleMenusCommand.getTenantId());
        }
        //当前已绑定菜单
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentMenus)) {
            //删除既往绑定
            for (RoleMenu currentMenu : currentMenus) {
                currentMenu.delete();
                roleMenuDomainService.update(currentMenu);
            }
        }
        for (Long menuId : grantRoleMenusCommand.getMenuIds()) {
            RoleMenu roleMenu = new RoleMenu(grantRoleMenusCommand.getAppCode(),
                    grantRoleMenusCommand.getTenantId(), grantRoleMenusCommand.getRoleId(), menuId);
            roleMenuDomainService.save(roleMenu);
        }
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand) {
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setAppCode(disGrantRoleMenusCommand.getAppCode());
        roleMenuSearchParam.setRoleId(disGrantRoleMenusCommand.getRoleId());
        if (disGrantRoleMenusCommand.getTenantId() != null) {
            roleMenuSearchParam.setTenantId(disGrantRoleMenusCommand.getTenantId());
        }
        //当前已绑定菜单
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isNotEmpty(currentMenus)) {
            //删除既往绑定
            for (RoleMenu currentMenu : currentMenus) {
                currentMenu.delete();
                roleMenuDomainService.update(currentMenu);
            }
        }
        return PojoResult.succeed(Boolean.TRUE);
    }
}
