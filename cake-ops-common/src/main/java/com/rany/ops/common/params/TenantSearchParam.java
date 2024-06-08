package com.rany.ops.common.params;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/5 20:50
 * @email 18668485565163.com
 */
@Data
public class TenantSearchParam extends BaseQuery {

    private Long isvId;

    private String name;

    private String isDeleted;

    private String status;
}
