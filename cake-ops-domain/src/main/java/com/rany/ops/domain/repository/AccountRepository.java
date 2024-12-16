package com.rany.ops.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.params.AccountPageSearchParam;
import com.rany.ops.common.params.AccountSearchParam;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.pk.AccountId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface AccountRepository extends Repository<Account, AccountId> {

    /**
     * 根据登录账号查找用户
     *
     * @param loginName
     * @return
     */
    Account findAccountByLoginName(String loginName);

    /**
     * 根据dingUnionId查找用户
     *
     * @param dingUnionId
     * @return
     */
    Account findAccountByDingUnionId(Long tenantId, String dingUnionId);

    /**
     * 账号更新
     *
     * @param account
     * @return
     */
    int update(Account account);


    /**
     * 保存登录方式
     *
     * @param account
     * @return
     */
    Boolean saveSafeStrategy(Account account);

    /**
     * 保存登录方式
     *
     * @param account
     * @return
     */
    Boolean updateSafeStrategy(Account account);


    /**
     * 查询列表
     *
     * @param accountSearchParam
     * @return
     */
    List<Account> findAccounts(AccountSearchParam accountSearchParam);


    /**
     * 分页查询账号
     *
     * @param accountPageSearchParam
     * @return
     */
    Page<Account> pageAccounts(AccountPageSearchParam accountPageSearchParam);

}
