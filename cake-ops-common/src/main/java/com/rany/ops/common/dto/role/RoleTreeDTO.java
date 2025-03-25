package com.rany.ops.common.dto.role;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleTreeDTO extends DTO {
    private String roleId;
    private String roleName;
    private String roleDesc;
    private String parentId;
    private String isDeleted;
    private String status;
    private String roleKey;
    private List<RoleTreeDTO> children;
}