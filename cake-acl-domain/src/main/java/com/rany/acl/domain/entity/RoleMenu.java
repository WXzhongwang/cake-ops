package com.rany.acl.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.acl.common.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleMenu extends BaseEntity<Long> {
    private String appCode;
    private Long tenantId;
    private Long userId;
    private Long roleId;

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        // this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }
}
