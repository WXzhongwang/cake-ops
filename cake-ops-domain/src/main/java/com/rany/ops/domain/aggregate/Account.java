package com.rany.ops.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.domain.dp.AccountName;
import com.rany.ops.domain.dp.EmailAddress;
import com.rany.ops.domain.dp.HeadImage;
import com.rany.ops.domain.dp.Phone;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.domain.event.account.*;
import com.rany.ops.domain.pk.AccountId;
import com.rany.ops.domain.pk.TenantId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Account 聚合根
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:11
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Account extends BaseAggregateRoot implements IAggregate<AccountId> {

    /**
     * 主键
     */
    private AccountId id;


    /**
     * 租户Id
     */
    private TenantId tenantId;
    /**
     * 用户名称
     */
    private AccountName accountName;


    /**
     * 安全策略
     */
    private List<SafeStrategy> safeStrategies;


    /**
     * 邮箱
     */
    private EmailAddress emailAddress;


    /**
     * 电话
     */
    private Phone phone;

    /**
     * 账号类型
     */
    private String accountType;


    /**
     * 是否是租户管理员
     */
    private Boolean isAdmin;

    /**
     * 状态
     */
    private String status;


    private String lastLoginIp;
    private Date lastLoginTime;
    private String feature;
    private String dingding;
    private String qq;
    private String wechat;
    private String tags;
    private Date birthday;
    private String dingUnionId;
    private String dingUserId;
    private String workNo;

    /**
     * 头像
     */
    private HeadImage headImage;

    private Tenant tenant;


    public Account(AccountId id, TenantId tenantId, AccountName accountName, List<SafeStrategy> safeStrategies) {
        this.id = id;
        this.tenantId = tenantId;
        this.accountName = accountName;
        this.safeStrategies = safeStrategies;
    }

    /**
     * 账号信息保存
     */
    public void save(String user) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.creator = user;
        this.modifier = user;
        this.registerEvent(new AccountCreatedEvent(this, this.gmtCreate));
    }

    public void disable(String modifier) {
        this.gmtModified = DateUtil.date();
        this.modifier = modifier;
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.registerEvent(new AccountDisabledEvent(this, this.gmtModified));
    }

    public void enable(String modifier) {
        this.gmtModified = DateUtil.date();
        this.modifier = modifier;
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new AccountEnabledEvent(this, this.gmtModified));
    }

    public void delete(String modifier) {
        this.gmtModified = DateUtil.date();
        this.modifier = modifier;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.registerEvent(new AccountDeletedEvent(this, this.gmtModified));
    }

    public void modify(String modifier) {
        this.gmtModified = DateUtil.date();
        this.modifier = modifier;
        this.registerEvent(new AccountModifiedEvent(this, this.gmtModified));
    }

    @Override
    public AccountId getBizID() {
        return id;
    }
}
