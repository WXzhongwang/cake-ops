package com.rany.acl.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.acl.common.dto.account.AccountDTO;
import com.rany.acl.common.params.AccountPageSearchParam;
import com.rany.acl.common.params.AccountSearchParam;
import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.aggregate.Account;
import com.rany.acl.domain.pk.AccountId;
import com.rany.acl.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class AccountDomainService {

    @Resource
    private AccountRepository accountRepository;
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

    public Boolean saveSafeStrategy(Account account) {
        return accountRepository.saveSafeStrategy(account);
    }

    public Boolean updateSafeStrategy(Account account) {
        return accountRepository.updateSafeStrategy(account);
    }


    public List<AccountDTO> selectAccounts(AccountSearchParam searchParam) {
        return accountRepository.findAccounts(searchParam);
    }

    public Page<AccountDTO> pageAccounts(AccountPageSearchParam searchParam) {
        return accountRepository.pageAccounts(searchParam);
    }
}
