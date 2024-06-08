package com.rany.ops.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.ops.common.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends BaseEntity<Long> {
    private String appCode;
    private Long tenantId;
    private Long roleId;
    private Long permissionId;

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        // this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }
}
