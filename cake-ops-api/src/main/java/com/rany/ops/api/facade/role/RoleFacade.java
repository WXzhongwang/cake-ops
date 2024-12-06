package com.rany.ops.api.facade.role;

import com.rany.ops.api.command.role.*;
import com.rany.ops.api.query.role.RoleBasicQuery;
import com.rany.ops.api.query.role.RoleTreeQuery;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.dto.role.RoleTreeDTO;

import java.util.List;


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
     * @param createRoleCommand 角色创建参数
     * @return 角色ID
     */
    Long createRole(CreateRoleCommand createRoleCommand);

    /**
     * 获取角色信息
     *
     * @param roleBasicQuery 查询参数
     * @return 角色信息
     */
    RoleDTO getRole(RoleBasicQuery roleBasicQuery);

    /**
     * 获取角色树信息
     *
     * @param roleTreeQuery 角色树查询
     * @return 角色树
     */
    List<RoleTreeDTO> getRoleTree(RoleTreeQuery roleTreeQuery);

    /**
     * 获取角色列表信息
     *
     * @param roleTreeQuery 角色树查询
     * @return 角色树
     */
    List<RoleDTO> listRoles(RoleTreeQuery roleTreeQuery);

    /**
     * 角色禁用
     *
     * @param disableRoleCommand 角色禁用
     * @return 是否禁用成功
     */
    Boolean disableRole(DisableRoleCommand disableRoleCommand);

    /**
     * 启用角色
     *
     * @param enableRoleCommand 角色启用
     * @return 是否启用成功
     */
    Boolean enableRole(EnableRoleCommand enableRoleCommand);

    /**
     * 删除角色
     *
     * @param deleteRoleCommand 角色删除
     * @return 是否删除成功
     */
    Boolean deleteRole(DeleteRoleCommand deleteRoleCommand);

    /**
     * 更新角色基本信息
     *
     * @param modifyRoleCommand 更新
     * @return 是否更新成功
     */
    Boolean modifyRole(ModifyRoleCommand modifyRoleCommand);
}
