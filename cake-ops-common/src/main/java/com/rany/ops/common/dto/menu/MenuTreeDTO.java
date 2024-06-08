package com.rany.ops.common.dto.menu;

import com.rany.ops.common.base.DTO;
import com.rany.ops.common.dto.permission.PermissionDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuTreeDTO extends DTO {
    private Long menuId;
    private String name;
    private String path;
    private Long parentId;
    private Integer level;
    private String icon;
    private Boolean hidden;
    private Boolean isDeleted;
    private Integer sort;

    private List<MenuTreeDTO> children;

    /**
     * 权限集合
     */
    private List<PermissionDTO> permissions;
}