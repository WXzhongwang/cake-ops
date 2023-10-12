package com.rany.acl.api.command.permission;

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
public class CreatePermissionCommand extends DTO {

    /**
     * appCode
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 角色名称
     */
    private String resourceType;

    /**
     * 角色名称
     */
    private String resourceName;

    /**
     * 角色名称
     */
    private String resourcePath;

    /**
     * 关联菜单Id
     */
    private Long refMenuId;
}
