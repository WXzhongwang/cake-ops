package com.rany.acl.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.menu.*;
import com.rany.acl.api.facade.MenuFacade;
import com.rany.acl.api.query.menu.MenuBasicQuery;
import com.rany.acl.api.query.menu.MenuTreeQuery;
import com.rany.acl.common.dto.menu.MenuDTO;
import com.rany.acl.common.dto.menu.MenuTreeDTO;
import com.rany.acl.common.enums.CommonStatusEnum;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.common.exception.BusinessException;
import com.rany.acl.common.exception.enums.BusinessErrorMessage;
import com.rany.acl.common.params.MenuSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Application;
import com.rany.acl.domain.aggregate.Menu;
import com.rany.acl.domain.convertor.MenuDataConvertor;
import com.rany.acl.domain.pk.MenuId;
import com.rany.acl.domain.service.ApplicationDomainService;
import com.rany.acl.domain.service.MenuDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 23:25
 * @email 18668485565163.com
 */

@Slf4j
@Service
@AllArgsConstructor
public class MenuRemoteServiceProvider implements MenuFacade {

    private final ApplicationDomainService applicationDomainService;
    private final MenuDomainService menuDomainService;
    private final MenuDataConvertor menuDataConvertor;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Long> createMenu(CreateMenuCommand createMenuCommand) {
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
        return PojoResult.succeed(menu.getId().getId());
    }

    @Override
    public PojoResult<MenuDTO> getMenu(MenuBasicQuery menuBasicQuery) {
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
        MenuDTO menuDTO = menuDataConvertor.sourceToDTO(menu);
        return PojoResult.succeed(menuDTO);
    }

    @Override
    public ListResult<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery) {
        Application application = applicationDomainService.findByAppCode(menuTreeQuery.getAppCode());
        if (Objects.isNull(application)) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        MenuSearchParam searchParam = new MenuSearchParam();
        searchParam.setAppCode(menuTreeQuery.getAppCode());
        searchParam.setTenantId(menuTreeQuery.getTenantId());
        List<MenuDTO> menuDTOS = menuDomainService.selectMenuList(searchParam);
        List<MenuDTO> top = menuDTOS.stream().filter(p -> Objects.equals(p.getLevel(), 1)).collect(Collectors.toList());
        List<MenuTreeDTO> treeDTO = menuDataConvertor.convertToTreeDTO(top);
        for (MenuTreeDTO menuDTO : treeDTO) {
            recursive(menuDTO, menuDTOS);
        }
        return ListResult.succeed(treeDTO);
    }

    public void recursive(MenuTreeDTO treeDTO, List<MenuDTO> menuDTOS) {
        List<MenuDTO> children = menuDTOS.stream().filter(p -> Objects.equals(treeDTO.getMenuId(), p.getParentId())).collect(Collectors.toList());
        List<MenuTreeDTO> childrenTreeItems = menuDataConvertor.convertToTreeDTO(children);
        treeDTO.setChildren(childrenTreeItems);
        for (MenuTreeDTO childrenItem : childrenTreeItems) {
            recursive(childrenItem, menuDTOS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PojoResult<Boolean> disableMenu(DisableMenuCommand disableMenuCommand) {
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
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> enableMenu(EnableMenuCommand enableMenuCommand) {
        Menu menu = menuDomainService.findById(new MenuId(enableMenuCommand.getMenuId()));
        if (Objects.isNull(menu)) {
            throw new BusinessException(BusinessErrorMessage.MENU_NOT_FOUND);
        }
        if (StringUtils.equals(menu.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.MENU_DELETED);
        }
        menu.enable(enableMenuCommand.getUser());
        menuDomainService.update(menu);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteMenu(DeleteMenuCommand deleteMenuCommand) {
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
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> modifyMenu(ModifyMenuCommand modifyMenuCommand) {
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
            menu.setName(modifyMenuCommand.getPath());
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
        return PojoResult.succeed(Boolean.TRUE);
    }
}
