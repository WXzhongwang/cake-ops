package com.rany.ops.infra.dao;

import com.rany.ops.common.params.RoleSearchParam;
import com.rany.ops.common.params.SubRoleSearchParam;
import com.rany.ops.domain.aggregate.Role;
import com.rany.ops.infra.po.RolePO;
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
public interface RoleDao {

    /**
     * 保存
     *
     * @param role
     * @return
     */
    int save(Role role);

    /**
     * 更新
     *
     * @param role
     * @return
     */
    int update(Role role);

    RolePO selectByRoleKey(@Param("appCode") String appCode,
                           @Param("tenantId") Long tenantId,
                           @Param("roleKey") String roleKey);


    /**
     * 查询列表
     *
     * @param searchParam
     * @return
     */
    List<RolePO> selectList(RoleSearchParam searchParam);


    /**
     * 查询子级菜单，包含未启用的
     *
     * @param subRoleSearchParam
     * @return
     */
    List<RolePO> selectSubRoleList(SubRoleSearchParam subRoleSearchParam);
}
