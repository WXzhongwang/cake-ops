package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.BaseTests;
import com.rany.acl.api.command.permission.CreatePermissionCommand;
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
        PojoResult<Long> app = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertTrue(app.getSuccess());


        createPermissionCommand.setResourcePath("acl:menu:update");
        createPermissionCommand.setResourceName("更新菜单");
        PojoResult<Long> app1 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertTrue(app1.getSuccess());

        createPermissionCommand.setResourcePath("acl:menu:enable");
        createPermissionCommand.setResourceName("启用菜单");
        PojoResult<Long> app2 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertTrue(app2.getSuccess());

        createPermissionCommand.setResourcePath("acl:menu:disable");
        createPermissionCommand.setResourceName("禁用菜单");
        PojoResult<Long> app3 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertTrue(app3.getSuccess());

        createPermissionCommand.setResourcePath("acl:menu:delete");
        createPermissionCommand.setResourceName("删除菜单");
        PojoResult<Long> app4 = permissionFacade.createPermission(createPermissionCommand);
        Assert.assertTrue(app4.getSuccess());
    }
}