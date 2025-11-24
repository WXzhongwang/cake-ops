package com.rany.ops.service.remote.account;

import cn.hutool.core.util.BooleanUtil;
import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.account.*;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.*;
import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.common.dto.account.SafeStrategyDTO;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.AccountPageSearchParam;
import com.rany.ops.common.params.AccountSearchParam;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.domain.page.PageUtils;
import com.rany.ops.domain.pk.AccountId;
import com.rany.ops.domain.service.AccountDomainService;
import com.rany.ops.infra.convertor.AccountDataConvertor;
import com.rany.ops.infra.convertor.SafeStrategyConvertor;
import com.rany.ops.service.factory.account.AccountFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 账号服务
 *
 * @author zhongshengwang
 * @description 账号服务
 * @date 2022/12/30 23:25
 * @email 18668485565163.com
 */

@Slf4j
@Service
@AllArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountDomainService accountDomainService;
    private final AccountDataConvertor accountDataConvertor;
    private final AccountFactory accountFactory;
    private final SafeStrategyConvertor safeStrategyConvertor;

    @Override
    public Long createAccount(CreateAccountCommand createAccountCommand) {
        Account account = accountFactory.build(createAccountCommand);
        account.save(createAccountCommand.getUser());
        accountDomainService.save(account);
        return account.getId().getId();
    }

    @Override
    public AccountDTO getAccount(AccountBasicQuery accountBasicQuery) {
        Account account = accountDomainService.findById(new AccountId(accountBasicQuery.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        return accountDataConvertor.sourceToDTO(account);
    }

    @Override
    public AccountDTO getAccountByDingId(AccountDingIdQuery accountBasicQuery) {
        Account account = accountDomainService.findAccountByDingUnionId(accountBasicQuery.getTenantId(), accountBasicQuery.getDingUnionId());
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        return accountDataConvertor.sourceToDTO(account);
    }

    @Override
    public SafeStrategyDTO getAccountByLoginInName(AccountLoginNameQuery accountBasicQuery) {
        SafeStrategy safeStrategy = accountDomainService.getStrategyByLoginInName(accountBasicQuery.getTenantId(), accountBasicQuery.getLoginName());
        if (Objects.isNull(safeStrategy)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        return safeStrategyConvertor.sourceToDTO(safeStrategy);
    }

    @Override
    public Boolean disableAccount(DisableAccountCommand disableAccountCommand) {
        Account account = accountDomainService.findById(new AccountId(disableAccountCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        account.disable(disableAccountCommand.getUser());
        accountDomainService.update(account);
        return Boolean.TRUE;
    }

    @Override
    public Boolean enableAccount(EnableAccountCommand enableAccountCommand) {
        Account account = accountDomainService.findById(new AccountId(enableAccountCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        account.enable(enableAccountCommand.getUser());
        accountDomainService.update(account);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAccount(DeleteAccountCommand deleteAccountCommand) {
        Account account = accountDomainService.findById(new AccountId(deleteAccountCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        account.delete(deleteAccountCommand.getUser());
        accountDomainService.update(account);
        return Boolean.TRUE;
    }

    @Override
    public Boolean modifyAccount(ModifyAccountCommand modifyAccountCommand) {
        Account account = accountFactory.build(modifyAccountCommand);
        account.modify(modifyAccountCommand.getUser());
        accountDomainService.update(account);
        return Boolean.TRUE;
    }

    @Override
    public Boolean createSafeStrategy(CreateSafeStrategyCommand createSafeStrategyCommand) {
        Account account = accountDomainService.findById(new AccountId(createSafeStrategyCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        if (StringUtils.equals(account.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_DELETED);
        }
        if (StringUtils.equals(account.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_DISABLED);
        }
        SafeStrategy safeStrategy = new SafeStrategy(account.getId().getId(), createSafeStrategyCommand.getStrategy().name(),
                createSafeStrategyCommand.getAuthCode(),
                createSafeStrategyCommand.getAuthValue()
        );
        if (Objects.nonNull(createSafeStrategyCommand.getBlockAt())) {
            safeStrategy.setBlockAt(createSafeStrategyCommand.getBlockAt());
        }
        if (Objects.nonNull(createSafeStrategyCommand.getExpiredAt())) {
            safeStrategy.setExpiredAt(createSafeStrategyCommand.getExpiredAt());
        }
        safeStrategy.init(createSafeStrategyCommand.getUser());
        account.setSafeStrategies(Collections.singletonList(safeStrategy));
        accountDomainService.saveSafeStrategy(account);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateSafeStrategy(UpdateSafeStrategyCommand updateSafeStrategyCommand) {
        Account account = accountDomainService.findById(new AccountId(updateSafeStrategyCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        if (StringUtils.equals(account.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_DELETED);
        }
        if (StringUtils.equals(account.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_DISABLED);
        }

        for (SafeStrategy safeStrategy : account.getSafeStrategies()) {
            if (StringUtils.equals(safeStrategy.getAuthCode(), updateSafeStrategyCommand.getAuthCode())
                    && StringUtils.equals(safeStrategy.getLoginStrategy(), updateSafeStrategyCommand.getStrategy().name())) {
                safeStrategy.setAuthValue(updateSafeStrategyCommand.getAuthValue());
                safeStrategy.setBlockAt(updateSafeStrategyCommand.getBlockAt());
                safeStrategy.setExpiredAt(updateSafeStrategyCommand.getExpiredAt());
                safeStrategy.modify(updateSafeStrategyCommand.getUser());
                continue;
            }
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_STRATEGY_NOT_FOUND);
        }
        accountDomainService.updateSafeStrategy(account);
        return Boolean.TRUE;
    }

    @Override
    public List<SafeStrategyDTO> findSafeStrategies(AccountSafeStrategyQuery accountQuery) {
        List<SafeStrategy> safeStrategies = accountDomainService.listSafeStrategy(new AccountId(accountQuery.getAccountId()));
        return safeStrategyConvertor.sourceToDTO(safeStrategies);
    }

    @Override
    public List<AccountDTO> findAccounts(AccountQuery accountQuery) {
        AccountSearchParam searchParam = new AccountSearchParam();
        if (Objects.nonNull(accountQuery.getTenantId())) {
            searchParam.setTenantId(accountQuery.getTenantId());
        }
        if (StringUtils.isNotEmpty(accountQuery.getAccountName())) {
            searchParam.setAccountName(accountQuery.getAccountName());
        }
        if (BooleanUtil.isTrue(accountQuery.getExcludeDeleted())) {
            searchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(accountQuery.getExcludeDisabled())) {
            searchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        if (Objects.nonNull(accountQuery.getAccountType())) {
            searchParam.setAccountType(accountQuery.getAccountType().name());
        }
        if (BooleanUtil.isFalse(accountQuery.getContainsAdmin())) {
            searchParam.setIsAdmin("0");
        }
        if (BooleanUtil.isTrue(accountQuery.getExcludeDeleted())) {
            searchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(accountQuery.getExcludeDisabled())) {
            searchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        if (CollectionUtils.isNotEmpty(accountQuery.getAccountIds())) {
            searchParam.setAccountIds(accountQuery.getAccountIds());
        }
        List<Account> accounts = accountDomainService.selectAccounts(searchParam);
        return accountDataConvertor.sourceToDTO(accounts);
    }

    @Override
    public Page<AccountDTO> pageAccounts(AccountPageQuery accountPageQuery) {
        AccountPageSearchParam searchParam = new AccountPageSearchParam();
        searchParam.setPageNo(accountPageQuery.getPageNo());
        searchParam.setPageSize(accountPageQuery.getPageSize());
        if (Objects.nonNull(accountPageQuery.getTenantId())) {
            searchParam.setTenantId(accountPageQuery.getTenantId());
        }
        if (StringUtils.isNotEmpty(accountPageQuery.getAccountName())) {
            searchParam.setAccountName(accountPageQuery.getAccountName());
        }
        if (BooleanUtil.isTrue(accountPageQuery.getExcludeDeleted())) {
            searchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(accountPageQuery.getExcludeDisabled())) {
            searchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        if (Objects.nonNull(accountPageQuery.getAccountType())) {
            searchParam.setAccountType(accountPageQuery.getAccountType().name());
        }
        if (BooleanUtil.isFalse(accountPageQuery.getContainsAdmin())) {
            searchParam.setIsAdmin("0");
        }
        if (BooleanUtil.isTrue(accountPageQuery.getExcludeDeleted())) {
            searchParam.setIsDeleted(DeleteStatusEnum.NO.getValue());
        }
        if (BooleanUtil.isTrue(accountPageQuery.getExcludeDisabled())) {
            searchParam.setStatus(CommonStatusEnum.ENABLE.getValue());
        }
        Page<Account> accountPage = accountDomainService.pageAccounts(searchParam);
        List<AccountDTO> accountDTOS = accountDataConvertor.sourceToDTO(new ArrayList<>(accountPage.getItems()));
        return PageUtils.build(accountPage, accountDTOS);
    }
}
