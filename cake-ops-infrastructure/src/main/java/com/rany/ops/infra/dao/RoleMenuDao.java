package com.rany.ops.infra.dao;

import com.rany.ops.common.params.RoleMenuSearchParam;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.infra.po.RoleMenuPO;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface RoleMenuDao {

    /**
     * 保存
     *
     * @param roleMenu
     * @return
     */
    int save(RoleMenu roleMenu);

    /**
     * 更新
     *
     * @param roleMenu
     * @return
     */
    int update(RoleMenu roleMenu);


    /**
     * 查询角色绑定的菜单
     *
     * @param searchParam
     * @return
     */
    List<RoleMenuPO> selectRoleMenuList(RoleMenuSearchParam searchParam);
}
