package com.rany.ops.api.query.grant;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户在单一应用下的角色，菜单，权限集合
 * 权限仅展示有角色菜单下的权限子集
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleMenuPermissionQuery extends BaseQuery {

    /**
     * 用户ID
     */
    private Long accountId;

    /**
     * 应用Code
     */
    private String appCode;

    /**
     * 非必填
     * 租户ID
     */
    private Long tenantId;
}
