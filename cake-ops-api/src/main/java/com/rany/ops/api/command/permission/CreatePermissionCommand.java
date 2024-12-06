package com.rany.ops.api.command.permission;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建权限点
 *
 * @author zhongshengwang
 * @description 创建权限点
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePermissionCommand extends BaseCommand {

    /**
     * appCode
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 权限点类型
     * action
     */
    private String resourceType;

    /**
     * 权限点名称
     */
    private String resourceName;

    /**
     * 权限点路径
     */
    private String resourcePath;

    /**
     * 关联菜单Id
     */
    private Long refMenuId;
}
