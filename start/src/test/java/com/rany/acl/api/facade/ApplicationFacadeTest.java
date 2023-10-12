package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.BaseTests;
import com.rany.acl.api.command.application.*;
import com.rany.acl.api.query.application.ApplicationBasicQuery;
import com.rany.acl.api.query.application.ApplicationPageQuery;
import com.rany.acl.api.query.application.ApplicationQuery;
import com.rany.acl.common.dto.application.ApplicationDTO;
import com.rany.acl.common.enums.AuthTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/6 22:41
 * @email 18668485565163.com
 */
public class ApplicationFacadeTest extends BaseTests {


    @Resource
    private ApplicationFacade applicationFacade;
    private static final Long APP_ID = 771358516215689216L;

    @Test
    public void createAccount() {
        CreateApplicationCommand createApplicationCommand = new CreateApplicationCommand();
        createApplicationCommand.setAppName("测试APP");
        createApplicationCommand.setAppCode("ACL_12580");
        createApplicationCommand.setAuthType(AuthTypeEnum.RBAC0.name());
        PojoResult<Long> account = applicationFacade.createApplication(createApplicationCommand);
        Assert.assertTrue(account.getSuccess());
    }

    @Test
    public void getAccount() {
        ApplicationBasicQuery applicationBasicQuery = new ApplicationBasicQuery();
        applicationBasicQuery.setAppId(APP_ID);
        PojoResult<ApplicationDTO> account = applicationFacade.getApplication(applicationBasicQuery);
        Assert.assertTrue(account.getSuccess());
    }

    @Test
    public void disableAccount() {
        DisableApplicationCommand disableApplicationCommand = new DisableApplicationCommand();
        disableApplicationCommand.setAppId(APP_ID);
        PojoResult<Boolean> account = applicationFacade.disableApplication(disableApplicationCommand);
        Assert.assertTrue(account.getContent());
    }

    @Test
    public void enableAccount() {
        EnableApplicationCommand enableApplicationCommand = new EnableApplicationCommand();
        enableApplicationCommand.setAppId(APP_ID);
        PojoResult<Boolean> account = applicationFacade.enableApplication(enableApplicationCommand);
        Assert.assertTrue(account.getContent());
    }

    @Test
    public void deleteAccount() {
        DeleteApplicationCommand deleteApplicationCommand = new DeleteApplicationCommand();
        deleteApplicationCommand.setAppId(APP_ID);
        PojoResult<Boolean> account = applicationFacade.deleteApplication(deleteApplicationCommand);
        Assert.assertTrue(account.getContent());
    }

    @Test
    public void modifyAccount() {
        ModifyApplicationCommand modifyApplicationCommand = new ModifyApplicationCommand();
        modifyApplicationCommand.setAppId(APP_ID);
        modifyApplicationCommand.setAppName("测试ACL");
        PojoResult<Boolean> account = applicationFacade.modifyApplication(modifyApplicationCommand);
        Assert.assertTrue(account.getContent());
    }


    @Test
    public void findAccounts() {
        ApplicationQuery applicationQuery = new ApplicationQuery();
        ListResult<ApplicationDTO> applications = applicationFacade.findApplications(applicationQuery);
        Assert.assertTrue(applications.getSuccess());
    }

    @Test
    public void pageTenants() {
        ApplicationPageQuery applicationPageQuery = new ApplicationPageQuery();
        PageResult<ApplicationDTO> applications = applicationFacade.pageApplications(applicationPageQuery);
        Assert.assertTrue(applications.getSuccess());
    }
}