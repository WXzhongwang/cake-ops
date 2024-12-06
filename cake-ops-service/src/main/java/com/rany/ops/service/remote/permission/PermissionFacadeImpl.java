package com.rany.ops.service.remote.permission;

import com.cake.framework.common.exception.BusinessException;
import com.rany.ops.api.command.permission.*;
import com.rany.ops.api.facade.permission.PermissionFacade;
import com.rany.ops.api.query.permission.MenuPermissionQuery;
import com.rany.ops.api.query.permission.PermissionBasicQuery;
import com.rany.ops.common.Constants;
import com.rany.ops.common.dto.permission.PermissionDTO;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.PermissionSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.aggregate.Permission;
import com.rany.ops.domain.pk.MenuId;
import com.rany.ops.domain.pk.PermissionId;
import com.rany.ops.domain.service.ApplicationDomainService;
import com.rany.ops.domain.service.MenuDomainService;
import com.rany.ops.domain.service.PermissionDomainService;
import com.rany.ops.infra.convertor.PermissionDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Objects;

/**
 * 权限点
 *
 * @author zhongshengwang
 */
@Slf4j
@Service
@AllArgsConstructor
public class PermissionFacadeImpl implements PermissionFacade {

    private final ApplicationDomainService applicationDomainService;
    private final MenuDomainService menuDomainService;
    private final PermissionDomainService permissionDomainService;
    private final PermissionDataConvertor permissionDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Long createPermission(CreatePermissionCommand createPermissionCommand) {
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
        return permission.getId().getId();
    }

    @Override
    public PermissionDTO getPermission(PermissionBasicQuery permissionBasicQuery) {
        Permission permission = permissionDomainService.findById(new PermissionId(permissionBasicQuery.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DISABLED);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        return permissionDataConvertor.sourceToDTO(permission);
    }

    @Override
    public List<PermissionDTO> listMenuPermission(MenuPermissionQuery menuPermissionQuery) {
        PermissionSearchParam searchParam = new PermissionSearchParam();
        searchParam.setRefMenuId(menuPermissionQuery.getRefMenuId());
        searchParam.setAppCode(menuPermissionQuery.getAppCode());
        searchParam.setTenantId(menuPermissionQuery.getTenantId());
        return permissionDomainService.selectPermissionList(searchParam);
    }

    @Override
    public Boolean disablePermission(DisablePermissionCommand disablePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(disablePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        permission.disable(disablePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return Boolean.TRUE;
    }

    @Override
    public Boolean enablePermission(EnablePermissionCommand enablePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(enablePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        if (StringUtils.equals(permission.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_DELETED);
        }
        permission.enable(enablePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deletePermission(DeletePermissionCommand deletePermissionCommand) {
        Permission permission = permissionDomainService.findById(new PermissionId(deletePermissionCommand.getPermissionId()));
        if (Objects.isNull(permission)) {
            throw new BusinessException(BusinessErrorMessage.PERMISSION_NOT_FOUND);
        }
        permission.delete(deletePermissionCommand.getUser());
        permissionDomainService.update(permission);
        return Boolean.TRUE;
    }

    @Override
    public Boolean modifyPermission(ModifyPermissionCommand modifyPermissionCommand) {
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
        return Boolean.TRUE;
    }
}
