package com.rany.ops.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.ops.common.Constants;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.domain.event.permission.*;
import com.rany.ops.domain.pk.PermissionId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Permission 聚合根
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
public class Permission extends BaseAggregateRoot implements IAggregate<PermissionId> {


    /**
     * 主键
     */
    private PermissionId id;
    /**
     * 应用code
     */
    private String appCode;

    private Long tenantId;

    /**
     * 资源类型，默认action
     */
    private String resourceType;

    /**
     * 资源名称
     * eg. 添加账号
     */
    private String resourceName;

    /**
     * 资源路径
     * eg. uic:account:add
     */
    private String resourcePath;

    /**
     * 权限点关联菜单
     */
    private Long refMenuId;

    private String status;

    /**
     * 关联应用
     */
    private Application application;

    public Permission(PermissionId id, String appCode, String resourceName,
                      String resourcePath,
                      Long refMenuId) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourcePath = resourcePath;
        this.appCode = appCode;
        this.refMenuId = refMenuId;
    }

    /**
     * 信息保存
     *
     * @return
     */
    public Boolean save(String user) {
        this.setResourceType(Constants.RESOURCE_TYPE_OPERATION);
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new PermissionCreatedEvent(this, this.gmtCreate));
        return Boolean.TRUE;
    }

    public Boolean disable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.registerEvent(new PermissionDisabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean enable(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.registerEvent(new PermissionEnabledEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean delete(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.registerEvent(new PermissionDeletedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    public Boolean modify(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.registerEvent(new PermissionModifiedEvent(this, this.gmtModified));
        return Boolean.TRUE;
    }

    @Override
    public PermissionId getBizID() {
        return id;
    }
}
