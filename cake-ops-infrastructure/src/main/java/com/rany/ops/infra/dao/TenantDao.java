package com.rany.ops.infra.dao;

import com.rany.ops.common.params.TenantPageSearchParam;
import com.rany.ops.common.params.TenantSearchParam;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.infra.po.TenantPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface TenantDao extends BaseMapper<TenantPO> {

    /**
     * 保存
     *
     * @param tenant
     * @return
     */
    int save(Tenant tenant);


    /**
     * 查询列表
     *
     * @param tenant
     * @return
     */
    List<TenantPO> selectList(TenantSearchParam tenant);

    List<TenantPO> findByIds(@Param("ids") List<Long> ids);

    /**
     * 分页查询
     *
     * @param searchParam
     * @return
     */
    List<TenantPO> selectPage(TenantPageSearchParam searchParam);

    /**
     * 根据简称查询租户
     *
     * @param shortName
     * @return
     */
    TenantPO selectByShortName(@Param("name") String shortName);


    /**
     * isv下租户数量
     *
     * @param isvId
     * @return
     */
    Integer selectTenantCountByIsvId(@Param("isvId") Long isvId);
}
