package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.role.*;
import com.rany.acl.api.query.role.RoleBasicQuery;
import com.rany.acl.api.query.role.RoleTreeQuery;
import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.dto.role.RoleTreeDTO;


/**
 * 角色管理
 *
 * @author zhongshengwang
 * @description 角色管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface RoleFacade {

    /**
     * 创建角色
     *
     * @param createRoleCommand
     * @return
     */
    PojoResult<Long> createRole(CreateRoleCommand createRoleCommand);

    /**
     * 获取角色信息
     *
     * @param RoleBasicQuery
     * @return
     */
    PojoResult<RoleDTO> getRole(RoleBasicQuery RoleBasicQuery);

    /**
     * 获取角色树信息
     *
     * @param RoleTreeQuery
     * @return
     */
    ListResult<RoleTreeDTO> getRoleTree(RoleTreeQuery RoleTreeQuery);

    /**
     * 角色禁用
     *
     * @param disableRoleCommand
     * @return
     */
    PojoResult<Boolean> disableRole(DisableRoleCommand disableRoleCommand);

    /**
     * 启用角色
     *
     * @param enableRoleCommand
     * @return
     */
    PojoResult<Boolean> enableRole(EnableRoleCommand enableRoleCommand);

    /**
     * 删除角色
     *
     * @param deleteRoleCommand
     * @return
     */
    PojoResult<Boolean> deleteRole(DeleteRoleCommand deleteRoleCommand);

    /**
     * 更新角色基本信息
     *
     * @param modifyRoleCommand
     * @return
     */
    PojoResult<Boolean> modifyRole(ModifyRoleCommand modifyRoleCommand);
}
