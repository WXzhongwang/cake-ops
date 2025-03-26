package com.rany.ops.api.command.grant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户角色授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GrantRolePermissionsV2Command extends BaseCommand {

    private String appCode;

    private Long tenantId;

    private Long roleId;

    private List<Long> addPermissionIds;
    private List<Long> removePermissionIds;
}
