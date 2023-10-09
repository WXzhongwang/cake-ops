package com.rany.acl.domain.event.app;

import com.cake.framework.common.event.DomainEvent;
import com.rany.acl.domain.aggregate.Application;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:26
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationEnabledEvent extends DomainEvent {

    private Application application;
    private Date eventTime;

    public ApplicationEnabledEvent(Application application, Date eventTime) {
        this.application = application;
        this.eventTime = eventTime;
    }
}
