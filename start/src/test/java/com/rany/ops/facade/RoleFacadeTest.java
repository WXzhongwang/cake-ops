package com.rany.ops.facade;

import com.rany.ops.BaseTests;
import com.rany.ops.api.command.role.CreateRoleCommand;
import com.rany.ops.api.facade.role.RoleFacade;
import com.rany.ops.api.query.role.RoleBasicQuery;
import com.rany.ops.api.query.role.RoleTreeQuery;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.dto.role.RoleTreeDTO;
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
        Long roleId = roleFacade.createRole(createRoleCommand);
        Assert.assertNotNull(roleId);
    }

    @Test
    public void getMenu() {
        RoleBasicQuery roleBasicQuery = new RoleBasicQuery();
        roleBasicQuery.setAppCode(APP_CODE);
        roleBasicQuery.setRoleId(872090360875200512L);
        RoleDTO role = roleFacade.getRole(roleBasicQuery);
        Assert.assertNotNull(role);
    }

    @Test
    public void getMenuTree() {
        RoleTreeQuery roleTreeQuery = new RoleTreeQuery();
        roleTreeQuery.setAppCode(APP_CODE);
        List<RoleTreeDTO> roleTree = roleFacade.getRoleTree(roleTreeQuery);
        Assert.assertFalse(roleTree.isEmpty());
    }
}