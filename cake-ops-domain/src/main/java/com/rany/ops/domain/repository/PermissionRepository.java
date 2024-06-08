package com.rany.ops.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.dto.permission.PermissionDTO;
import com.rany.ops.common.params.PermissionSearchParam;
import com.rany.ops.domain.aggregate.Permission;
import com.rany.ops.domain.pk.PermissionId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface PermissionRepository extends Repository<Permission, PermissionId> {

    /**
     * 角色更新
     *
     * @param permission
     * @return
     */
    int update(Permission permission);

    /**
     * 按应用搜索角色列表，全部层级平铺
     *
     * @param searchParam
     * @return
     */
    List<PermissionDTO> findPermissions(PermissionSearchParam searchParam);
}
