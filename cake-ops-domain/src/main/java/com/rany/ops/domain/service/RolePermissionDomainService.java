package com.rany.ops.domain.service;

import com.rany.ops.common.params.RolePermissionSearchParam;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class RolePermissionDomainService {

    @Resource
    private RoleRepository roleRepository;

    public List<RolePermission> getRolePermissions(RolePermissionSearchParam rolePermissionSearchParam) {
        return roleRepository.findRolePermissions(rolePermissionSearchParam);
    }

    public Boolean save(RolePermission rolePermission) {
        return roleRepository.saveRolePermission(rolePermission);
    }

    public Boolean update(RolePermission rolePermission) {
        return roleRepository.updateRolePermission(rolePermission);
    }
}
