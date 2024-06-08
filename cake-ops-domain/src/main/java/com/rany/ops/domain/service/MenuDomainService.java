package com.rany.ops.domain.service;

import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.params.MenuSearchParam;
import com.rany.ops.common.params.SubMenuSearchParam;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.pk.MenuId;
import com.rany.ops.domain.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class MenuDomainService {

    @Resource
    private MenuRepository menuRepository;


    public Menu findById(MenuId menuId) {
        return menuRepository.find(menuId);
    }

    public List<Menu> findSubMenuListByMenuId(MenuId menuId) {
        SubMenuSearchParam menuSearchParam = new SubMenuSearchParam();
        menuSearchParam.setMenuId(menuId.getId());
        return menuRepository.findSubMenus(menuSearchParam);
    }

    /**
     * 获取下级全部菜单列表，全部层级，包含未启用的
     *
     * @param menuId
     * @return
     */
    public List<Menu> findAllSubMenuListByMenuId(MenuId menuId) {
        List<Menu> menus = new ArrayList<>();
        recursive(menuId.getId(), menus);
        return menus;
    }


    public void recursive(Long menuId, List<Menu> menus) {
        SubMenuSearchParam menuSearchParam = new SubMenuSearchParam();
        menuSearchParam.setMenuId(menuId);
        List<Menu> subMenus = menuRepository.findSubMenus(menuSearchParam);
        menus.addAll(subMenus);
        if (CollectionUtils.isNotEmpty(subMenus)) {
            for (Menu subMenu : subMenus) {
                recursive(subMenu.getId().getId(), menus);
            }
        }
    }

    public List<MenuDTO> selectMenuList(MenuSearchParam searchParam) {
        return menuRepository.findMenus(searchParam);
    }

    public Boolean save(Menu menu) {
        menuRepository.save(menu);
        return Boolean.TRUE;
    }

    public Boolean update(Menu menu) {
        menuRepository.update(menu);
        return Boolean.TRUE;
    }
}
