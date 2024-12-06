package com.rany.ops.api.query.account;

import com.rany.ops.common.base.BasePageQuery;
import com.rany.ops.common.enums.AccountTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号分页查询
 *
 * @author zhongshengwang
 * @description 账号分页查询
 * @date 2022/12/31 16:54
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountPageQuery extends BasePageQuery {

    private Long tenantId;

    private String accountName;

    private String email;

    private String phone;

    private AccountTypeEnum accountType;

    private Boolean containsAdmin = Boolean.TRUE;

    private Boolean excludeDeleted = true;

    private Boolean excludeDisabled = false;

}
