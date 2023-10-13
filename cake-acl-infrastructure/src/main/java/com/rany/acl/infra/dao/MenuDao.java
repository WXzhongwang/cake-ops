package com.rany.acl.infra.dao;

import com.rany.acl.common.params.MenuSearchParam;
import com.rany.acl.common.params.SubMenuSearchParam;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.infra.po.MenuPO;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface MenuDao extends BaseMapper<MenuPO> {

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    int save(Menu menu);

    /**
     * 更新
     *
     * @param menu
     * @return
     */
    int update(Menu menu);

    /**
     * 查询列表
     *
     * @param searchParam
     * @return
     */
    List<MenuPO> selectList(MenuSearchParam searchParam);

    /**
     * 查询子级菜单，包含未启用的
     *
     * @param subMenuSearchParam
     * @return
     */
    List<MenuPO> selectSubMenuList(SubMenuSearchParam subMenuSearchParam);
}
