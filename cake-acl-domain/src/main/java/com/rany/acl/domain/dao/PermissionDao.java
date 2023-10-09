package com.rany.acl.domain.dao;

import com.rany.acl.domain.aggregate.Permission;
import com.rany.acl.infra.po.PermissionPO;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface PermissionDao extends BaseMapper<PermissionPO> {

    /**
     * 保存
     *
     * @param permission
     * @return
     */
    int save(Permission permission);

    /**
     * 更新
     *
     * @param permission
     * @return
     */
    int update(Permission permission);
}
