package com.rany.acl.api.command.role;

import com.rany.acl.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
public class ModifyRoleCommand extends DTO {

    /**
     * roleId
     */
    private Long roleId;

    /**
     * 菜单名称
     */
    private String roleName;

    /**
     * 菜单名称
     */
    private String roleDesc;

    /**
     * 父级菜单id
     */
    private Long parentId;
}
