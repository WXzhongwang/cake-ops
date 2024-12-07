package com.rany.ops.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.domain.dp.EmailAddress;
import com.rany.ops.domain.dp.IsvName;
import com.rany.ops.domain.dp.Phone;
import com.rany.ops.domain.pk.IsvId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Account 聚合根
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:11
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Isv extends BaseAggregateRoot implements IAggregate<IsvId> {
    private IsvId id;
    /**
     * isv名称
     */
    private IsvName name;

    /**
     * 地址
     */
    private EmailAddress emailAddress;

    /**
     * 手机号
     */
    private Phone phone;

    /**
     * 租户个数
     */
    private Integer maxTenants;

    /**
     * url
     */
    private String url;

    /**
     * 国家
     */
    private String country;


    /**
     * 状态
     */
    private String status;

    /**
     * ip地址
     */
    private String registerIp;
    private String address;

    public Isv() {
    }

    public Isv(IsvId isvId, IsvName isvName, EmailAddress emailAddress, Phone phone) {
        this.id = isvId;
        this.name = isvName;
        this.emailAddress = emailAddress;
        this.phone = phone;
    }

    /**
     * 启用
     */
    public void enable(String user) {
        this.modifier = user;
        this.gmtModified = new Date();
        this.status = CommonStatusEnum.ENABLE.getValue();
    }

    /**
     * 禁用
     */
    public void disable(String user) {
        this.modifier = user;
        this.gmtModified = new Date();
        this.status = CommonStatusEnum.DISABLED.getValue();
    }

    /**
     * 删除
     */
    public void delete(String user) {
        this.modifier = user;
        this.gmtModified = new Date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
    }

    @Override
    public IsvId getBizID() {
        return id;
    }
}
