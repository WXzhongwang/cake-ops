package com.rany.ops.web.controller.uic;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.tenant.*;
import com.rany.ops.api.facade.tenant.TenantFacade;
import com.rany.ops.api.query.tenant.TenantBasicQuery;
import com.rany.ops.api.query.tenant.TenantPageQuery;
import com.rany.ops.common.dto.tenant.TenantDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * isv
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/tenant")
public class TenantController {

    @Resource
    private TenantFacade tenantFacade;

    @PostMapping("/page")
    public PageResult<TenantDTO> page(@RequestBody TenantPageQuery appPageQuery) {
        Page<TenantDTO> page = tenantFacade.pageTenants(appPageQuery);
        return PageResult.succeed(page);
    }

    @PostMapping("/get")
    public PojoResult<TenantDTO> get(TenantBasicQuery query) {
        TenantDTO isv = tenantFacade.getTenant(query);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateTenantCommand createAppCommand) {
        Long isv = tenantFacade.createTenant(createAppCommand);
        return PojoResult.succeed(isv.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyTenantCommand command) {
        Boolean success = tenantFacade.modifyTenant(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableTenantCommand command) {
        Boolean isv = tenantFacade.enableTenant(command);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableTenantCommand command) {
        Boolean isv = tenantFacade.disableTenant(command);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteTenantCommand command) {
        Boolean isv = tenantFacade.deleteTenant(command);
        return PojoResult.succeed(isv);
    }
}
