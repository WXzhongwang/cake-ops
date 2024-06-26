package com.rany.ops.api.facade.menu;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.menu.*;
import com.rany.ops.api.query.menu.MenuBasicQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;

/**
 * 菜单管理
 *
 * @author zhongshengwang
 * @description 菜单管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface MenuFacade {


    /**
     * 创建菜单
     *
     * @param createMenuCommand
     * @return
     */
    PojoResult<Long> createMenu(CreateMenuCommand createMenuCommand);

    /**
     * 获取菜单信息
     *
     * @param menuBasicQuery
     * @return
     */
    PojoResult<MenuDTO> getMenu(MenuBasicQuery menuBasicQuery);

    /**
     * 获取菜单树信息
     *
     * @param menuTreeQuery
     * @return
     */
    ListResult<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery);

    /**
     * 菜单禁用
     *
     * @param disableMenuCommand
     * @return
     */
    PojoResult<Boolean> disableMenu(DisableMenuCommand disableMenuCommand);

    /**
     * 启用菜单
     *
     * @param enableMenuCommand
     * @return
     */
    PojoResult<Boolean> enableMenu(EnableMenuCommand enableMenuCommand);

    /**
     * 删除菜单
     *
     * @param deleteMenuCommand
     * @return
     */
    PojoResult<Boolean> deleteMenu(DeleteMenuCommand deleteMenuCommand);

    /**
     * 更新菜单基本信息
     *
     * @param modifyMenuCommand
     * @return
     */
    PojoResult<Boolean> modifyMenu(ModifyMenuCommand modifyMenuCommand);
}

