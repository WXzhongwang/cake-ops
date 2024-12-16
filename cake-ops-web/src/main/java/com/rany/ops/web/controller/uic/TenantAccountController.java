package com.rany.ops.web.controller.uic;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.account.*;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.api.query.account.AccountPageQuery;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 租户账号
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/account")
public class TenantAccountController {
    @Resource
    private AccountFacade accountFacade;

    @PostMapping("/page")
    public PageResult<AccountDTO> page(@RequestBody AccountPageQuery appPageQuery) {
        Page<AccountDTO> page = accountFacade.pageAccounts(appPageQuery);
        return PageResult.succeed(page);
    }

    @PostMapping("/get")
    public PojoResult<AccountDTO> get(AccountBasicQuery query) {
        AccountDTO account = accountFacade.getAccount(query);
        return PojoResult.succeed(account);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateAccountCommand command) {
        Long accountId = accountFacade.createAccount(command);
        return PojoResult.succeed(accountId.toString());
    }

    @PostMapping("/create-strategy")
    public PojoResult<Boolean> createSafeStrategy(@RequestBody CreateSafeStrategyCommand command) {
        Boolean success = accountFacade.createSafeStrategy(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/update-strategy")
    public PojoResult<Boolean> updateSafeStrategy(@RequestBody UpdateSafeStrategyCommand command) {
        Boolean success = accountFacade.updateSafeStrategy(command);
        return PojoResult.succeed(success);
    }


    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyAccountCommand command) {
        Boolean success = accountFacade.modifyAccount(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableAccountCommand command) {
        Boolean isv = accountFacade.enableAccount(command);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableAccountCommand command) {
        Boolean success = accountFacade.disableAccount(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteAccountCommand command) {
        Boolean account = accountFacade.deleteAccount(command);
        return PojoResult.succeed(account);
    }

    @PostMapping("/find")
    public ListResult<AccountDTO> findAccounts(@RequestBody AccountQuery command) {
        List<AccountDTO> accounts = accountFacade.findAccounts(command);
        return ListResult.succeed(accounts);
    }
}
