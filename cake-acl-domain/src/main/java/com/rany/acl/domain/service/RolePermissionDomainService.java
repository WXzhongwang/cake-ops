package com.rany.acl.domain.service;

import com.rany.acl.common.params.RolePermissionSearchParam;
import com.rany.acl.domain.entity.RolePermission;
import com.rany.acl.domain.repository.RoleRepository;
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
