package com.rany.ops.api.facade.account;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.account.*;
import com.rany.ops.api.query.account.*;
import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.common.dto.account.SafeStrategyDTO;

import java.util.List;

/**
 * 账号服务
 *
 * @author zhongshengwang
 * @description 账号服务
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface AccountFacade {


    /**
     * 创建租户账号
     *
     * @param command command
     * @return accountId
     */
    Long createAccount(CreateAccountCommand command);

    /**
     * 获取账号信息
     *
     * @param accountBasicQuery query
     * @return account
     */
    AccountDTO getAccount(AccountBasicQuery accountBasicQuery);


    /**
     * 获取账号信息
     *
     * @param accountBasicQuery query
     * @return account
     */
    AccountDTO getAccountByDingId(AccountDingIdQuery accountBasicQuery);

    /**
     * 获取账号信息
     *
     * @param accountBasicQuery query
     * @return account
     */
    SafeStrategyDTO getAccountByLoginInName(AccountLoginNameQuery accountBasicQuery);

    /**
     * 账号禁用
     *
     * @param disableAccountCommand 指令
     * @return success
     */
    Boolean disableAccount(DisableAccountCommand disableAccountCommand);

    /**
     * 启用账号
     *
     * @param enableAccountCommand 指令
     * @return success
     */
    Boolean enableAccount(EnableAccountCommand enableAccountCommand);

    /**
     * 删除账户
     *
     * @param deleteAccountCommand 指令
     * @return success
     */
    Boolean deleteAccount(DeleteAccountCommand deleteAccountCommand);

    /**
     * 更新账号基本信息
     *
     * @param modifyAccountCommand 指令
     * @return success
     */
    Boolean modifyAccount(ModifyAccountCommand modifyAccountCommand);

    /**
     * 创建登录策略
     *
     * @param createSafeStrategyCommand 指令
     * @return success
     */
    Boolean createSafeStrategy(CreateSafeStrategyCommand createSafeStrategyCommand);

    /**
     * 更新登录策略
     *
     * @param updateSafeStrategyCommand 指令
     * @return success
     */
    Boolean updateSafeStrategy(UpdateSafeStrategyCommand updateSafeStrategyCommand);


    /**
     * 查看账号登录策略
     *
     * @param accountQuery 查询账号登录策略
     * @return 登录策略
     */
    List<SafeStrategyDTO> findSafeStrategies(AccountSafeStrategyQuery accountQuery);

    /**
     * 分页查询账号
     *
     * @param accountQuery 查询
     * @return 账号
     */
    List<AccountDTO> findAccounts(AccountQuery accountQuery);

    /**
     * 查询账号
     *
     * @param accountPageQuery 查询
     * @return 分页
     */
    Page<AccountDTO> pageAccounts(AccountPageQuery accountPageQuery);

}
