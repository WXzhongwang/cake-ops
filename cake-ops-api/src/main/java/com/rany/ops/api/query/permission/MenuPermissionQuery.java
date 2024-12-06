package com.rany.ops.api.query.permission;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单下权限点查询
 *
 * @author zhongshengwang
 * @description 菜单下权限点查询
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuPermissionQuery extends BaseQuery {

    /**
     * 关联菜单
     */
    private Long refMenuId;

    /**
     * appCode
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;
}
