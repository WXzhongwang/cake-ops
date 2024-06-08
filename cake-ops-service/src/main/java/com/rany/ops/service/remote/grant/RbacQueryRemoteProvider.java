package com.rany.ops.service.remote.grant;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.Constants;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.MenuSearchParam;
import com.rany.ops.common.params.RoleMenuSearchParam;
import com.rany.ops.common.params.RoleSearchParam;
import com.rany.ops.common.params.UserRoleSearchParam;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.service.*;
import com.rany.ops.infra.convertor.MenuDataConvertor;
import com.rany.ops.infra.convertor.RoleDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RbacQueryRemoteProvider implements RbacQueryFacade {

    private final ApplicationDomainService applicationDomainService;
    private final UserRoleDomainService userRoleDomainService;
    private final RoleMenuDomainService roleMenuDomainService;
    private final MenuDomainService menuDomainService;
    private final PermissionDomainService permissionDomainService;
    private final RolePermissionDomainService rolePermissionDomainService;
    private final RoleDomainService roleDomainService;
    private final RoleDataConvertor roleDataConvertor;
    private final MenuDataConvertor menuDataConvertor;

    @Override
    public PojoResult<UserRoleMenuDTO> getUserRbacModel(UserRoleMenuPermissionQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        UserRoleMenuDTO userRoleMenuPermissionDTO = new UserRoleMenuDTO().setRoles(new ArrayList<>())
                .setMenuTree(new ArrayList<>());

        UserRoleSearchParam userRoleSearchParam = new UserRoleSearchParam();
        userRoleSearchParam.setUserId(query.getAccountId());
        userRoleSearchParam.setTenantId(query.getTenantId());
        userRoleSearchParam.setAppCode(query.getAppCode());
        // 获取用户角色
        List<UserRole> userRoles = userRoleDomainService.listUserRoles(userRoleSearchParam);
        if (CollectionUtils.isEmpty(userRoles)) {
            return PojoResult.succeed(userRoleMenuPermissionDTO);
        }

        List<Long> ownRoleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setTenantId(query.getTenantId());
        searchParam.setAppCode(query.getAppCode());
        searchParam.setRoleIds(ownRoleIds);
        List<RoleDTO> roleDTOS = roleDomainService.selectRoleList(searchParam);
        userRoleMenuPermissionDTO.setRoles(roleDTOS);


        RoleDTO superAdminRole = roleDTOS.stream()
                .filter(p -> Objects.equals(p.getRoleKey(), Constants.SUPER_ADMINISTRATOR_ROLE_KEY)).findFirst().orElse(null);

        // 角色关联菜单
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setTenantId(query.getTenantId());
        roleMenuSearchParam.setAppCode(query.getAppCode());
        roleMenuSearchParam.setRoleIds(superAdminRole != null ? null : ownRoleIds);
        List<RoleMenu> roleRefMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isEmpty(roleRefMenus)) {
            return PojoResult.succeed(userRoleMenuPermissionDTO);
        }

        List<Long> roleRefMenuIds = roleRefMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        MenuSearchParam menuSearchParam = new MenuSearchParam();
        menuSearchParam.setTenantId(query.getTenantId());
        menuSearchParam.setAppCode(query.getAppCode());
        menuSearchParam.setMenuIds(roleRefMenuIds);
        List<MenuDTO> menuDTOS = menuDomainService.selectMenuList(menuSearchParam);
        List<MenuDTO> top = menuDTOS.stream().filter(p -> Objects.equals(p.getLevel(), 1)).collect(Collectors.toList());
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTreeDTO(top);
        for (MenuTreeDTO menuDTO : treeDTO) {
            recursive(menuDTO, menuDTOS);
        }
        userRoleMenuPermissionDTO.setMenuTree(treeDTO);
        return PojoResult.succeed(userRoleMenuPermissionDTO);
    }


    public void recursive(MenuTreeDTO treeDTO, List<MenuDTO> menuDTOS) {
        List<MenuDTO> children = menuDTOS.stream().filter(p -> Objects.equals(treeDTO.getMenuId(), p.getParentId())).collect(Collectors.toList());
        List<MenuTreeDTO> childrenTreeItems = menuDataConvertor.convertToTreeDTO(children);
        treeDTO.setChildren(childrenTreeItems);
        for (MenuTreeDTO childrenItem : childrenTreeItems) {
            recursive(childrenItem, menuDTOS);
        }
    }
}
