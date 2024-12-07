package com.rany.ops.api.query.tenant;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TenantBasicQuery
 *
 * @author zhongshengwang
 * @description TenantBasicQuery
 * @date 2022/12/31 16:47
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBasicQuery extends BaseQuery {

    private Long tenantId;
}
