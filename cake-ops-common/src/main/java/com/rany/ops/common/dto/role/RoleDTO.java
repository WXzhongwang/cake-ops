package com.rany.ops.common.dto.role;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends DTO {
    private Long roleId;
    private String roleName;
    private String roleDesc;
    private String roleKey;
    private Long parentId;
    private String isDeleted;
    private String status;
}