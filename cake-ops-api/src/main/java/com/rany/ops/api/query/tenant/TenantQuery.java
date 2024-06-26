package com.rany.ops.api.query.tenant;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:47
 * @email 18668485565163.com
 */
@Data
public class TenantQuery extends BaseQuery {

    private Long isvId;

    private String name;

    private Boolean excludeDeleted = true;

    private Boolean excludeDisabled = false;
}
