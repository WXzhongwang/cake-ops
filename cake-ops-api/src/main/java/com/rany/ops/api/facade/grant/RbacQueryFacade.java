package com.rany.ops.api.facade.grant;

import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;

/**
 * 查询用户角色权限模型接口
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface RbacQueryFacade {


    /**
     * 获取单一用户应用下权限集合
     *
     * @param query
     * @return
     */
    PojoResult<UserRoleMenuDTO> getUserRbacModel(UserRoleMenuPermissionQuery query);
}
