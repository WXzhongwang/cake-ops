package com.rany.acl.infra.dao;

import com.rany.acl.common.params.RoleMenuSearchParam;
import com.rany.acl.domain.entity.RoleMenu;
import com.rany.acl.infra.po.RoleMenuPO;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface RoleMenuDao extends BaseMapper<RoleMenuPO> {

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
