package com.rany.acl.domain.repository.impl;

import com.rany.acl.common.dto.menu.MenuDTO;
import com.rany.acl.common.params.MenuSearchParam;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.domain.convertor.MenuDataConvertor;
import com.rany.acl.domain.dao.MenuDao;
import com.rany.acl.domain.pk.MenuId;
import com.rany.acl.domain.repository.MenuRepository;
import com.rany.acl.infra.mapper.MenuPOMapper;
import com.rany.acl.infra.po.MenuPO;
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
    public List<MenuDTO> findMenus(MenuSearchParam menuSearchParam) {
        List<MenuPO> menuPOS = menuDao.selectList(menuSearchParam);
        return menuDataConvertor.targetToDTO(menuPOS);
    }
}
