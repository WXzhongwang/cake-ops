package com.rany.acl.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.pk.RoleId;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface RoleRepository extends Repository<Role, RoleId> {
}
