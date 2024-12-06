package com.rany.ops.facade.tenant;

import cn.hutool.core.util.RandomUtil;
import com.cake.framework.common.response.Page;
import com.rany.ops.BaseTests;
import com.rany.ops.api.command.tenant.*;
import com.rany.ops.api.facade.tenant.TenantFacade;
import com.rany.ops.api.query.tenant.TenantBasicQuery;
import com.rany.ops.api.query.tenant.TenantPageQuery;
import com.rany.ops.api.query.tenant.TenantQuery;
import com.rany.ops.common.dto.tenant.TenantDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/29 22:39
 * @email 18668485565163.com
 */
public class TenantFacadeTest extends BaseTests {
    @Resource
    private TenantFacade tenantFacade;

    @Test
    public void createTenant() {
        CreateTenantCommand createTenantCommand = new CreateTenantCommand();
        createTenantCommand.setIsvId(768060752375459840L);
        createTenantCommand.setName(RandomUtil.randomString(15));
        createTenantCommand.setShortName(RandomUtil.randomString(8));
        createTenantCommand.setEmail("lovepet24@163.com");
        createTenantCommand.setPhone("18668485565");
        createTenantCommand.setSource("dev");
        createTenantCommand.setInitialAccount(true);
        tenantFacade.createTenant(createTenantCommand);
    }


    @Test
    public void modifyTenant() {
        ModifyTenantCommand modifyTenantCommand = new ModifyTenantCommand();
        modifyTenantCommand.setTenantId(769008916825780224L);
        modifyTenantCommand.setName(RandomUtil.randomString(15));
        modifyTenantCommand.setEmail("lovepet24@163.com");
        modifyTenantCommand.setPhone("13554353449");
        modifyTenantCommand.setAddress("浙江省杭州市西湖区");
        tenantFacade.modifyTenant(modifyTenantCommand);
    }

    @Test
    public void disableTenant() {
        DisableTenantCommand disableTenantCommand = new DisableTenantCommand();
        disableTenantCommand.setTenantId(768460649511661568L);
        tenantFacade.disableTenant(disableTenantCommand);
    }


    @Test
    public void enableTenant() {
        EnableTenantCommand enableTenantCommand = new EnableTenantCommand();
        enableTenantCommand.setTenantId(768460649511661568L);
        tenantFacade.enableTenant(enableTenantCommand);
    }


    @Test
    public void deleteTenant() {
        DeleteTenantCommand deleteTenantCommand = new DeleteTenantCommand();
        deleteTenantCommand.setTenantId(768460649511661568L);
        tenantFacade.deleteTenant(deleteTenantCommand);
    }


    @Test
    public void findTenant() {
        TenantBasicQuery tenantBasicQuery = new TenantBasicQuery();
        tenantBasicQuery.setTenantId(768821269968859136L);
        TenantDTO tenant = tenantFacade.getTenant(tenantBasicQuery);
        Assert.assertNotNull(tenant);
    }

    @Test
    public void findTenants() {
        TenantQuery tenantQuery = new TenantQuery();
        tenantQuery.setIsvId(768060752375459840L);
        tenantQuery.setName("杭州锐尼科技有限公司");
        List<TenantDTO> tenant = tenantFacade.findTenants(tenantQuery);
        Assert.assertFalse(tenant.isEmpty());
    }

    @Test
    public void pageTenants() {
        TenantPageQuery tenantPageQuery = new TenantPageQuery();
        tenantPageQuery.setIsvId(768060752375459840L);
        tenantPageQuery.setName("杭州锐尼科技有限公司");
        Page<TenantDTO> tenant = tenantFacade.pageTenants(tenantPageQuery);
        Assert.assertFalse(tenant.getItems().isEmpty());
    }
}