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
public class ModifyPermissionCommand extends DTO {

    /**
     * appCode
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * permissionId
     */
    private Long permissionId;


    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String resourcePath;
}
