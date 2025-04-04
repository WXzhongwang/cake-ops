package com.rany.ops.common.dto.account;

import com.rany.ops.common.base.DTO;
import com.rany.ops.common.dto.tenant.TenantDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/7 21:32
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDTO extends DTO {
    private String id;
    private String accountName;
    private String phone;
    private String email;
    private String tenantId;
    private Boolean isAdmin;
    private String accountType;
    private String status;
    private String isDeleted;
    private String lastLoginIp;
    private Date lastLoginTime;
    private String feature;
    private Date gmtCreate;
    private Date gmtModified;
    private String headImage;
    private String dingding;
    private String qq;
    private String wechat;
    private Date birthday;
    private String tags;
    private TenantDTO tenant;
}
