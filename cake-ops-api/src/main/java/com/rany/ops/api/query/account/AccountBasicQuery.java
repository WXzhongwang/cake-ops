package com.rany.ops.api.query.account;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
public class AccountBasicQuery extends BaseQuery {

    private Long accountId;

    private Long tenantId;
}
