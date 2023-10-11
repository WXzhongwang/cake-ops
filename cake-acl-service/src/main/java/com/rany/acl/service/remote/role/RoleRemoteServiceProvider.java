package com.rany.acl.service.remote.role;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.role.*;
import com.rany.acl.api.facade.role.RoleFacade;
import com.rany.acl.api.query.role.RoleBasicQuery;
import com.rany.acl.api.query.role.RoleTreeQuery;
import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.dto.role.RoleTreeDTO;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.exception.enums.BusinessErrorMessage;
import com.rany.acl.common.params.RoleSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Application;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.convertor.RoleDataConvertor;
import com.rany.acl.domain.pk.RoleId;
import com.rany.acl.domain.service.ApplicationDomainService;
import com.rany.acl.domain.service.RoleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleRemoteServiceProvider implements RoleFacade {

    private final ApplicationDomainService applicationDomainService;
    private final RoleDomainService roleDomainService;
    private final RoleDataConvertor roleDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Long> createRole(CreateRoleCommand createRoleCommand) {
        Application application = applicationDomainService.findByAppCode(createRoleCommand.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        Role role = new Role(new RoleId(snowflakeIdWorker.nextId()),
                createRoleCommand.getAppCode(),
                createRoleCommand.getRoleName(),
                createRoleCommand.getRoleDesc(),
                createRoleCommand.getRoleKey());
        if (Objects.nonNull(createRoleCommand.getTenantId())) {
            role.setTenantId(createRoleCommand.getTenantId());
        }

        Role current = roleDomainService.findByRoleKey(createRoleCommand.getAppCode(),
                createRoleCommand.getTenantId(), createRoleCommand.getRoleKey());
        if (current != null) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DUPLICATED);
        }

        if (Objects.nonNull(createRoleCommand.getParentId())) {
            Role parentRole = roleDomainService.findById(new RoleId(createRoleCommand.getParentId()));
            if (parentRole == null) {
                throw new BusinessException(BusinessErrorMessage.PARENT_ROLE_NOT_FOUND);
            }
            if (StringUtils.equals(parentRole.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
                throw new BusinessException(BusinessErrorMessage.PARENT_ROLE_DISABLED);
            }
            if (StringUtils.equals(parentRole.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
                throw new BusinessException(BusinessErrorMessage.PARENT_ROLE_DISABLED);
            }
            role.setParentRoleId(createRoleCommand.getParentId());
        }
        role.save();
        roleDomainService.save(role);
        return PojoResult.succeed(role.getId().getId());
    }

    @Override
    public PojoResult<RoleDTO> getRole(RoleBasicQuery RoleBasicQuery) {
        Role role = roleDomainService.findById(new RoleId(RoleBasicQuery.getRoleId()));
        if (Objects.isNull(role)) {
            throw new BusinessException(BusinessErrorMessage.ROLE_NOT_FOUND);
        }
        if (StringUtils.equals(role.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DISABLED);
        }
        if (StringUtils.equals(role.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ROLE_DELETED);
        }
        RoleDTO roleDTO = roleDataConvertor.sourceToDTO(role);
        return PojoResult.succeed(roleDTO);
    }

    @Override
    public ListResult<RoleTreeDTO> getRoleTree(RoleTreeQuery roleTreeQuery) {
        Application application = applicationDomainService.findByAppCode(roleTreeQuery.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }

        RoleSearchParam searchParam = new RoleSearchParam();
        searchParam.setAppCode(roleTreeQuery.getAppCode());
        searchParam.setTenantId(roleTreeQuery.getTenantId());
        List<RoleDTO> roleDTOS = roleDomainService.selectRoleList(searchParam);
        List<RoleDTO> top = roleDTOS.stream().filter(p -> Objects.isNull(p.getParentId())).collect(Collectors.toList());
        List<RoleTreeDTO> treeDTO = roleDataConvertor.convertToTreeDTO(top);
        for (RoleTreeDTO menuDTO : treeDTO) {
            recursive(menuDTO, roleDTOS);
        }
        return ListResult.succeed(treeDTO);
    }

    public void recursive(RoleTreeDTO treeDTO, List<RoleDTO> menuDTOS) {
        List<RoleDTO> children = menuDTOS.stream().filter(p -> Objects.equals(treeDTO.getRoleId(), p.getParentId())).collect(Collectors.toList());
        List<RoleTreeDTO> childrenTreeItems = roleDataConvertor.convertToTreeDTO(children);
        treeDTO.setChildren(childrenTreeItems);
        for (RoleTreeDTO childrenItem : childrenTreeItems) {
            recursive(childrenItem, menuDTOS);
        }
    }

    @Override
    public PojoResult<Boolean> disableRole(DisableRoleCommand disableRoleCommand) {
        return null;
    }

    @Override
    public PojoResult<Boolean> enableRole(EnableRoleCommand enableRoleCommand) {
        return null;
    }

    @Override
    public PojoResult<Boolean> deleteRole(DeleteRoleCommand deleteRoleCommand) {
        return null;
    }

    @Override
    public PojoResult<Boolean> modifyRole(ModifyRoleCommand modifyRoleCommand) {
        return null;
    }
}
