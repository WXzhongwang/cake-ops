package com.rany.ops.facade.account;

import com.cake.framework.common.response.Page;
import com.rany.ops.BaseTests;
import com.rany.ops.api.command.account.*;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.api.query.account.AccountPageQuery;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.common.enums.AccountTypeEnum;
import com.rany.ops.common.enums.LoginSafeStrategyEnum;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/6 22:41
 * @email 18668485565163.com
 */
public class AccountFacadeTest extends BaseTests {


    @Resource
    private AccountFacade accountFacade;
    private static final Long TENANT_ID = 771342305205563392L;
    private static final Long ACCOUNT_ID = 771358516215689216L;

    @Test
    public void createAccount() {
        CreateAccountCommand createAccountCommand = new CreateAccountCommand();
        createAccountCommand.setTenantId(TENANT_ID);
        createAccountCommand.setAccountName("测试管理员01580");
        createAccountCommand.setEmail("1528683621@qq.com");
        createAccountCommand.setPhone("18668485565");
        createAccountCommand.setAccountType(AccountTypeEnum.BASIC.name());
        createAccountCommand.setIsAdmin(true);
        createAccountCommand.setLoginName("test");
        createAccountCommand.setLoginPwd("123456");
        Long account = accountFacade.createAccount(createAccountCommand);
        Assert.assertTrue(account > 0);
    }

    @Test
    public void getAccount() {
        AccountBasicQuery accountBasicQuery = new AccountBasicQuery();
        accountBasicQuery.setAccountId(ACCOUNT_ID);
        AccountDTO account = accountFacade.getAccount(accountBasicQuery);
        Assert.assertNotNull(account);
    }

    @Test
    public void disableAccount() {
        DisableAccountCommand disableAccountCommand = new DisableAccountCommand();
        disableAccountCommand.setAccountId(ACCOUNT_ID);
        Boolean account = accountFacade.disableAccount(disableAccountCommand);
        Assert.assertTrue(account);
    }

    @Test
    public void enableAccount() {
        EnableAccountCommand enableAccountCommand = new EnableAccountCommand();
        enableAccountCommand.setAccountId(ACCOUNT_ID);
        Boolean account = accountFacade.enableAccount(enableAccountCommand);
        Assert.assertTrue(account);
    }

    @Test
    public void deleteAccount() {
        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand();
        deleteAccountCommand.setAccountId(ACCOUNT_ID);
        Boolean success = accountFacade.deleteAccount(deleteAccountCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void modifyAccount() {
        ModifyAccountCommand modifyAccountCommand = new ModifyAccountCommand();
        modifyAccountCommand.setAccountId(ACCOUNT_ID);
        modifyAccountCommand.setQq("108719251");
        modifyAccountCommand.setWechat("WxZhongWang");
        Boolean success = accountFacade.modifyAccount(modifyAccountCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void createSafeStrategy() throws ParseException {
        CreateSafeStrategyCommand createSafeStrategyCommand = new CreateSafeStrategyCommand();
        createSafeStrategyCommand.setTenantId(TENANT_ID);
        createSafeStrategyCommand.setAccountId(ACCOUNT_ID);
        createSafeStrategyCommand.setAuthCode("1528683821@qq.com");
        createSafeStrategyCommand.setAuthValue("test123");
        createSafeStrategyCommand.setStrategy(LoginSafeStrategyEnum.LDAP);
        createSafeStrategyCommand.setBlockAt(DateUtils.parseDate("2023-01-15 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        createSafeStrategyCommand.setExpiredAt(DateUtils.parseDate("2023-01-10 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        Boolean success = accountFacade.createSafeStrategy(createSafeStrategyCommand);
        Assert.assertTrue(success);
    }

    @Test
    public void updateSafeStrategy() throws ParseException {
        UpdateSafeStrategyCommand updateSafeStrategyCommand = new UpdateSafeStrategyCommand();
        updateSafeStrategyCommand.setTenantId(TENANT_ID);
        updateSafeStrategyCommand.setAccountId(ACCOUNT_ID);
        updateSafeStrategyCommand.setAuthCode("1528683821@qq.com");
        updateSafeStrategyCommand.setAuthValue("test123123");
        updateSafeStrategyCommand.setStrategy(LoginSafeStrategyEnum.LDAP);
        updateSafeStrategyCommand.setBlockAt(DateUtils.parseDate("2023-01-15 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        updateSafeStrategyCommand.setExpiredAt(DateUtils.parseDate("2023-01-10 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        Boolean success = accountFacade.updateSafeStrategy(updateSafeStrategyCommand);
        Assert.assertTrue(success);
    }


    @Test
    public void findAccounts() {
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setTenantId(TENANT_ID);
        accountQuery.setContainsAdmin(false);
        accountQuery.setAccountIds(Lists.newArrayList(771342305708879872L));
        List<AccountDTO> accounts = accountFacade.findAccounts(accountQuery);
        Assert.assertFalse(accounts.isEmpty());
    }

    @Test
    public void pageTenants() {
        AccountPageQuery accountPageQuery = new AccountPageQuery();
        accountPageQuery.setTenantId(TENANT_ID);
        Page<AccountDTO> accounts = accountFacade.pageAccounts(accountPageQuery);
        Assert.assertFalse(accounts.getItems().isEmpty());
    }
}