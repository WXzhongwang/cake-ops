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
public class UserRole extends BaseEntity<Long> {
    private String appCode;
    private Long tenantId;
    private Long userId;
    private Long roleId;


    public void save(String user) {
        this.gmtCreate = DateUtil.date();
        this.creator = user;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }

    public Boolean delete(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }
}
