package com.rany.ops.common.params;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/5 20:50
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionSearchParam extends BaseQuery {

    private String appCode;

    private Long tenantId;
    
    private Long refMenuId;
}
