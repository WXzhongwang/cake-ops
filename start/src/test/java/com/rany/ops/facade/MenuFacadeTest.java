package com.rany.ops.facade;

import com.rany.ops.BaseTests;
import com.rany.ops.api.command.menu.CreateMenuCommand;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.facade.menu.MenuFacade;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/6 22:41
 * @email 18668485565163.com
 */
public class MenuFacadeTest extends BaseTests {


    @Resource
    private MenuFacade menuFacade;
    @Resource
    private RbacQueryFacade rbacQueryFacade;
    private static final String APP_CODE = "ACL_12580";

    @Test
    public void createMenu() {
        CreateMenuCommand createMenuCommand = new CreateMenuCommand();
        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("应用管理");
        createMenuCommand.setPath("/acl/app/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(1);
        createMenuCommand.setHidden(false);
        Long menuId = menuFacade.createMenu(createMenuCommand);
        Assert.assertNotNull(menuId);


        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("菜单管理");
        createMenuCommand.setPath("/acl/menu/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        Long menu = menuFacade.createMenu(createMenuCommand);
        Assert.assertNotNull(menu);

        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("角色管理");
        createMenuCommand.setPath("/acl/role/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        Long role = menuFacade.createMenu(createMenuCommand);
        Assert.assertNotNull(role);

        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("页面权限管理");
        createMenuCommand.setPath("/acl/permission/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        Long permission = menuFacade.createMenu(createMenuCommand);
        Assert.assertNotNull(permission);
    }


    @Test
    public void getMenuTree() {
        MenuTreeQuery menuTreeQuery = new MenuTreeQuery();
        menuTreeQuery.setAppCode(APP_CODE);
        List<MenuTreeDTO> menuTree = menuFacade.getMenuTree(menuTreeQuery);
        Assert.assertFalse(menuTree.isEmpty());
    }

    @Test
    public void getUserMenuTree() {
        UserRoleMenuPermissionQuery query = new UserRoleMenuPermissionQuery();
        query.setAppCode("CAKE_DEVOPS");
        query.setAccountId(768460662077796352L);
        UserRoleMenuDTO menuTree = rbacQueryFacade.getUserRbacModel(query);
        Assert.assertNotNull(menuTree);
    }
}