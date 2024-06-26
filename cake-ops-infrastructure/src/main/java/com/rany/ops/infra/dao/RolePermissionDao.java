package com.rany.ops.infra.dao;

import com.rany.ops.common.params.RolePermissionSearchParam;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.infra.po.RolePermissionPO;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface RolePermissionDao {

    /**
     * 保存
     *
     * @param rolePermission
     * @return
     */
    int save(RolePermission rolePermission);

    /**
     * 更新
     *
     * @param rolePermission
     * @return
     */
    int update(RolePermission rolePermission);


    /**
     * 查询角色绑定的权限点
     *
     * @param searchParam
     * @return
     */
    List<RolePermissionPO> selectRolePermissionList(RolePermissionSearchParam searchParam);
}
