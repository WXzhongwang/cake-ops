package com.rany.ops.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.params.TenantPageSearchParam;
import com.rany.ops.common.params.TenantSearchParam;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.domain.pk.IsvId;
import com.rany.ops.domain.pk.TenantId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface TenantRepository extends Repository<Tenant, TenantId> {

    /**
     * 获取租户简称
     *
     * @param shortName
     * @return
     */
    Tenant selectByShortName(String shortName);

    /**
     * 获取租户数量
     *
     * @param isvId
     * @return
     */
    Integer selectTenantCountByIsvId(IsvId isvId);


    /**
     * 更新租户信息
     *
     * @param tenant
     * @return
     */
    Boolean updateTenant(Tenant tenant);

    List<Tenant> findByIds(List<Long> tenantIds);


    /**
     * 查询列表
     *
     * @param tenant
     * @return
     */
    List<Tenant> findTenants(TenantSearchParam tenant);


    /**
     * 分页查询租户
     *
     * @param tenant
     * @return
     */
    Page<Tenant> pageTenants(TenantPageSearchParam tenant);
}
