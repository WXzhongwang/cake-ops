package com.rany.ops.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.ops.common.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhongshengwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleMenu extends BaseEntity<Long> {
    private String appCode;
    private Long tenantId;
    private Long menuId;
    private Long roleId;

    public void save(String user) {
        this.gmtCreate = DateUtil.date();
        this.creator = user;
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }


    public Boolean delete(String user) {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        this.modifier = user;
        return Boolean.TRUE;
    }
}
