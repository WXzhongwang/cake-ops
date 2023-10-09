package com.rany.acl.domain.event.menu;

import com.cake.framework.common.event.DomainEvent;
import com.rany.acl.domain.aggregate.Menu;
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
public class MenuCreatedEvent extends DomainEvent {

    private Menu menu;
    private Date eventTime;

    public MenuCreatedEvent(Menu menu, Date eventTime) {
        this.menu = menu;
        this.eventTime = eventTime;
    }
}
