package com.rany.ops.common.params;

import com.rany.ops.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TenantPageSearchParam
 *
 * @author zhongshengwang
 * @description TenantPageSearchParam
 * @date 2023/1/5 20:50
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantPageSearchParam extends BasePageQuery {

    private Long isvId;

    private String name;

    private String isDeleted;

    private String status;
}
