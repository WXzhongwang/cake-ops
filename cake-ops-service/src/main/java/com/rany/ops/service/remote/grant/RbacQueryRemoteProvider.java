package com.rany.ops.service.remote.grant;

import com.alibaba.dubbo.config.annotation.Service;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.dto.application.UserRoleMenuPermissionDTO;
import com.rany.ops.domain.service.*;
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
