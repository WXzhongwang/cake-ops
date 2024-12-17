package com.rany.ops.api.query.account;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AccountBasicQuery
 *
 * @author zhongshengwang
 * @description AccountBasicQuery
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountBasicQuery extends BaseQuery {

    private Long accountId;

}
