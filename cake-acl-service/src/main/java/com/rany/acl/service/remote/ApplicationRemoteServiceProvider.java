package com.rany.acl.service.remote;

import com.cake.framework.common.exception.CommonReturnCode;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.application.*;
import com.rany.acl.api.facade.ApplicationFacade;
import com.rany.acl.api.query.application.ApplicationBasicQuery;
import com.rany.acl.api.query.application.ApplicationPageQuery;
import com.rany.acl.api.query.application.ApplicationQuery;
import com.rany.acl.common.dto.application.ApplicationDTO;
import com.rany.acl.common.enums.AuthTypeEnum;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessErrorMessage;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.params.ApplicationPageSearchParam;
import com.rany.acl.common.params.ApplicationSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Application;
import com.rany.acl.domain.pk.ApplicationId;
import com.rany.acl.domain.service.ApplicationDomainService;
import com.rany.acl.infra.convertor.ApplicationDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.Objects;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 23:25
 * @email 18668485565163.com
 */

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationRemoteServiceProvider implements ApplicationFacade {

    private final ApplicationDomainService applicationDomainService;
    private final ApplicationDataConvertor accountDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Long> createApplication(CreateApplicationCommand createApplicationCommand) {
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
        return PojoResult.succeed(application.getId().getId());
    }

    @Override
    public PojoResult<ApplicationDTO> getApplication(ApplicationBasicQuery applicationBasicQuery) {
        Application application = applicationDomainService.findById(new ApplicationId(applicationBasicQuery.getAppId()));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DISABLED);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        ApplicationDTO accountDTO = accountDataConvertor.sourceToDTO(application);
        return PojoResult.succeed(accountDTO);
    }

    @Override
    public PojoResult<ApplicationDTO> getApplicationByAppCode(ApplicationBasicQuery accountBasicQuery) {
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
        ApplicationDTO applicationDTO = accountDataConvertor.sourceToDTO(application);
        return PojoResult.succeed(applicationDTO);
    }

    @Override
    public PojoResult<Boolean> disableApplication(DisableApplicationCommand disableApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(disableApplicationCommand.getAppId()));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        application.disable(disableApplicationCommand.getUser());
        applicationDomainService.update(application);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> enableApplication(EnableApplicationCommand enableApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(enableApplicationCommand.getAppId()));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        if (StringUtils.equals(application.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.APP_DELETED);
        }
        application.enable(enableApplicationCommand.getUser());
        applicationDomainService.update(application);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteApplication(DeleteApplicationCommand deleteApplicationCommand) {
        Application application
                = applicationDomainService.findById(new ApplicationId(deleteApplicationCommand.getAppId()));
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        application.delete(deleteApplicationCommand.getUser());
        applicationDomainService.update(application);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyApplication(ModifyApplicationCommand modifyApplicationCommand) {
        Application application = applicationDomainService.findById(new ApplicationId(modifyApplicationCommand.getAppId()));
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
        return PojoResult.succeed(Boolean.TRUE);
    }


    @Override
    public ListResult<ApplicationDTO> findApplications(ApplicationQuery applicationQuery) {
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
        return ListResult.succeed(applicationDomainService.selectApplications(searchParam));
    }

    @Override
    public PageResult<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery) {
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
        Page<ApplicationDTO> page = applicationDomainService.pageApplications(searchParam);
        return PageResult.succeed(page);
    }
}
