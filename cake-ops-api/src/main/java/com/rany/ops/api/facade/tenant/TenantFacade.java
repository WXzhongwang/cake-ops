package com.rany.ops.api.facade.tenant;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.tenant.*;
import com.rany.ops.api.query.tenant.TenantBasicQuery;
import com.rany.ops.api.query.tenant.TenantPageQuery;
import com.rany.ops.api.query.tenant.TenantQuery;
import com.rany.ops.common.dto.tenant.TenantDTO;

import java.util.List;

/**
 * 租户管理
 *
 * @author zhongshengwang
 * @description 租户管理
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public interface TenantFacade {


    /**
     * 创建租户
     *
     * @param createTenantCommand
     * @return
     */
    Long createTenant(CreateTenantCommand createTenantCommand);

    /**
     * 更新租户
     *
     * @param modifyTenantCommand
     * @return
     */
    Boolean modifyTenant(ModifyTenantCommand modifyTenantCommand);

    /**
     * 租户禁用
     *
     * @param disableTenantCommand
     * @return
     */
    Boolean disableTenant(DisableTenantCommand disableTenantCommand);

    /**
     * 启用租户
     *
     * @param enableTenantCommand
     * @return
     */
    Boolean enableTenant(EnableTenantCommand enableTenantCommand);

    /**
     * 启用租户
     *
     * @param deleteTenantCommand
     * @return
     */
    Boolean deleteTenant(DeleteTenantCommand deleteTenantCommand);


    /**
     * 查询单个租户信息
     *
     * @param tenantBasicQuery
     * @return
     */
    TenantDTO getTenant(TenantBasicQuery tenantBasicQuery);


    /**
     * 查询指定isv全部租户信息
     *
     * @param tenantQuery
     * @return
     */
    List<TenantDTO> findTenants(TenantQuery tenantQuery);

    /**
     * 分页查询租户信息
     *
     * @param tenantPageQuery
     * @return
     */
    Page<TenantDTO> pageTenants(TenantPageQuery tenantPageQuery);
}
