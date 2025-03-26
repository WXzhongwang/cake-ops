package com.rany.ops.service.remote.application;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.exception.CommonReturnCode;
import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.application.*;
import com.rany.ops.api.command.grant.GrantUserRoleCommand;
import com.rany.ops.api.facade.application.ApplicationFacade;
import com.rany.ops.api.facade.grant.GrantUserRoleFacade;
import com.rany.ops.api.query.application.ApplicationBasicQuery;
import com.rany.ops.api.query.application.ApplicationPageQuery;
import com.rany.ops.api.query.application.ApplicationQuery;
import com.rany.ops.common.Constants;
import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.common.enums.AuthTypeEnum;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.ApplicationPageSearchParam;
import com.rany.ops.common.params.ApplicationSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.aggregate.Role;
import com.rany.ops.domain.pk.ApplicationId;
import com.rany.ops.domain.pk.RoleId;
import com.rany.ops.domain.service.ApplicationDomainService;
import com.rany.ops.domain.service.RoleDomainService;
import com.rany.ops.infra.convertor.ApplicationDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 应用服务
 *
 * @author zhongshengwang
 * @description 应用服务
 * @date 2022/12/30 23:25
 * @email 18668485565163.com
 */

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationFacadeImpl implements ApplicationFacade {

    private final ApplicationDomainService applicationDomainService;
    private final RoleDomainService roleDomainService;
    private final GrantUserRoleFacade grantUserRoleFacade;
    private final ApplicationDataConvertor accountDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createApplication(CreateApplicationCommand createApplicationCommand) {
        Application application = new Application(new ApplicationId(snowflakeIdWorker.nextId()),
                createApplicationCommand.getAppName(),
                createApplicationCommand.getAppCode());
        application.setAuthType(AuthTypeEnum.RBAC0.name());
        if (StringUtils.isNotEmpty(createApplicationCommand.getAppCode())) {
            if (!EnumUtils.isValidEnum(AuthTypeEnum.class, createApplicationCommand.getAuthType())) {
                throw new BusinessException(CommonReturnCode.PARAMETER_ILLEGAL);
            }
            AuthTypeEnum authTypeEnum = EnumUtils.getEnum(AuthTypeEnum.class, createApplicationCommand.getAuthType());
            application.setAuthType(authTypeEnum.name());
        }
        application.save(createApplicationCommand.getUser());
        applicationDomainService.save(application);

        // 应用创建，新建超管角色
        Role role = new Role(new RoleId(snowflakeIdWorker.nextId()),
                createApplicationCommand.getAppCode(),
                Constants.SUPER_ADMINISTRATOR_ROLE_DESC,
                Constants.SUPER_ADMINISTRATOR_ROLE_DESC,
                Constants.SUPER_ADMINISTRATOR_ROLE_KEY);

        role.save(createApplicationCommand.getUser());
        roleDomainService.save(role);

        // 创建者授予超管角色
        GrantUserRoleCommand grantUserRoleCommand = new GrantUserRoleCommand();
        grantUserRoleCommand.setAppCode(createApplicationCommand.getAppCode());
        grantUserRoleCommand.setUser(createApplicationCommand.getUser());
        grantUserRoleCommand.setAccountId(Long.valueOf(createApplicationCommand.getUser()));
        grantUserRoleCommand.setRoleId(role.getId().getId());
        grantUserRoleFacade.grantUserRole(grantUserRoleCommand);
        return application.getId().getId();
    }

    @Override
    public ApplicationDTO getApplication(ApplicationBasicQuery applicationBasicQuery) {
        Application application = applicationDomainService.findById(new ApplicationId(Long.valueOf(applicationBasicQuery.getAppId())));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        return accountDataConvertor.sourceToDTO(application);
    }

    @Override
    public ApplicationDTO getApplicationByAppCode(ApplicationBasicQuery accountBasicQuery) {
        Application application = applicationDomainService.findByAppCode(accountBasicQuery.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        return accountDataConvertor.sourceToDTO(application);
    }

    @Override
    public Boolean disableApplication(DisableApplicationCommand disableApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(Long.valueOf(disableApplicationCommand.getId())));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        application.disable(disableApplicationCommand.getUser());
        applicationDomainService.update(application);
        return true;
    }

    @Override
    public Boolean enableApplication(EnableApplicationCommand enableApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(Long.valueOf(enableApplicationCommand.getId())));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        application.enable(enableApplicationCommand.getUser());
        applicationDomainService.update(application);
        return true;
    }

    @Override
    public Boolean deleteApplication(DeleteApplicationCommand deleteApplicationCommand) {
        Application application
                = applicationDomainService.findById(new ApplicationId(Long.valueOf(deleteApplicationCommand.getId())));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        application.delete(deleteApplicationCommand.getUser());
        applicationDomainService.update(application);
        return true;
    }

    @Override
    public Boolean modifyApplication(ModifyApplicationCommand modifyApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(Long.valueOf(modifyApplicationCommand.getId())));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        if (StringUtils.isNotEmpty(modifyApplicationCommand.getAppName())) {
            application.setAppName(modifyApplicationCommand.getAppName());
        }
        if (StringUtils.isNotEmpty(modifyApplicationCommand.getAuthType())) {
            application.setAuthType(modifyApplicationCommand.getAuthType());
        }
        application.modify(modifyApplicationCommand.getUser());
        applicationDomainService.update(application);
        return true;
    }


    @Override
    public List<ApplicationDTO> findApplications(ApplicationQuery applicationQuery) {
        ApplicationSearchParam searchParam = new ApplicationSearchParam();
        if (StringUtils.isNotEmpty(applicationQuery.getAppName())) {
            searchParam.setAppName(applicationQuery.getAppName());
        }
        if (StringUtils.isNotEmpty(applicationQuery.getAppCode())) {
            searchParam.setAppCode(applicationQuery.getAppCode());
        }
        if (StringUtils.isNotEmpty(applicationQuery.getAuthType())) {
            searchParam.setAuthType(applicationQuery.getAuthType());
        }
        return applicationDomainService.selectApplications(searchParam);
    }

    @Override
    public Page<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery) {
        ApplicationPageSearchParam searchParam = new ApplicationPageSearchParam();
        searchParam.setPageNo(applicationPageQuery.getPageNo());
        searchParam.setPageSize(applicationPageQuery.getPageSize());

        if (StringUtils.isNotEmpty(applicationPageQuery.getAppName())) {
            searchParam.setAppName(applicationPageQuery.getAppName());
        }
        if (StringUtils.isNotEmpty(applicationPageQuery.getAppCode())) {
            searchParam.setAppCode(applicationPageQuery.getAppCode());
        }
        if (StringUtils.isNotEmpty(applicationPageQuery.getAuthType())) {
            searchParam.setAuthType(applicationPageQuery.getAuthType());
        }
        return applicationDomainService.pageApplications(searchParam);
    }
}
