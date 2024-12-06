package com.rany.ops.service.factory.account;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.exception.CommonReturnCode;
import com.rany.ops.api.command.account.CreateAccountCommand;
import com.rany.ops.api.command.account.ModifyAccountCommand;
import com.rany.ops.common.enums.AccountTypeEnum;
import com.rany.ops.common.enums.LoginSafeStrategyEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.util.AccountUtil;
import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.domain.dp.AccountName;
import com.rany.ops.domain.dp.EmailAddress;
import com.rany.ops.domain.dp.HeadImage;
import com.rany.ops.domain.dp.Phone;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.domain.pk.AccountId;
import com.rany.ops.domain.pk.TenantId;
import com.rany.ops.domain.repository.AccountRepository;
import com.rany.ops.domain.repository.TenantRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AccountFactory
 *
 * @author zhongwang
 * @date 2024/12/6
 * @slogan Why Not
 * @email zhongshengwang
 */
@Component
public class AccountFactory {
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private TenantRepository tenantRepository;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public Account build(CreateAccountCommand createAccountCommand) {
        List<SafeStrategy> strategyList = new ArrayList<>();
        Account account = new Account(new AccountId(snowflakeIdWorker.nextId()),
                new TenantId(createAccountCommand.getTenantId()),
                new AccountName(createAccountCommand.getAccountName()),
                strategyList);
        if (StringUtils.isNotEmpty(createAccountCommand.getEmail())) {
            account.setEmailAddress(new EmailAddress(createAccountCommand.getEmail()));
        }
        if (StringUtils.isNotEmpty(createAccountCommand.getPhone())) {
            account.setPhone(new Phone(createAccountCommand.getPhone()));
        }
        if (StringUtils.isNotEmpty(createAccountCommand.getHeadImage())) {
            account.setHeadImage(new HeadImage(createAccountCommand.getHeadImage()));
        }
        account.setAccountType(AccountTypeEnum.BASIC.name());
        if (StringUtils.isNotEmpty(createAccountCommand.getAccountType())) {
            if (!EnumUtils.isValidEnum(AccountTypeEnum.class, createAccountCommand.getAccountType())) {
                throw new BusinessException(CommonReturnCode.PARAMETER_ILLEGAL);
            }
            AccountTypeEnum accountTypeEnum = EnumUtils.getEnum(AccountTypeEnum.class, createAccountCommand.getAccountType());
            account.setAccountType(accountTypeEnum.name());
        }
        if (StringUtils.isNotEmpty(createAccountCommand.getLoginName()) && StringUtils.isNotEmpty(createAccountCommand.getLoginPwd())) {
            Tenant tenant = tenantRepository.find(account.getTenantId());
            String loginName = AccountUtil.buildAccountLoginName(createAccountCommand.getLoginName(), tenant.getTenantName().getShortName());
            Account hasAccount = accountRepository.findAccountByLoginName(loginName);
            if (Objects.nonNull(hasAccount)) {
                throw new BusinessException(BusinessErrorMessage.ACCOUNT_NAME_DUPLICATED);
            }
            SafeStrategy safeStrategy = new SafeStrategy(account.getId().getId(),
                    LoginSafeStrategyEnum.BASIC_AUTH.name(),
                    AccountUtil.buildAccountLoginName(createAccountCommand.getLoginName(), tenant.getTenantName().getShortName()),
                    AccountUtil.md5(createAccountCommand.getLoginPwd()));
            strategyList.add(safeStrategy);
        }

        account.setIsAdmin(BooleanUtils.isTrue(createAccountCommand.getIsAdmin()));
        if (StringUtils.isNotEmpty(createAccountCommand.getDingUnionId())) {
            account.setDingUnionId(createAccountCommand.getDingUnionId());
        }
        if (StringUtils.isNotEmpty(createAccountCommand.getOpenId())) {
            account.setDingUserId(createAccountCommand.getOpenId());
        }
        return account;
    }

    public Account build(ModifyAccountCommand modifyAccountCommand) {
        Account account = accountRepository.find(new AccountId(modifyAccountCommand.getAccountId()));
        if (Objects.isNull(account)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getEmail())) {
            account.setEmailAddress(new EmailAddress(modifyAccountCommand.getEmail()));
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getPhone())) {
            account.setPhone(new Phone(modifyAccountCommand.getPhone()));
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getHeadImage())) {
            account.setHeadImage(new HeadImage(modifyAccountCommand.getHeadImage()));
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getQq())) {
            account.setQq(modifyAccountCommand.getQq());
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getWechat())) {
            account.setWechat(modifyAccountCommand.getWechat());
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getDingding())) {
            account.setDingding(modifyAccountCommand.getDingding());
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getFeature())) {
            account.setFeature(modifyAccountCommand.getFeature());
        }
        if (StringUtils.isNotEmpty(modifyAccountCommand.getTags())) {
            account.setTags(modifyAccountCommand.getTags());
        }
        if (Objects.nonNull(modifyAccountCommand.getBirthday())) {
            account.setBirthday(modifyAccountCommand.getBirthday());
        }
        return account;
    }
}
