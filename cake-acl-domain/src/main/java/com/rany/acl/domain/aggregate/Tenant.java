package com.rany.acl.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.domain.dp.EmailAddress;
import com.rany.acl.domain.dp.Phone;
import com.rany.acl.domain.dp.TenantName;
import com.rany.acl.domain.dp.TenantSource;
import com.rany.acl.domain.event.CreateTenantAdminAccountEvent;
import com.rany.acl.domain.pk.IsvId;
import com.rany.acl.domain.pk.TenantId;
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
}
