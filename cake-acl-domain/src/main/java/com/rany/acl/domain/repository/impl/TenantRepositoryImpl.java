package com.rany.acl.domain.repository.impl;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.acl.common.dto.tenant.TenantDTO;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.params.TenantPageSearchParam;
import com.rany.acl.common.params.TenantSearchParam;
import com.rany.acl.domain.repository.TenantRepository;
import com.rany.acl.infra.mapper.TenantPOMapper;
import com.rany.acl.domain.aggregate.Tenant;
import com.rany.acl.domain.convertor.TenantDataConvertor;
import com.rany.acl.domain.dao.TenantDao;
import com.rany.acl.domain.page.annotation.PagingQuery;
import com.rany.acl.domain.pk.IsvId;
import com.rany.acl.domain.pk.TenantId;
import com.rany.acl.infra.po.TenantPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:18
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Slf4j
@Service
public class TenantRepositoryImpl implements TenantRepository {

    private final TenantPOMapper tenantPOMapper;
    private final TenantDao tenantDao;
    private final TenantDataConvertor tenantDataConvertor;

    @Override
    public Tenant find(@NotNull TenantId accountId) {
        TenantPO tenantPO = tenantPOMapper.selectByPrimaryKey(accountId.getId());
        return tenantDataConvertor.targetToSource(tenantPO);
    }

    @Override
    public void remove(@NotNull Tenant tenant) {
        TenantId tenantId = tenant.getId();
        TenantPO tenantPO = tenantPOMapper.selectByPrimaryKey(tenantId.getId());
        tenantPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        tenantPO.setGmtModified(DateUtil.date());
        tenantPOMapper.updateByPrimaryKeySelective(tenantPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Tenant tenant) {
        tenantDao.save(tenant);
    }

    @Override
    public TenantPO selectByShortName(String shortName) {
        return tenantDao.selectByShortName(shortName);
    }

    @Override
    public Integer selectTenantCountByIsvId(IsvId isvId) {
        return tenantDao.selectTenantCountByIsvId(isvId.getId());
    }

    @Override
    public Boolean updateTenant(Tenant tenant) {
        TenantPO tenantPO = tenantDataConvertor.sourceToTarget(tenant);
        tenantPO.setGmtModified(DateUtil.date());
        tenantPOMapper.updateByPrimaryKeySelective(tenantPO);
        return Boolean.TRUE;
    }

    @Override
    public List<TenantDTO> findTenants(TenantSearchParam tenant) {
        List<TenantPO> tenantPOS = tenantDao.selectList(tenant);
        return tenantDataConvertor.targetToDTO(tenantPOS);
    }

    @Override
    @PagingQuery
    public Page<TenantDTO> pageTenants(TenantPageSearchParam tenantPageSearchParam) {
        List<TenantPO> content = tenantDao.selectPage(tenantPageSearchParam);
        PageInfo<TenantPO> pageInfo = new PageInfo<>(content);
        Page<TenantDTO> pageDTO = new Page<>();
        pageDTO.setPageNo(pageInfo.getPageNum());
        pageDTO.setPageSize(pageInfo.getPageSize());
        pageDTO.setTotalPage(pageInfo.getPages());
        pageDTO.setTotal(Long.valueOf(pageInfo.getTotal()).intValue());
        List<TenantDTO> values = tenantDataConvertor.targetToDTO(pageInfo.getList());
        pageDTO.setItems(values);
        return pageDTO;
    }
}
