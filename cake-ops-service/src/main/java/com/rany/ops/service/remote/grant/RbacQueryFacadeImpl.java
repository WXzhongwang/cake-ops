package com.rany.ops.service.remote.grant;

import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.facade.menu.MenuFacade;
import com.rany.ops.api.query.grant.*;
import com.rany.ops.common.Constants;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.dto.permission.PermissionDTO;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.params.*;
import com.rany.ops.common.util.TreeUtil;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.aggregate.Permission;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.repository.MenuRepository;
import com.rany.ops.domain.service.*;
import com.rany.ops.infra.convertor.MenuDataConvertor;
import com.rany.ops.infra.convertor.PermissionDataConvertor;
import com.rany.ops.infra.convertor.RoleDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhongshengwang
 */
@Slf4j
@Service
@AllArgsConstructor
public class RbacQueryFacadeImpl implements RbacQueryFacade {

    private final ApplicationDomainService applicationDomainService;
    private final UserRoleDomainService userRoleDomainService;
    private final RoleMenuDomainService roleMenuDomainService;
    private final MenuDomainService menuDomainService;
    private final PermissionDomainService permissionDomainService;
    private final RolePermissionDomainService rolePermissionDomainService;
    private final RoleDomainService roleDomainService;
    private final RoleDataConvertor roleDataConvertor;
    private final MenuDataConvertor menuDataConvertor;
    private final PermissionDataConvertor permissionDataConvertor;
    private final MenuFacade menuFacade;
    private final MenuRepository menuRepository;

