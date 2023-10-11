package com.rany.acl.common.dto.role;

import com.rany.acl.common.base.DTO;
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