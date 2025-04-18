package com.rany.ops.domain.service;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.TenantPageSearchParam;
import com.rany.ops.common.params.TenantSearchParam;
import com.rany.ops.domain.aggregate.Isv;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.domain.pk.TenantId;
import com.rany.ops.domain.repository.IsvRepository;
import com.rany.ops.domain.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class TenantDomainService {
    @Resource
    private IsvRepository isvRepository;
    @Resource
    private TenantRepository tenantRepository;

    public Boolean save(Tenant tenant) {
        Isv isv = isvRepository.find(tenant.getIsvId());
        if (Objects.isNull(isv) || StringUtils.equals(isv.getIsDeleted(), DeleteStatusEnum.YES.getValue())
                || StringUtils.equals(isv.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ISV_INVALID);
        }
        Tenant tenantPO = tenantRepository.selectByShortName(tenant.getTenantName().getShortName());
        if (Objects.nonNull(tenantPO)) {
            throw new BusinessException(BusinessErrorMessage.TENANT_SHORT_NAME_EXISTED);
        }
        Integer tenantsCount = tenantRepository.selectTenantCountByIsvId(isv.getId());
        if (isv.getMaxTenants() <= tenantsCount) {
            throw new BusinessException(BusinessErrorMessage.TENANT_OVER_MAX_SIZE);
        }
        tenantRepository.save(tenant);
        return Boolean.TRUE;
    }

    public Tenant findById(TenantId tenantId) {
        return tenantRepository.find(tenantId);
    }

    public Boolean update(Tenant tenant) {
        return tenantRepository.updateTenant(tenant);
    }

    public List<Tenant> selectTenants(TenantSearchParam searchParam) {

        return tenantRepository.findTenants(searchParam);
    }

    public Page<Tenant> pageTenants(TenantPageSearchParam pageSearchParam) {
        Page<Tenant> tenantDTOPage = tenantRepository.pageTenants(pageSearchParam);
        Collection<Tenant> items = tenantDTOPage.getItems();
        List<Isv> isvPOList = isvRepository.findByIds(items.stream().map(p -> p.getIsvId().getId()).collect(Collectors.toList()));
        Map<Long, Isv> isvMap = isvPOList.stream().collect(Collectors.toMap(x -> x.getId().getId(), isvPO -> isvPO));
        for (Tenant value : items) {
            Isv isv = isvMap.get(value.getIsvId().getId());
            if (Objects.isNull(isv)) {
                continue;
            }
            value.setIsv(isv);
        }
        return tenantDTOPage;
    }
}
