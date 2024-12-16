package com.rany.ops.service.remote.tenant;

import cn.hutool.core.util.BooleanUtil;
import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
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
import com.rany.ops.domain.page.PageUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 租户服务
 *
 * @author zhongshengwang
 * @description 租户服务
 * @date 2022/12/29 21:10
 * @email 18668485565163.com
 */
@Slf4j
@Service
@AllArgsConstructor
public class TenantFacadeImpl implements TenantFacade {

    private final TenantDomainService tenantDomainService;
    private final IsvDomainService isvDomainService;
    private final TenantDataConvertor tenantDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    @IsvValidCheck(expression = "#createTenantCommand.isvId")
    public Long createTenant(CreateTenantCommand createTenantCommand) {
        Tenant tenant = new Tenant(new TenantId(snowflakeIdWorker.nextId()),
                new IsvId(createTenantCommand.getIsvId()),
                new TenantName(createTenantCommand.getName(), createTenantCommand.getShortName()),
                new EmailAddress(createTenantCommand.getEmail()),
                new TenantSource(createTenantCommand.getSource()),
                new Phone(createTenantCommand.getPhone()),
                createTenantCommand.getAddress(),
                CommonStatusEnum.ENABLE.getValue(), null
        );
        tenant.setIsDeleted(DeleteStatusEnum.NO.getValue());
        tenant.save(createTenantCommand.getInitialAccount(), createTenantCommand.getUser());
        tenantDomainService.save(tenant);
        return tenant.getId().getId();
    }

    @Override
    @TenantValidCheck(expression = "#modifyTenantCommand.tenantId")
    public Boolean modifyTenant(ModifyTenantCommand modifyTenantCommand) {
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
        tenant.setModifier(modifyTenantCommand.getUser());
        tenant.setGmtCreate(new Date());
        tenantDomainService.update(tenant);
        return Boolean.TRUE;
    }

    @Override
    public Boolean disableTenant(DisableTenantCommand disableTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(disableTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        tenant.disabled(disableTenantCommand.getUser());
        tenantDomainService.update(tenant);
        return Boolean.TRUE;
    }

    @Override
    public Boolean enableTenant(EnableTenantCommand enableTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(enableTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        tenant.enable(enableTenantCommand.getUser());
        tenantDomainService.update(tenant);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteTenant(DeleteTenantCommand deleteTenantCommand) {
        Tenant tenant = tenantDomainService.findById(new TenantId(deleteTenantCommand.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        tenant.delete(deleteTenantCommand.getUser());
        tenantDomainService.update(tenant);
        return Boolean.TRUE;
    }

    @Override
    public TenantDTO getTenant(TenantBasicQuery tenantBasicQuery) {
        Tenant tenant = tenantDomainService.findById(new TenantId(tenantBasicQuery.getTenantId()));
        if (Objects.isNull(tenant)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_NOT_FOUND);
        }
        if (StringUtils.equals(tenant.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.TENANT_DELETED);
        }
        return tenantDataConvertor.sourceToDTO(tenant);
    }

    @Override
//    @IsvValidCheck(expression = "#tenantQuery.isvId")
    public List<TenantDTO> findTenants(TenantQuery tenantQuery) {
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
        List<Tenant> tenants = tenantDomainService.selectTenants(searchParam);
        return tenantDataConvertor.sourceToDTO(tenants);
    }

    @Override
    public Page<TenantDTO> pageTenants(TenantPageQuery tenantPageQuery) {
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
        Page<Tenant> tenantPage = tenantDomainService.pageTenants(pageSearchParam);
        List<TenantDTO> tenantDTOS = tenantDataConvertor.sourceToDTO(new ArrayList<>(tenantPage.getItems()));
        return PageUtils.build(tenantPage, tenantDTOS);
    }
}
