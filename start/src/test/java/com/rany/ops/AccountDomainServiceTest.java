package com.rany.ops;

import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.dp.AccountName;
import com.rany.ops.domain.pk.AccountId;
import com.rany.ops.domain.pk.TenantId;
import com.rany.ops.domain.service.AccountDomainService;
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
        account.save("");
        Boolean saveRes = accountDomainService.save(account);
        Assert.assertTrue(saveRes);
    }
}