package com.rany.acl.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.acl.common.enums.DeleteStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends BaseEntity<Long> {
    private String appCode;
    private Long tenantId;
    private Long userId;
    private Long roleId;

    public UserRole(String appCode, Long tenantId, Long userId, Long roleId) {
        this.appCode = appCode;
        this.tenantId = tenantId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        // this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }
}
