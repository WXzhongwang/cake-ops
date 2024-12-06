package com.rany.ops.web.controller.acl;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.permission.*;
import com.rany.ops.api.facade.permission.PermissionFacade;
import com.rany.ops.api.query.permission.MenuPermissionQuery;
import com.rany.ops.api.query.permission.PermissionBasicQuery;
import com.rany.ops.common.dto.permission.PermissionDTO;
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
@RequestMapping("/api/ops/permission")
public class PermissionController {

    @Resource
    private PermissionFacade permissionFacade;

    @PostMapping("/get")
    public PojoResult<PermissionDTO> get(PermissionBasicQuery query) {
        PermissionDTO permission = permissionFacade.getPermission(query);
        return PojoResult.succeed(permission);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreatePermissionCommand command) {
        Long permissionId = permissionFacade.createPermission(command);
        return PojoResult.succeed(permissionId.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyPermissionCommand command) {
        Boolean success = permissionFacade.modifyPermission(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnablePermissionCommand command) {
        Boolean success = permissionFacade.enablePermission(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisablePermissionCommand command) {
        Boolean success = permissionFacade.disablePermission(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeletePermissionCommand command) {
        Boolean success = permissionFacade.deletePermission(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/list-menu-permissions")
    public ListResult<PermissionDTO> listMenuPermissions(@RequestBody MenuPermissionQuery query) {
        List<PermissionDTO> permissionList = permissionFacade.listMenuPermission(query);
        return ListResult.succeed(permissionList);
    }
}
