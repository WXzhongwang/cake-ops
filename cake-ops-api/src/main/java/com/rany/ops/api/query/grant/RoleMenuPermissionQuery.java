package com.rany.ops.api.query.grant;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 单一角色 菜单，权限集合
 *
 * @author zhongshengwang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuPermissionQuery extends BaseQuery {

    /**
     * roleId
     */
    private Long roleId;

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
