package com.rany.ops.common.dto.permission;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhongshengwang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionDTO extends DTO {

    private Long permissionId;
    /**
     * 应用code
     */
    private String appCode;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 资源类型，默认action
     */
    private String resourceType;

    /**
     * 资源名称
     * eg. 添加账号
     */
    private String resourceName;

    /**
     * 资源路径
     * eg. uic:account:add
     */
    private String resourcePath;

    /**
     * 权限点关联菜单
     */
    private Long refMenuId;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否删除
     */
    private String isDeleted;
}
