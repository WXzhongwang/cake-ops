package com.rany.ops.api.command.account;

import com.rany.ops.common.base.BaseCommand;
import com.rany.ops.common.enums.LoginSafeStrategyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * CreateSafeStrategyCommand
 *
 * @author zhongshengwang
 * @description CreateSafeStrategyCommand
 * @date 2022/12/30 22:17
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateSafeStrategyCommand extends BaseCommand {

    /**
     * 账号Id
     */
    private Long accountId;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 登录策略
     */
    private LoginSafeStrategyEnum strategy;
    /**
     * 登录账号名
     */
    private String authCode;

    /**
     * 密码
     */
    private String authValue;


    /**
     * 禁用时间
     */
    private Date blockAt;

    /**
     * 过期时间
     */
    private Date expiredAt;
}
