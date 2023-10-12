package com.rany.acl.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.domain.event.app.*;
import com.rany.acl.domain.pk.ApplicationId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Application 聚合根
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
public class Application extends BaseAggregateRoot implements IAggregate<ApplicationId> {

    /**
     * 主键
     */
    private ApplicationId id;
    private String appName;
    private String appCode;
    private String authType;
    private String status;


    public Application(ApplicationId id, String appName, String appCode) {
        this.id = id;
        this.appName = appName;
        this.appCode = appCode;
    }

    /**
     * 信息保存
     *
     * @return
     */
    public Boolean save(String user) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.creator = user;
        this.modifier = user;
        this.registerEvent(new ApplicationCreatedEvent(this, this.gmtCreate));
        return Boolean.TRUE;
    }

    public Boolean disable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.registerEvent(new ApplicationDisabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean enable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new ApplicationEnabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean delete(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.registerEvent(new ApplicationDeletedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean modify(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.registerEvent(new ApplicationModifiedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }
}
