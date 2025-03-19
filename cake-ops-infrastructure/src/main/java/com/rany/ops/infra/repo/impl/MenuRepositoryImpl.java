package com.rany.ops.infra.repo.impl;

import com.rany.ops.common.params.MenuSearchParam;
import com.rany.ops.common.params.SubMenuSearchParam;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.pk.MenuId;
import com.rany.ops.domain.repository.MenuRepository;
import com.rany.ops.infra.convertor.MenuDataConvertor;
import com.rany.ops.infra.dao.MenuDao;
import com.rany.ops.infra.mapper.MenuPOMapper;
import com.rany.ops.infra.po.MenuPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * menu repo
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:18
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Slf4j
@Service
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuPOMapper menuMapper;
    private final MenuDao menuDao;
    private final MenuDataConvertor menuDataConvertor;

    @Override
    public Menu find(@NotNull MenuId roleId) {
        MenuPO menuPO = menuMapper.selectByPrimaryKey(roleId.getId());
        return menuDataConvertor.targetToSource(menuPO);
    }

    @Override
    public void remove(@NotNull Menu role) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuDao.update(menu);
    }

    @Override
    public List<Menu> findMenus(MenuSearchParam menuSearchParam) {
        List<MenuPO> menuPOS = menuDao.selectList(menuSearchParam);
        return menuDataConvertor.targetToSource(menuPOS);
    }

    @Override
    public List<Menu> findSubMenus(SubMenuSearchParam subMenuSearchParam) {
        List<MenuPO> menuPOS = menuDao.selectSubMenuList(subMenuSearchParam);
        return menuDataConvertor.targetToSource(menuPOS);
    }
}
