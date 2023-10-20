package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.rany.acl.api.facade.RbacQueryFacade;
import com.rany.acl.api.query.UserRoleMenuPermissionQuery;
import com.rany.acl.common.dto.UserRoleMenuPermissionDTO;
import com.rany.acl.domain.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RbacQueryRemoteProvider implements RbacQueryFacade {

    private final ApplicationDomainService applicationDomainService;
    private final UserRoleDomainService userRoleDomainService;
    private final RoleMenuDomainService roleMenuDomainService;
    private final PermissionDomainService permissionDomainService;
    private final RolePermissionDomainService rolePermissionDomainService;

    @Override
    public UserRoleMenuPermissionDTO getUserRbacModel(UserRoleMenuPermissionQuery query) {
        return null;
    }
}
