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
public class AccountPageSearchParam extends BasePageQuery {

    private Long tenantId;

    private String accountName;

    private String email;

    private String phone;

    private String accountType;

    private String isAdmin;

    private String isDeleted;

    private String status;
}
