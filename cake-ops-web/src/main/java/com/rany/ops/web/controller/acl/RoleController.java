package com.rany.ops.web.controller.acl;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.grant.*;
import com.rany.ops.api.command.role.*;
import com.rany.ops.api.facade.grant.GrantRoleMenuFacade;
import com.rany.ops.api.facade.grant.GrantRolePermissionFacade;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.facade.role.RoleFacade;
import com.rany.ops.api.query.grant.RoleMenuListQuery;
import com.rany.ops.api.query.grant.RoleMenuPermissionQuery;
import com.rany.ops.api.query.grant.RoleMenuTreeQuery;
import com.rany.ops.api.query.grant.RolePermissionListQuery;
import com.rany.ops.api.query.role.RoleBasicQuery;
import com.rany.ops.api.query.role.RoleTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.dto.permission.PermissionDTO;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.dto.role.RoleTreeDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * MenuController
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/role")
public class RoleController {

    @Resource
    private RoleFacade roleFacade;
    @Resource
    private GrantRoleMenuFacade grantRoleMenuFacade;
    @Resource
    private GrantRolePermissionFacade grantRolePermissionFacade;

    @Resource
    private RbacQueryFacade rbacQueryFacade;

    @PostMapping("/get-role-tree")
    public ListResult<RoleTreeDTO> getRoleTree(@RequestBody RoleTreeQuery roleTreeQuery) {
        List<RoleTreeDTO> roleTree = roleFacade.getRoleTree(roleTreeQuery);
        return ListResult.succeed(roleTree);
    }

    @PostMapping("/list-role")
    public ListResult<RoleDTO> listRules(@RequestBody RoleTreeQuery roleTreeQuery) {
        List<RoleDTO> roleTree = roleFacade.listRoles(roleTreeQuery);
        return ListResult.succeed(roleTree);
    }

    @PostMapping("/get")
    public PojoResult<RoleDTO> get(@RequestBody RoleBasicQuery query) {
        RoleDTO role = roleFacade.getRole(query);
        return PojoResult.succeed(role);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateRoleCommand command) {
        Long roleId = roleFacade.createRole(command);
        return PojoResult.succeed(roleId.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyRoleCommand command) {
        Boolean success = roleFacade.modifyRole(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableRoleCommand command) {
        Boolean role = roleFacade.enableRole(command);
        return PojoResult.succeed(role);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableRoleCommand command) {
        Boolean role = roleFacade.disableRole(command);
        return PojoResult.succeed(role);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteRoleCommand command) {
        Boolean role = roleFacade.deleteRole(command);
        return PojoResult.succeed(role);
    }

    @PostMapping("/get-role-menu-permission")
    public PojoResult<List<MenuTreeDTO>> getRoleMenuPermission(@RequestBody RoleMenuPermissionQuery query) {
        List<MenuTreeDTO> roleMenuPermissions = rbacQueryFacade.getRoleMenuPermissions(query);
        return PojoResult.succeed(roleMenuPermissions);
    }

    @PostMapping("/get-role-menu-tree")
    public PojoResult<List<MenuTreeDTO>> getRoleMenus(@RequestBody RoleMenuTreeQuery query) {
        List<MenuTreeDTO> tree = rbacQueryFacade.getRoleMenuTree(query);
        return PojoResult.succeed(tree);
    }

    @PostMapping("/list-role-menu")
    public PojoResult<List<MenuDTO>> listRoleMenus(@RequestBody RoleMenuListQuery query) {
        List<MenuDTO> menus = rbacQueryFacade.listRoleMenus(query);
        return PojoResult.succeed(menus);
    }

    @PostMapping("/list-role-permission")
    public PojoResult<List<PermissionDTO>> listRolePermissions(@RequestBody RolePermissionListQuery query) {
        List<PermissionDTO> menus = rbacQueryFacade.listRolePermissions(query);
        return PojoResult.succeed(menus);
    }


    @PostMapping("/grant-role-menu")
    public PojoResult<Boolean> grantRoleMenus(@RequestBody GrantRoleMenusCommand command) {
        Boolean success = grantRoleMenuFacade.grantRoleMenus(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/clear-role-menu")
    public PojoResult<Boolean> disGrantRoleMenus(@RequestBody DisGrantRoleMenusCommand command) {
        Boolean success = grantRoleMenuFacade.disGrantRoleMenus(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/grant-role-permission")
    public PojoResult<Boolean> grantRolePermissions(@RequestBody GrantRolePermissionsCommand command) {
        Boolean success = grantRolePermissionFacade.grantRolePermissions(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/v2/grant-role-permission")
    public PojoResult<Boolean> grantRolePermissionsV2(@RequestBody GrantRolePermissionsV2Command command) {
        Boolean success = grantRolePermissionFacade.grantRolePermissionsV2(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/clear-role-permission")
    public PojoResult<Boolean> disGrantRolePermissions(@RequestBody DisGrantRolePermissionsCommand command) {
        Boolean success = grantRolePermissionFacade.disGrantRolePermissions(command);
        return PojoResult.succeed(success);
    }

}
