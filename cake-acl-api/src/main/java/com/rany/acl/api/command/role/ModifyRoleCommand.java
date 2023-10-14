package com.rany.acl.api.command.role;

import com.rany.acl.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifyRoleCommand extends BaseCommand {

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
    private String roleKey;

    /**
     * 父级菜单id
     */
    private Long parentId;
}
