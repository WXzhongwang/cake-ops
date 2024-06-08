package com.rany.ops.api.query.role;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleTreeQuery extends BaseQuery {

    /**
     * 非必填
     */
    private Long tenantId;

    private String appCode;
}
