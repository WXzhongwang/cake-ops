package com.rany.acl;

import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Account;
import com.rany.acl.domain.dp.AccountName;
import com.rany.acl.domain.pk.AccountId;
import com.rany.acl.domain.pk.TenantId;
import com.rany.acl.domain.service.AccountDomainService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:42
 * @email 18668485565163.com
 */
public class AccountDomainServiceTest extends BaseTests {

    @Resource
    private AccountDomainService accountDomainService;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Test
    public void testSave() {
        Account account = new Account(new AccountId(snowflakeIdWorker.nextId()),
                new TenantId(768452909531803648L),
                new AccountName("zhongshengwang.zsw"),
                null);
        account.save();
        Boolean saveRes = accountDomainService.save(account);
        Assert.assertTrue(saveRes);
    }
}