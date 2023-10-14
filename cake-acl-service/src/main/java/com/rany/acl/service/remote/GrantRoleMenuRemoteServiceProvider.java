package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.acl.api.command.grant.GrantRoleMenusCommand;
import com.rany.acl.api.facade.GrantRoleMenuFacade;
import com.rany.acl.common.params.RoleMenuSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.entity.RoleMenu;
import com.rany.acl.domain.service.RoleDomainService;
import com.rany.acl.domain.service.RoleMenuDomainService;
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
