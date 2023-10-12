package com.rany.acl.api.command.role;

import com.rany.acl.common.base.BaseCommand;
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
public class CreateRoleCommand extends BaseCommand {

    /**
     * 账号名
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色名称
     */
    private String roleDesc;

    /**
     * 角色唯一标识
     */
    private String roleKey;

    /**
     * 父级角色id
     */
    private Long parentId;
}
