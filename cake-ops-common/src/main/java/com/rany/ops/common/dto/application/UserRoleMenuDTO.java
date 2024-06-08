package com.rany.ops.common.dto.application;

import com.rany.ops.common.base.DTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.dto.role.RoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 获取用户在单一应用下的角色，菜单，权限集合
 * 权限仅展示有角色菜单下的权限子集
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserRoleMenuDTO extends DTO {

    /**
     * 用户角色
     */
    private List<RoleDTO> roles;

    /**
     * 菜单树
     */
    private List<MenuTreeDTO> menuTree;
}
