package com.rany.acl.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.domain.event.role.*;
import com.rany.acl.domain.pk.RoleId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Role 聚合根
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
public class Role extends BaseAggregateRoot implements IAggregate<RoleId> {


    /**
     * 主键
     */
    private RoleId id;
    /**
     * 应用code
     */
    private String appCode;

    private Long tenantId;

    private String roleName;

    private String roleDesc;

    private String status;

    /**
     * 上级角色
     */
    private Long parentRoleId;

    /**
     * 关联应用
     */
    private Application application;

    /**
     * 角色菜单
     */
    private List<Menu> menuList;

    public Role(RoleId id, String appCode, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.appCode = appCode;
        this.roleDesc = roleDesc;
    }

    /**
     * 信息保存
     *
     * @return
     */
    public Boolean save() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new RoleCreatedEvent(this, this.gmtCreate));
        return Boolean.TRUE;
    }

    public Boolean disable() {
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.registerEvent(new RoleDisabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean enable() {
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new RoleEnabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.registerEvent(new RoleDeletedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean modify() {
        this.gmtModified = DateUtil.date();
        this.registerEvent(new RoleModifiedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }
}
