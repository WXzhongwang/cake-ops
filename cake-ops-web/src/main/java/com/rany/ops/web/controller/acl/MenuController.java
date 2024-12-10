package com.rany.ops.web.controller.acl;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.menu.*;
import com.rany.ops.api.facade.menu.MenuFacade;
import com.rany.ops.api.query.menu.MenuBasicQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * MenuController
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/menu")
public class MenuController {

    @Resource
    private MenuFacade menuFacade;

    @PostMapping("/get-menu-tree")
    public ListResult<MenuTreeDTO> getMenuTree(@RequestBody MenuTreeQuery menuTreeQuery) {
        List<MenuTreeDTO> menuTree = menuFacade.getMenuTree(menuTreeQuery);
        return ListResult.succeed(menuTree);
    }


    @PostMapping("/get")
    public PojoResult<MenuDTO> get(@RequestBody MenuBasicQuery query) {
        MenuDTO menu = menuFacade.getMenu(query);
        return PojoResult.succeed(menu);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateMenuCommand command) {
        Long menuId = menuFacade.createMenu(command);
        return PojoResult.succeed(menuId.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyMenuCommand command) {
        Boolean success = menuFacade.modifyMenu(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableMenuCommand command) {
        Boolean menu = menuFacade.enableMenu(command);
        return PojoResult.succeed(menu);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableMenuCommand command) {
        Boolean menu = menuFacade.disableMenu(command);
        return PojoResult.succeed(menu);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteMenuCommand command) {
        Boolean menu = menuFacade.deleteMenu(command);
        return PojoResult.succeed(menu);
    }
}
