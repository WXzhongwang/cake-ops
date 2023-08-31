package com.rany.acl.api.query.account;

import com.rany.acl.common.base.BasePageQuery;
import com.rany.acl.common.enums.AccountTypeEnum;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:54
 * @email 18668485565163.com
 */
@Data
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
