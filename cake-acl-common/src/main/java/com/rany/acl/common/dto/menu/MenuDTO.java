package com.rany.acl.common.dto.menu;

import com.rany.acl.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuDTO extends DTO {
    private Long menuId;
    private Long tenantId;
    private String appCode;
    private String name;
    private String path;
    private Long parentId;
    private Integer level;
    private String icon;
    private Boolean hidden;
    private Boolean isDeleted;
    private Integer sort;
}