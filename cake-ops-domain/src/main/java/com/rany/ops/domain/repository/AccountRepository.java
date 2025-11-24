package com.rany.ops.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.params.AccountPageSearchParam;
import com.rany.ops.common.params.AccountSearchParam;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.domain.pk.AccountId;

import java.util.List;

/**
 * 账号仓储
 *
 * @author zhongshengwang
 * @description 账号仓储
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface AccountRepository extends Repository<Account, AccountId> {

    /**
     * 根据登录账号查找用户
     *
     * @param loginName 登录名称
     * @return 账号
     */
    Account findAccountByLoginName(String loginName);
    SafeStrategy getStrategyByLoginInName(Long tenantId, String loginName);

    /**
     * 根据dingUnionId查找用户
     *
     * @param dingUnionId ding union id
     * @return Account
     */
    Account findAccountByDingUnionId(Long tenantId, String dingUnionId);

    /**
     * 账号更新
     *
     * @param account 账号
     * @return 更新
     */
    int update(Account account);


    /**
     * 保存登录方式
     *
     * @param account 账号
     */
    void saveSafeStrategy(Account account);

    /**
     * 查询登录方式
     *
     * @param accountId 账号id
     * @return 登录方式
     */
    List<SafeStrategy> listSafeStrategy(AccountId accountId);

    /**
     * 保存登录方式
     *
     * @param account 账号
     */
    void updateSafeStrategy(Account account);


    /**
     * 查询列表
     *
     * @param accountSearchParam 查询参数
     * @return 账号
     */
    List<Account> findAccounts(AccountSearchParam accountSearchParam);


    /**
     * 分页查询账号
     *
     * @param accountPageSearchParam 查询参数
     * @return Page<Account>
     */
    Page<Account> pageAccounts(AccountPageSearchParam accountPageSearchParam);

}
