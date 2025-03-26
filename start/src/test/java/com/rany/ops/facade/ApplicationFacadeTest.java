package com.rany.ops.facade;

import com.cake.framework.common.response.Page;
import com.rany.ops.BaseTests;
import com.rany.ops.api.command.application.*;
import com.rany.ops.api.facade.application.ApplicationFacade;
import com.rany.ops.api.query.application.ApplicationBasicQuery;
import com.rany.ops.api.query.application.ApplicationPageQuery;
import com.rany.ops.api.query.application.ApplicationQuery;
import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.common.enums.AuthTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

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
    private static final String APP_ID = "771358516215689216";

    @Test
    public void createAccount() {
        CreateApplicationCommand createApplicationCommand = new CreateApplicationCommand();
        createApplicationCommand.setAppName("测试APP");
        createApplicationCommand.setAppCode("ACL_12580");
        createApplicationCommand.setAuthType(AuthTypeEnum.RBAC0.name());
        Long application = applicationFacade.createApplication(createApplicationCommand);
        Assert.assertNotNull(application);
    }

    @Test
    public void getAccount() {
        ApplicationBasicQuery applicationBasicQuery = new ApplicationBasicQuery();
        applicationBasicQuery.setAppId(APP_ID);
        ApplicationDTO application = applicationFacade.getApplication(applicationBasicQuery);
        Assert.assertNotNull(application);
    }

    @Test
    public void disableAccount() {
        DisableApplicationCommand disableApplicationCommand = new DisableApplicationCommand();
        disableApplicationCommand.setId(APP_ID);
        Boolean success = applicationFacade.disableApplication(disableApplicationCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void enableAccount() {
        EnableApplicationCommand enableApplicationCommand = new EnableApplicationCommand();
        enableApplicationCommand.setId(APP_ID);
        Boolean success = applicationFacade.enableApplication(enableApplicationCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void deleteAccount() {
        DeleteApplicationCommand deleteApplicationCommand = new DeleteApplicationCommand();
        deleteApplicationCommand.setId(APP_ID);
        Boolean success = applicationFacade.deleteApplication(deleteApplicationCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void modifyAccount() {
        ModifyApplicationCommand modifyApplicationCommand = new ModifyApplicationCommand();
        modifyApplicationCommand.setId(APP_ID);
        modifyApplicationCommand.setAppName("测试ACL");
        Boolean success = applicationFacade.modifyApplication(modifyApplicationCommand);
        Assert.assertTrue(success);
    }


    @Test
    public void findAccounts() {
        ApplicationQuery applicationQuery = new ApplicationQuery();
        List<ApplicationDTO> applications = applicationFacade.findApplications(applicationQuery);
        Assert.assertTrue(applications.isEmpty());
    }

    @Test
    public void pageTenants() {
        ApplicationPageQuery applicationPageQuery = new ApplicationPageQuery();
        Page<ApplicationDTO> applications = applicationFacade.pageApplications(applicationPageQuery);
        Assert.assertTrue(applications.getItems().isEmpty());
    }
}