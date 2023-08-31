package com.rany.acl.common.params;

import com.rany.acl.common.base.BasePageQuery;
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
public class TenantPageSearchParam extends BasePageQuery {
    
    private Long isvId;

    private String name;

    private String isDeleted;

    private String status;
}
