package com.rany.ops.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.domain.event.account.CreateTenantAdminAccountEvent;
import com.rany.ops.domain.dp.EmailAddress;
import com.rany.ops.domain.dp.Phone;
import com.rany.ops.domain.dp.TenantName;
import com.rany.ops.domain.dp.TenantSource;
import com.rany.ops.domain.pk.IsvId;
import com.rany.ops.domain.pk.TenantId;
import lombok.*;

/**
 * Account 聚合根
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:11
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Tenant extends BaseAggregateRoot implements IAggregate<TenantId> {
    /**
     * PK
     */
    private TenantId id;

    /**
     * isv
     */
    private IsvId isvId;
    /**
     * 租户名称名称
     */
    private TenantName tenantName;

    /**
     * 邮件地址
     */
    private EmailAddress emailAddress;

    /**
     * 邮件地址
     */
    private TenantSource source;

    /**
     * 联系电话
     */
    private Phone phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态
     */
    private String status;

    public Boolean save(Boolean initialFirstAccount) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        if (BooleanUtil.isTrue(initialFirstAccount)) {
            registerEvent(new CreateTenantAdminAccountEvent(this, DateUtil.date()));
        }
        return Boolean.TRUE;
    }

    public void disabled() {
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.DISABLED.getValue();
    }

    public void enable() {
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.ENABLE.getValue();
    }

    public void delete() {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
    }

    @Override
    public TenantId getBizID() {
        return id;
    }
}
