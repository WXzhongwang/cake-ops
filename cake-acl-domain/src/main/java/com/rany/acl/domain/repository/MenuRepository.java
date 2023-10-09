package com.rany.acl.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.acl.common.dto.menu.MenuDTO;
import com.rany.acl.common.params.MenuSearchParam;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.domain.pk.MenuId;

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

    List<MenuDTO> findMenus(MenuSearchParam menuSearchParam);
}
