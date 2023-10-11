package com.rany.acl.common.dto.role;

import com.rany.acl.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleTreeDTO extends DTO {
    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Long parentId;
    private String isDeleted;
    private String status;
    private String roleKey;
    private List<RoleTreeDTO> children;
}