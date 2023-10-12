package com.rany.acl.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.domain.event.menu.MenuCreatedEvent;
import com.rany.acl.domain.event.menu.MenuDisabledEvent;
import com.rany.acl.domain.event.menu.MenuModifiedEvent;
import com.rany.acl.domain.pk.MenuId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Menu 聚合根
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
public class Menu extends BaseAggregateRoot implements IAggregate<MenuId> {


    /**
     * 主键
     */
    private MenuId id;
    /**
     * 应用code
     */
    private String appCode;

    private Long tenantId;

    private String name;

    private String path;

    private String status;

    private Long parentId;

    private Integer level;

    private String icon;

    private Boolean hidden;

    private Integer sort;

    /**
     * 关联应用
     */
    private Application application;

    /**
     * 子菜单
     */
    private List<Menu> subMenus;


    /**
     * 页面关联权限点
     */
    private List<Permission> permissions;

    public Menu(MenuId id, String name, String appCode, String path) {
        this.id = id;
        this.name = name;
        this.appCode = appCode;
        this.path = path;
    }

    /**
     * 信息保存
     *
     * @return
     */
    public Boolean save(String user) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.creator = user;
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new MenuCreatedEvent(this, this.gmtCreate));
        return Boolean.TRUE;
    }

    public Boolean disable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.registerEvent(new MenuDisabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean enable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new MenuCreatedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean delete(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.registerEvent(new MenuCreatedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean modify(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.registerEvent(new MenuModifiedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }
}
