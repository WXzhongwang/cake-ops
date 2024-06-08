package com.rany.ops.domain.event.role;

import com.cake.framework.common.event.DomainEvent;
import com.rany.ops.domain.aggregate.Role;
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
public class RoleModifiedEvent extends DomainEvent {

    private Role role;
    private Date eventTime;

    public RoleModifiedEvent(Role role, Date eventTime) {
        this.role = role;
        this.eventTime = eventTime;
    }
}
