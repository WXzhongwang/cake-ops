package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.BaseTests;
import com.rany.acl.api.command.role.CreateRoleCommand;
import com.rany.acl.api.facade.role.RoleFacade;
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
public class RoleFacadeTest extends BaseTests {


    @Resource
    private RoleFacade roleFacade;
    private static final String APP_CODE = "ACL_12580";

    @Test
    public void createMenu() {
        CreateRoleCommand createRoleCommand = new CreateRoleCommand();
        createRoleCommand.setAppCode(APP_CODE);
        createRoleCommand.setRoleName("应用超级管理员");
        createRoleCommand.setRoleDesc("应用超级管理员");
        createRoleCommand.setRoleKey("SUPER_ADMINISTRATOR");
        PojoResult<Long> app = roleFacade.createRole(createRoleCommand);
        Assert.assertTrue(app.getSuccess());
    }
}