package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.BaseTests;
import com.rany.acl.api.command.menu.CreateMenuCommand;
import com.rany.acl.api.facade.menu.MenuFacade;
import com.rany.acl.api.query.menu.MenuTreeQuery;
import com.rany.acl.common.dto.menu.MenuTreeDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

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
        PojoResult<Long> app = menuFacade.createMenu(createMenuCommand);
        Assert.assertTrue(app.getSuccess());


        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("菜单管理");
        createMenuCommand.setPath("/acl/menu/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        PojoResult<Long> menu = menuFacade.createMenu(createMenuCommand);
        Assert.assertTrue(menu.getSuccess());

        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("角色管理");
        createMenuCommand.setPath("/acl/role/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        PojoResult<Long> role = menuFacade.createMenu(createMenuCommand);
        Assert.assertTrue(role.getSuccess());

        createMenuCommand.setAppCode(APP_CODE);
        createMenuCommand.setName("页面权限管理");
        createMenuCommand.setPath("/acl/permission/list");
        createMenuCommand.setIcon("https://avatars.githubusercontent.com/u/27359059?v=4");
        createMenuCommand.setSort(2);
        createMenuCommand.setHidden(false);
        PojoResult<Long> permission = menuFacade.createMenu(createMenuCommand);
        Assert.assertTrue(permission.getSuccess());
    }


    @Test
    public void getMenuTree() {
        MenuTreeQuery menuTreeQuery = new MenuTreeQuery();
        menuTreeQuery.setAppCode(APP_CODE);
        ListResult<MenuTreeDTO> menuTree = menuFacade.getMenuTree(menuTreeQuery);
        Assert.assertTrue(menuTree.getSuccess());
    }
}