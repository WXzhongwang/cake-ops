package com.rany.ops.service.remote.tenant;

import cn.hutool.core.util.BooleanUtil;
import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.tenant.*;
import com.rany.ops.api.facade.tenant.TenantFacade;
import com.rany.ops.api.query.tenant.TenantBasicQuery;
import com.rany.ops.api.query.tenant.TenantPageQuery;
import com.rany.ops.api.query.tenant.TenantQuery;
import com.rany.ops.common.dto.tenant.TenantDTO;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.TenantPageSearchParam;
import com.rany.ops.common.params.TenantSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.domain.dp.EmailAddress;
import com.rany.ops.domain.dp.Phone;
import com.rany.ops.domain.dp.TenantName;
import com.rany.ops.domain.dp.TenantSource;
import com.rany.ops.domain.pk.IsvId;
import com.rany.ops.domain.pk.TenantId;
import com.rany.ops.domain.service.IsvDomainService;
import com.rany.ops.domain.service.TenantDomainService;
import com.rany.ops.infra.convertor.TenantDataConvertor;
import com.rany.ops.service.aop.annotation.IsvValidCheck;
import com.rany.ops.service.aop.annotation.TenantValidCheck;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.Objects;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/29 21:10
 * @email 18668485565163.com
 */
@Slf4j
@Service
//@ShenyuService("/tenant/**")
@AllArgsConstructor
public class TenantRemoteServiceProvider implements TenantFacade {

    private final TenantDomainService tenantDomainService;
    private final IsvDomainService isvDomainService;
    private final TenantDataConvertor tenantDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    @IsvValidCheck(expression = "#createTenantCommand.isvId")
    public PojoResult<Boolean> createTenant(CreateTenantCommand createTenantCommand) {
        Tenant tenant = new Tenant(new TenantId(snowflakeIdWorker.nextId()),
                new IsvId(createTenantCommand.getIsvId()),
                new TenantName(createTenantCommand.getName(), createTenantCommand.getShortName()),
                new EmailAddress(createTenantCommand.getEmail()),
                new TenantSource(createTenantCommand.getSource()),
                new Phone(createTenantCommand.getPhone()),
                createTenantCommand.getAddress(),
                CommonStatusEnum.ENABLE.getValue()
        );
        tenant.setIsDeleted(DeleteStatusEnum.NO.getValue());
        tenant.save(createTenantCommand.getInitialAccount());
        tenantDomainService.save(tenant);
        return PojoResult.succeed();
    }

    @Override
    @TenantValidCheck(expression = "#modifyTenantCommand.tenantId")
    public PojoResult<Boolean> modifyTenant(ModifyTenantCommand modifyTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(modifyTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        tenant.setTenantName(new TenantName(modifyTenantCommand.getName(), tenant.getTenantName().getShortName()));
        tenant.setEmailAddress(new EmailAddress(modifyTenantCommand.getEmail()));
        tenant.setPhone(new Phone(modifyTenantCommand.getPhone()));
        tenant.setAddress(modifyTenantCommand.getAddress());
        tenantDomainService.update(tenant);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> disableTenant(DisableTenantCommand disableTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(disableTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        tenant.disabled();
        tenantDomainService.update(tenant);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> enableTenant(EnableTenantCommand enableTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(enableTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        tenant.enable();
        tenantDomainService.update(tenant);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> deleteTenant(DeleteTenantCommand deleteTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(deleteTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        tenant.delete();
        tenantDomainService.update(tenant);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<TenantDTO> getTenant(TenantBasicQuery tenantBasicQuery) {
        Tenant tenant = tenantDomainService.findById(new TenantId(tenantBasicQuery.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        TenantDTO isvDTO = tenantDataConvertor.sourceToDTO(tenant);
        return PojoResult.succeed(isvDTO);
    }

    @Override
    @IsvValidCheck(expression = "#tenantQuery.isvId")
    public ListResult<TenantDTO> findTenants(TenantQuery tenantQuery) {
        TenantSearchParam searchParam = new TenantSearchParam();
        if (Objects.nonNull(tenantQuery.getIsvId())) {
            searchParam.setIsvId(tenantQuery.getIsvId());
        }
        if (StringUtils.isNotEmpty(tenantQuery.getName())) {
            searchParam.setName(searchParam.getName());
        }
        if (BooleanUtil.isTrue(tenantQuery.getExcludeDeleted())) {
            searchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(tenantQuery.getExcludeDisabled())) {
            searchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        return ListResult.succeed(tenantDomainService.selectTenants(searchParam));
    }

    @Override
    @IsvValidCheck(expression = "#tenantPageQuery.isvId")
    public PageResult<TenantDTO> pageTenants(TenantPageQuery tenantPageQuery) {
        TenantPageSearchParam pageSearchParam = new TenantPageSearchParam();
        pageSearchParam.setPageNo(tenantPageQuery.getPageNo());
        pageSearchParam.setPageSize(tenantPageQuery.getPageSize());
        if (Objects.nonNull(tenantPageQuery.getIsvId())) {
            pageSearchParam.setIsvId(tenantPageQuery.getIsvId());
        }
        if (StringUtils.isNotEmpty(tenantPageQuery.getName())) {
            pageSearchParam.setName(tenantPageQuery.getName());
        }
        if (BooleanUtil.isTrue(tenantPageQuery.getExcludeDeleted())) {
            pageSearchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(tenantPageQuery.getExcludeDisabled())) {
            pageSearchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        Page<TenantDTO> page = tenantDomainService.pageTenants(pageSearchParam);
        return PageResult.succeed(page);
    }
}
