package com.rany.ops.api.facade.menu;

import com.rany.ops.api.command.menu.*;
import com.rany.ops.api.query.menu.MenuBasicQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;

import java.util.List;

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
     * @param createMenuCommand 创建菜单
     * @return 菜单ID
     */
    Long createMenu(CreateMenuCommand createMenuCommand);

    /**
     * 获取菜单信息
     *
     * @param menuBasicQuery 基础信息获取
     * @return
     */
    MenuDTO getMenu(MenuBasicQuery menuBasicQuery);

    /**
     * 获取菜单树信息
     *
     * @param menuTreeQuery 获取菜单树
     * @return 获取菜单树
     */
    List<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery);

    /**
     * 菜单禁用
     *
     * @param disableMenuCommand 菜单禁用
     * @return 是否成功
     */
    Boolean disableMenu(DisableMenuCommand disableMenuCommand);

    /**
     * 启用菜单
     *
     * @param enableMenuCommand 菜单启用
     * @return 是否成功
     */
    Boolean enableMenu(EnableMenuCommand enableMenuCommand);

    /**
     * 删除菜单
     *
     * @param deleteMenuCommand 菜单删除
     * @return 是否成功
     */
    Boolean deleteMenu(DeleteMenuCommand deleteMenuCommand);

    /**
     * 更新菜单基本信息
     *
     * @param modifyMenuCommand 菜单更新
     * @return 是否成功
     */
    Boolean modifyMenu(ModifyMenuCommand modifyMenuCommand);
}

