package com.rany.ops.common.dto.menu;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuDTO extends DTO {
    private String menuId;
    private String tenantId;
    private String appCode;
    private String name;
    private String path;
    private String parentId;
    private Integer level;
    private String icon;
    private Boolean hidden;
    private Boolean isDeleted;
    private Integer sort;

    /**
     * 菜单类型
     */
    private String menuType;
}