    @Override
    public UserRoleMenuDTO getUserRbacModel(UserRoleMenuPermissionQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            return new UserRoleMenuDTO();
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
            return userRoleMenuPermissionDTO;
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

        MenuSearchParam menuSearchParam = new MenuSearchParam();
        searchParam.setAppCode(query.getAppCode());
        searchParam.setTenantId(query.getTenantId());

        if (superAdminRole != null) {
            List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
            List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);
            List<MenuTreeDTO> tree = TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getParentId(), "0"),
                    (parent, sub) -> Objects.equals(parent.getMenuId(), sub.getParentId()), MenuTreeDTO::setChildren);
            userRoleMenuPermissionDTO.setMenuTree(tree);
            return userRoleMenuPermissionDTO;
        }
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setTenantId(query.getTenantId());
        roleMenuSearchParam.setAppCode(query.getAppCode());
        roleMenuSearchParam.setRoleIds(ownRoleIds);
        List<RoleMenu> roleRefMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isEmpty(roleRefMenus)) {
            return userRoleMenuPermissionDTO;
        }

        List<Long> roleRefMenuIds = roleRefMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        menuSearchParam.setMenuIds(roleRefMenuIds);
        List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);
        List<MenuTreeDTO> tree = TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getParentId(), "0"),
                (parent, sub) -> Objects.equals(parent.getMenuId(), sub.getParentId()), MenuTreeDTO::setChildren);
        userRoleMenuPermissionDTO.setMenuTree(tree);
        return userRoleMenuPermissionDTO;
    }

    /**
     * 单一角色菜单权限集合
     *
     * @param query 查询条件
     * @return 单一角色菜单权限集合
     */
    @Override
    public List<MenuTreeDTO> getRoleMenuPermissions(RoleMenuPermissionQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            return Collections.emptyList();
        }
        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setTenantId(query.getTenantId());
        searchParam.setAppCode(query.getAppCode());
        searchParam.setRoleIds(Lists.of(query.getRoleId()));
        List<RoleDTO> roles = roleDomainService.selectRoleList(searchParam);
        RoleDTO superAdminRole = roles.stream()
                .filter(p -> Objects.equals(p.getRoleKey(), Constants.SUPER_ADMINISTRATOR_ROLE_KEY)).findFirst().orElse(null);

        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setTenantId(query.getTenantId());
        roleMenuSearchParam.setAppCode(query.getAppCode());
        roleMenuSearchParam.setRoleId(query.getRoleId());

        RolePermissionSearchParam rolePermissionSearchParam = new RolePermissionSearchParam();
        rolePermissionSearchParam.setAppCode(query.getAppCode());
        rolePermissionSearchParam.setRoleId(query.getRoleId());
        rolePermissionSearchParam.setTenantId(query.getTenantId());

        if (superAdminRole != null) {
            // 如果是超管则默认加载全部菜单
            roleMenuSearchParam.setRoleId(null);
            // 如果是超管则默认加载全部权限点
            rolePermissionSearchParam.setRoleId(null);
        }

        // 当前已绑定菜单
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        List<Long> roleRefMenuIds = currentMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        // 当前已绑定权限点
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(rolePermissionSearchParam);
        List<Long> roleRefPermissionIds = currentPermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

        MenuSearchParam menuSearchParam = new MenuSearchParam();
        menuSearchParam.setTenantId(query.getTenantId());
        menuSearchParam.setAppCode(query.getAppCode());
        menuSearchParam.setMenuIds(roleRefMenuIds);
        List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);

        PermissionSearchParam permissionSearchParam = new PermissionSearchParam();
        permissionSearchParam.setTenantId(query.getTenantId());
        permissionSearchParam.setAppCode(query.getAppCode());
        permissionSearchParam.setPermissionIds(roleRefPermissionIds);
        List<Permission> permissions = permissionDomainService.selectPermissionList(permissionSearchParam);
        List<PermissionDTO> permissionList = permissionDataConvertor.sourceToDTO(permissions);
        dfs(treeDTO, permissionList);

        return TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getParentId(), "0"),
                (parent, sub) -> Objects.equals(parent.getMenuId(), sub.getParentId()), MenuTreeDTO::setChildren);
    }

    @Override
    public List<MenuTreeDTO> getRoleMenus(RoleMenuTreeQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            return Collections.emptyList();
        }
        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setTenantId(query.getTenantId());
        searchParam.setAppCode(query.getAppCode());
        searchParam.setRoleIds(Lists.of(query.getRoleId()));
        List<RoleDTO> roles = roleDomainService.selectRoleList(searchParam);

        RoleDTO superAdminRole = roles.stream().filter(p -> Objects.equals(p.getRoleKey(),
                Constants.SUPER_ADMINISTRATOR_ROLE_KEY)).findFirst().orElse(null);
        MenuSearchParam menuSearchParam = new MenuSearchParam();
        searchParam.setAppCode(query.getAppCode());
        searchParam.setTenantId(query.getTenantId());
        if (superAdminRole != null) {
            List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
            List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);
            return TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getParentId(), "0"),
                    (parent, sub) -> Objects.equals(parent.getMenuId(), sub.getParentId()), MenuTreeDTO::setChildren);
        }

        // 当前已绑定菜单
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setTenantId(query.getTenantId());
        roleMenuSearchParam.setAppCode(query.getAppCode());
        roleMenuSearchParam.setRoleId(query.getRoleId());
        List<RoleMenu> currentMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        List<Long> roleRefMenuIds = currentMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleRefMenuIds)) {
            return Collections.emptyList();
        }
        menuSearchParam.setMenuIds(roleRefMenuIds);
        List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);
        return TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getParentId(), "0"),
                (parent, sub) -> Objects.equals(parent.getMenuId(), sub.getParentId()), MenuTreeDTO::setChildren);
    }

    @Override
    public List<MenuDTO> listRoleMenus(RoleMenuListQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            return Collections.emptyList();
        }
        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setTenantId(query.getTenantId());
        searchParam.setAppCode(query.getAppCode());
        searchParam.setRoleIds(Lists.of(query.getRoleId()));

        List<RoleDTO> roles = roleDomainService.selectRoleList(searchParam);
        RoleDTO superAdminRole = roles.stream().filter(p -> Objects.equals(p.getRoleKey(),
                Constants.SUPER_ADMINISTRATOR_ROLE_KEY)).findFirst().orElse(null);

        MenuSearchParam menuSearchParam = new MenuSearchParam();
        searchParam.setAppCode(query.getAppCode());
        searchParam.setTenantId(query.getTenantId());

        if (superAdminRole != null) {
            List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
            return menuDataConvertor.sourceToDTO(menus);
        }
        // 当前已绑定菜单
        RoleMenuSearchParam roleMenuSearchParam = new RoleMenuSearchParam();
        roleMenuSearchParam.setTenantId(query.getTenantId());
        roleMenuSearchParam.setAppCode(query.getAppCode());
        roleMenuSearchParam.setRoleIds(Lists.of(query.getRoleId()));
        List<RoleMenu> roleRefMenus = roleMenuDomainService.getRoleMenus(roleMenuSearchParam);
        if (CollectionUtils.isEmpty(roleRefMenus)) {
            return Collections.emptyList();
        }
        List<Long> roleRefMenuIds = roleRefMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        menuSearchParam.setMenuIds(roleRefMenuIds);
        List<Menu> menus = menuDomainService.selectMenuList(menuSearchParam);
        return menuDataConvertor.sourceToDTO(menus);
    }

    @Override
    public List<PermissionDTO> listRolePermissions(RolePermissionListQuery query) {
        Application application = applicationDomainService.findByAppCode(query.getAppCode());
        if (application == null) {
            return Collections.emptyList();
        }
        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setTenantId(query.getTenantId());
        searchParam.setAppCode(query.getAppCode());
        searchParam.setRoleIds(Lists.of(query.getRoleId()));

        List<RoleDTO> roles = roleDomainService.selectRoleList(searchParam);
        RoleDTO superAdminRole = roles.stream().filter(p -> Objects.equals(p.getRoleKey(),
                Constants.SUPER_ADMINISTRATOR_ROLE_KEY)).findFirst().orElse(null);

        PermissionSearchParam permissionSearchParam = new PermissionSearchParam();
        permissionSearchParam.setTenantId(query.getTenantId());
        permissionSearchParam.setAppCode(query.getAppCode());

        if (superAdminRole != null) {
            List<Permission> permissions = permissionDomainService.selectPermissionList(permissionSearchParam);
            return permissionDataConvertor.sourceToDTO(permissions);
        }

        RolePermissionSearchParam rolePermissionSearchParam = new RolePermissionSearchParam();
        rolePermissionSearchParam.setAppCode(query.getAppCode());
        rolePermissionSearchParam.setRoleId(query.getRoleId());
        rolePermissionSearchParam.setTenantId(query.getTenantId());
        // 当前已绑定权限点
        List<RolePermission> currentPermissions = rolePermissionDomainService.getRolePermissions(rolePermissionSearchParam);
        List<Long> roleRefPermissionIds = currentPermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleRefPermissionIds)) {
            return Collections.emptyList();
        }
        permissionSearchParam.setPermissionIds(roleRefPermissionIds);
        List<Permission> permissions = permissionDomainService.selectPermissionList(permissionSearchParam);
        return permissionDataConvertor.sourceToDTO(permissions);
    }

    public void dfs(List<MenuTreeDTO> tree, List<PermissionDTO> permissions) {
        for (MenuTreeDTO menuTreeDTO : tree) {
            List<PermissionDTO> permissionDTOList = permissions.stream().filter(p -> Objects.equals(p.getRefMenuId(), menuTreeDTO.getMenuId()))
                    .collect(Collectors.toList());
            menuTreeDTO.setPermissions(permissionDTOList);
            if (CollectionUtils.isNotEmpty(menuTreeDTO.getChildren())) {
                dfs(menuTreeDTO.getChildren(), permissions);
            }
        }
    }
}
