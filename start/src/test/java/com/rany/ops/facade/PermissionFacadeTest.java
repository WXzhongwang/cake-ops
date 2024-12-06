package com.rany.ops.facade;

import com.rany.ops.BaseTests;
import com.rany.ops.api.command.permission.CreatePermissionCommand;
import com.rany.ops.api.facade.permission.PermissionFacade;
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
public class PermissionFacadeTest extends BaseTests {


    @Resource
    private PermissionFacade permissionFacade;
    private static final String APP_CODE = "ACL_12580";

    @Test
    public void createMenuPermission() {
        CreatePermissionCommand createPermissionCommand = new CreatePermissionCommand();
        createPermissionCommand.setAppCode(APP_CODE);
        createPermissionCommand.setRefMenuId(872080737707372544L);
        createPermissionCommand.setResourceType("action");
        createPermissionCommand.setResourceName("创建菜单");
        createPermissionCommand.setResourcePath("acl:menu:add");
        Long app = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertNotNull(app);


        createPermissionCommand.setResourcePath("acl:menu:update");
        createPermissionCommand.setResourceName("更新菜单");
        Long app1 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertNotNull(app1);

        createPermissionCommand.setResourcePath("acl:menu:enable");
        createPermissionCommand.setResourceName("启用菜单");
        Long app2 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertNotNull(app2);

        createPermissionCommand.setResourcePath("acl:menu:disable");
        createPermissionCommand.setResourceName("禁用菜单");
        Long app3 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertNotNull(app3);

        createPermissionCommand.setResourcePath("acl:menu:delete");
        createPermissionCommand.setResourceName("删除菜单");
        Long app4 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertNotNull(app4);
    }
}