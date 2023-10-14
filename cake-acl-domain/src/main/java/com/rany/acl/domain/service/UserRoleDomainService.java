package com.rany.acl.domain.service;

import com.rany.acl.common.params.UserRoleSearchParam;
import com.rany.acl.domain.entity.UserRole;
import com.rany.acl.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class UserRoleDomainService {

    @Resource
    private RoleRepository roleRepository;

    public UserRole getUserRole(UserRoleSearchParam userRoleSearchParam) {
        return roleRepository.findUserRole(userRoleSearchParam);
    }

    public Boolean save(UserRole userRole) {
        return roleRepository.saveUserRole(userRole);
    }

    public Boolean update(UserRole userRole) {
        return roleRepository.saveUserRole(userRole);
    }
}
