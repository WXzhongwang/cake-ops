package com.rany.acl.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.params.RoleSearchParam;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.pk.RoleId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface RoleRepository extends Repository<Role, RoleId> {

    /**
     * 角色更新
     *
     * @param role
     * @return
     */
    int update(Role role);


    Role findByRoleKey(String appCode, Long tenantId, String roleKey);

    /**
     * 按应用搜索角色列表，全部层级平铺
     *
     * @param roleSearchParam
     * @return
     */
    List<RoleDTO> findRoles(RoleSearchParam roleSearchParam);

}
