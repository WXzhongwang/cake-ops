package com.rany.ops.api.command.account;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DisableAccountCommand
 *
 * @author zhongshengwang
 * @description DisableAccountCommand
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DisableAccountCommand extends BaseCommand {
    /**
     * 账号Id
     */
    private Long accountId;
}
