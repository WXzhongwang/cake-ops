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
public class RoleBasicQuery extends BaseQuery {

    private Long roleId;
    private String appCode;
}
