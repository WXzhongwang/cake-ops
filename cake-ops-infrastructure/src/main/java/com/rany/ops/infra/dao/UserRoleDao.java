package com.rany.ops.infra.dao;

import com.rany.ops.common.params.UserRoleSearchParam;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.infra.po.UserRolePO;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface UserRoleDao {

    /**
     * 保存
     *
     * @param userRole
     * @return
     */
    int save(UserRole userRole);

    /**
     * 更新
     *
     * @param userRole
     * @return
     */
    int update(UserRole userRole);


    /**
     * 查询用户单个角色
     *
     * @param searchParam
     * @return
     */
    UserRolePO selectUserRole(UserRoleSearchParam searchParam);


    /**
     * 查询用户所有的角色
     *
     * @param searchParam
     * @return
     */
    List<UserRolePO> selectUserRoleList(UserRoleSearchParam searchParam);
}
