package com.rany.acl.domain.service;

import com.rany.acl.common.dto.menu.MenuDTO;
import com.rany.acl.common.params.MenuSearchParam;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.domain.pk.MenuId;
import com.rany.acl.domain.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
