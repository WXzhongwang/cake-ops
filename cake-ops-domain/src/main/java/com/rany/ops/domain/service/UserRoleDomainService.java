package com.rany.ops.domain.service;

import com.rany.ops.common.params.UserRoleSearchParam;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class UserRoleDomainService {

    @Resource
    private RoleRepository roleRepository;

    public UserRole getUserRole(UserRoleSearchParam userRoleSearchParam) {
        return roleRepository.findUserRole(userRoleSearchParam);
    }

    public List<UserRole> listUserRoles(UserRoleSearchParam userRoleSearchParam) {
        return roleRepository.findUserRoles(userRoleSearchParam);
    }
    
    public Boolean save(UserRole userRole) {
        return roleRepository.saveUserRole(userRole);
    }

    public Boolean update(UserRole userRole) {
        return roleRepository.updateUserRole(userRole);
    }
}
