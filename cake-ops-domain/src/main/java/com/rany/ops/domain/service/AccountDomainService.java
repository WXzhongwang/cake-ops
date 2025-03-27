package com.rany.ops.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.ops.common.params.AccountPageSearchParam;
import com.rany.ops.common.params.AccountSearchParam;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.domain.pk.AccountId;
import com.rany.ops.domain.repository.AccountRepository;
import com.rany.ops.domain.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 账号领域服务
 *
 * @author zhongshengwang
 * @description 账号领域服务
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class AccountDomainService {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private TenantRepository tenantRepository;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public Boolean save(Account account) {
        accountRepository.save(account);
        return Boolean.TRUE;
    }

    public Boolean update(Account account) {
        accountRepository.update(account);
        return Boolean.TRUE;
    }

    public Account findById(AccountId accountId) {
        return accountRepository.find(accountId);
    }


    public Account findAccountByDingUnionId(Long tenantId, String dingUnionId) {
        return accountRepository.findAccountByDingUnionId(tenantId, dingUnionId);
    }

    public Account findAccountByLoginName(String loginName) {
        return accountRepository.findAccountByLoginName(loginName);
    }

    public void saveSafeStrategy(Account account) {
        accountRepository.saveSafeStrategy(account);
    }

    public List<SafeStrategy> listSafeStrategy(AccountId accountId) {
        return accountRepository.listSafeStrategy(accountId);
    }

    public void updateSafeStrategy(Account account) {
        accountRepository.updateSafeStrategy(account);
    }


    public List<Account> selectAccounts(AccountSearchParam searchParam) {
        return accountRepository.findAccounts(searchParam);
    }

    public Page<Account> pageAccounts(AccountPageSearchParam searchParam) {
        Page<Account> accountPage = accountRepository.pageAccounts(searchParam);
        Collection<Account> items = accountPage.getItems();
        List<Tenant> tenantList = tenantRepository.findByIds(items.stream().map(p -> p.getTenantId().getId()).collect(Collectors.toList()));
        Map<Long, Tenant> tenantMap = tenantList.stream().collect(Collectors.toMap(x -> x.getId().getId(), isvPO -> isvPO));
        for (Account value : items) {
            Tenant tenant = tenantMap.get(value.getTenantId().getId());
            if (Objects.isNull(tenant)) {
                continue;
            }
            value.setTenant(tenant);
        }
        return accountPage;
    }
}
