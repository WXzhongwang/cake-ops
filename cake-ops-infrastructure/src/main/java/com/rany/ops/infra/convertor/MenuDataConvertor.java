package com.rany.ops.infra.convertor;

import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import com.rany.ops.domain.aggregate.Menu;
import com.rany.ops.infra.po.MenuPO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface MenuDataConvertor extends BaseConvertor<Menu, MenuPO> {


    /**
     * 聚合根转PO
     *
     * @param menu
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(expression = "java(cn.hutool.core.util.BooleanUtil.toString(menu.getHidden(), \"1\", \"0\"))", target = "hidden")
    @Override
    MenuPO sourceToTarget(Menu menu);

    /**
     * 聚合根转PO
     *
     * @param menuPO
     * @return
     */
    @Override
    @Mapping(source = "id", target = "id.id")
    @Mapping(target = "hidden", expression = "java(cn.hutool.core.util.BooleanUtil.toBoolean(menuPO.getHidden()))")
    Menu targetToSource(MenuPO menuPO);

    @Mapping(source = "id.id", target = "menuId")
    MenuDTO sourceToDTO(Menu menu);


    /**
     * PO转DTO
     *
     * @param menuPO
     * @return
     */
    @Mapping(source = "id", target = "menuId")
    @Mapping(target = "hidden", expression = "java(cn.hutool.core.util.BooleanUtil.toBoolean(menuPO.getHidden()))")
    MenuDTO targetToDTO(MenuPO menuPO);

    /**
     * PO转DTO
     *
     * @param menuPOS
     * @return
     */
    @InheritConfiguration(name = "targetToDTO")
    List<MenuDTO> targetToDTO(List<MenuPO> menuPOS);

    List<MenuTreeDTO> convertToTreeDTO(List<MenuDTO> menuDTOS);
}
