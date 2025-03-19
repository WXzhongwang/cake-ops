package com.rany.ops.service.remote.menu;

import com.cake.framework.common.exception.BusinessException;
import com.rany.ops.api.command.menu.*;
import com.rany.ops.api.facade.menu.MenuFacade;
import com.rany.ops.api.query.menu.MenuBasicQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.MenuSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.common.util.TreeUtil;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.domain.pk.MenuId;
import com.rany.ops.domain.service.ApplicationDomainService;
import com.rany.ops.domain.service.MenuDomainService;
import com.rany.ops.infra.convertor.MenuDataConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 菜单接口
 *
 * @author zhongshengwang
 * @description 菜单接口
 * @date 2022/12/30 23:25
 * @email 18668485565163.com
 */

@Slf4j
@Service
@AllArgsConstructor
public class MenuFacadeImpl implements MenuFacade {

    private final ApplicationDomainService applicationDomainService;
    private final MenuDomainService menuDomainService;
    private final MenuDataConvertor menuDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Long createMenu(CreateMenuCommand createMenuCommand) {
        Menu menu = new Menu(new MenuId(snowflakeIdWorker.nextId()),
                createMenuCommand.getName(),
                createMenuCommand.getAppCode(),
                createMenuCommand.getPath());
        if (Objects.nonNull(createMenuCommand.getTenantId())) {
            menu.setTenantId(createMenuCommand.getTenantId());
        }
        if (Objects.nonNull(createMenuCommand.getParentId())) {
            Menu parentMenu = menuDomainService.findById(new MenuId(createMenuCommand.getParentId()));
            if (parentMenu == null) {
                throw new BusinessException(BusinessErrorMessage.PARENT_MENU_NOT_FOUND);
            }
            if (StringUtils.equals(parentMenu.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
                throw new BusinessException(BusinessErrorMessage.PARENT_MENU_DISABLED);
            }
            if (StringUtils.equals(parentMenu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
                throw new BusinessException(BusinessErrorMessage.PARENT_MENU_DELETED);
            }
            menu.setParentId(createMenuCommand.getParentId());
            menu.setLevel(parentMenu.getLevel() + 1);
        } else {
            menu.setLevel(1);
        }
        menu.setIcon(createMenuCommand.getIcon());
        menu.setHidden(createMenuCommand.getHidden());
        menu.setParentId(createMenuCommand.getParentId());
        menu.setSort(createMenuCommand.getSort());


        menu.save(createMenuCommand.getUser());
        menuDomainService.save(menu);
        return menu.getId().getId();
    }

    @Override
    public MenuDTO getMenu(MenuBasicQuery menuBasicQuery) {
        Menu menu = menuDomainService.findById(new MenuId(menuBasicQuery.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        if (StringUtils.equals(menu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DELETED);
        }
        if (StringUtils.equals(menu.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DISABLED);
        }
        return menuDataConvertor.sourceToDTO(menu);
    }

    @Override
    public List<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery) {
        Application application = applicationDomainService.findByAppCode(menuTreeQuery.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        MenuSearchParam searchParam = new MenuSearchParam();
        searchParam.setAppCode(menuTreeQuery.getAppCode());
        searchParam.setTenantId(menuTreeQuery.getTenantId());
        List<Menu> menus = menuDomainService.selectMenuList(searchParam);
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTree(menus);
        return TreeUtil.makeTree(treeDTO, (menuTreeDTO) -> Objects.equals(menuTreeDTO.getLevel(), 1),
                (menuTreeDTO, menuTreeDTO2) -> Objects.equals(menuTreeDTO.getParentId(), menuTreeDTO2.getMenuId()), MenuTreeDTO::setChildren);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disableMenu(DisableMenuCommand disableMenuCommand) {
        Menu menu = menuDomainService.findById(new MenuId(disableMenuCommand.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        if (StringUtils.equals(menu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DELETED);
        }
        List<Menu> allSubMenuList = menuDomainService.findAllSubMenuListByMenuId(menu.getId());
        allSubMenuList.add(menu);
        for (Menu menuItem : allSubMenuList) {
            // 只有启用的菜单方需禁用
            if (StringUtils.equals(menuItem.getStatus(), CommonStatusEnum.ENABLE.getValue())) {
                menuItem.disable(disableMenuCommand.getUser());
                menuDomainService.update(menu);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean enableMenu(EnableMenuCommand enableMenuCommand) {
        Menu menu = menuDomainService.findById(new MenuId(enableMenuCommand.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        if (StringUtils.equals(menu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DELETED);
        }
        menu.enable(enableMenuCommand.getUser());
        menuDomainService.update(menu);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteMenu(DeleteMenuCommand deleteMenuCommand) {
        Menu menu = menuDomainService.findById(new MenuId(deleteMenuCommand.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        List<Menu> subMenuList = menuDomainService.findSubMenuListByMenuId(menu.getId());
        if (CollectionUtils.isNotEmpty(subMenuList)) {
            throw new BusinessException(BusinessErrorMessage.MENU_CONTAINS_CHILDREN);
        }
        menu.delete(deleteMenuCommand.getUser());
        menuDomainService.update(menu);
        return Boolean.TRUE;
    }

    @Override
    public Boolean modifyMenu(ModifyMenuCommand modifyMenuCommand) {
        Menu menu = menuDomainService.findById(new MenuId(modifyMenuCommand.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        if (StringUtils.equals(menu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DELETED);
        }
        if (StringUtils.equals(menu.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DISABLED);
        }
        if (StringUtils.isNotEmpty(modifyMenuCommand.getName())) {
            menu.setName(modifyMenuCommand.getName());
        }
        if (StringUtils.isNotEmpty(modifyMenuCommand.getPath())) {
            menu.setPath(modifyMenuCommand.getPath());
        }
        if (StringUtils.isNotEmpty(modifyMenuCommand.getIcon())) {
            menu.setIcon(modifyMenuCommand.getIcon());
        }
        if (Objects.nonNull(modifyMenuCommand.getHidden())) {
            menu.setHidden(modifyMenuCommand.getHidden());
        }
        if (Objects.nonNull(modifyMenuCommand.getSort())) {
            menu.setSort(modifyMenuCommand.getSort());
        }
        menu.modify(modifyMenuCommand.getUser());
        menuDomainService.update(menu);
        return Boolean.TRUE;
    }
}
