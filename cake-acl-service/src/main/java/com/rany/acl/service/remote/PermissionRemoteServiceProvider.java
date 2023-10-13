package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.permission.*;
import com.rany.acl.api.facade.PermissionFacade;
import com.rany.acl.api.query.permission.PermissionBasicQuery;
import com.rany.acl.common.Constants;
import com.rany.acl.common.dto.permission.PermissionDTO;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.exception.enums.BusinessErrorMessage;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Application;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.domain.aggregate.Permission;
import com.rany.acl.domain.pk.MenuId;
import com.rany.acl.domain.pk.PermissionId;
import com.rany.acl.domain.service.ApplicationDomainService;
import com.rany.acl.domain.service.MenuDomainService;
import com.rany.acl.domain.service.PermissionDomainService;
import com.rany.acl.infra.convertor.PermissionDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionRemoteServiceProvider implements PermissionFacade {

    private final ApplicationDomainService applicationDomainService;
    private final MenuDomainService menuDomainService;
    private final PermissionDomainService permissionDomainService;
    private final PermissionDataConvertor permissionDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Long> createPermission(CreatePermissionCommand createPermissionCommand) {
        Application application = applicationDomainService.findByAppCode(createPermissionCommand.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        if (!StringUtils.equals(createPermissionCommand.getResourceType(), Constants.RESOURCE_TYPE_ACTION)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_RESOURCE_TYPE_ILLEGAL);
        }
        if (Objects.isNull(createPermissionCommand.getRefMenuId())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_REF_MENU_REQUIRED);
        }
        Menu refMenu = menuDomainService.findById(new MenuId(createPermissionCommand.getRefMenuId()));
        if (refMenu == null) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_REF_MENU_NOT_FOUND);
        }
        if (!StringUtils.equals(refMenu.getAppCode(), application.getAppCode())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_REF_MENU_NOT_MATCHED);
        }

        Permission permission = new Permission(new PermissionId(snowflakeIdWorker.nextId()),
                createPermissionCommand.getAppCode(),
                createPermissionCommand.getResourceName(),
                createPermissionCommand.getResourcePath(),
                createPermissionCommand.getRefMenuId());
        if (Objects.nonNull(createPermissionCommand.getTenantId())) {
            permission.setTenantId(createPermissionCommand.getTenantId());
        }
        permission.save(createPermissionCommand.getUser());
        permissionDomainService.save(permission);
        return PojoResult.succeed(permission.getId().getId());
    }

    @Override
    public PojoResult<PermissionDTO> getPermission(PermissionBasicQuery PermissionBasicQuery) {
        Permission Permission = permissionDomainService.findById(new PermissionId(PermissionBasicQuery.getPermissionId()));
        if (Objects.isNull(Permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(Permission.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DISABLED);
        }
        if (StringUtils.equals(Permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        PermissionDTO permissionDTO = permissionDataConvertor.sourceToDTO(Permission);
        return PojoResult.succeed(permissionDTO);
    }

    @Override
    public PojoResult<Boolean> disablePermission(DisablePermissionCommand disablePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(disablePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        permission.disable(disablePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> enablePermission(EnablePermissionCommand enablePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(enablePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        permission.enable(enablePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deletePermission(DeletePermissionCommand deletePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(deletePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        permission.delete(deletePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyPermission(ModifyPermissionCommand modifyPermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(modifyPermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        if (StringUtils.equals(permission.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DISABLED);
        }
        if (StringUtils.isNotEmpty(modifyPermissionCommand.getResourceName())) {
            permission.setResourceName(modifyPermissionCommand.getResourceName());
        }
        if (StringUtils.isNotEmpty(modifyPermissionCommand.getResourceType())) {
            permission.setResourceType(modifyPermissionCommand.getResourceType());
        }
        if (StringUtils.isNotEmpty(modifyPermissionCommand.getResourcePath())) {
            permission.setResourcePath(modifyPermissionCommand.getResourcePath());
        }
        permission.modify(modifyPermissionCommand.getUser());
        permissionDomainService.update(permission);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
