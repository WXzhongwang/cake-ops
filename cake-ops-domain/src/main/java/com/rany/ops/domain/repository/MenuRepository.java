package com.rany.ops.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.params.MenuSearchParam;
import com.rany.ops.common.params.SubMenuSearchParam;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.pk.MenuId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface MenuRepository extends Repository<Menu, MenuId> {

    /**
     * 菜单更新
     *
     * @param menu
     * @return
     */
    int update(Menu menu);

    /**
     * 按应用搜索菜单列表，全部层级平铺
     *
     * @param menuSearchParam
     * @return
     */
    List<MenuDTO> findMenus(MenuSearchParam menuSearchParam);

    /**
     * 查询下级菜单列表，包含未启用的
     *
     * @param menuSearchParam
     * @return
     */
    List<Menu> findSubMenus(SubMenuSearchParam menuSearchParam);
}
