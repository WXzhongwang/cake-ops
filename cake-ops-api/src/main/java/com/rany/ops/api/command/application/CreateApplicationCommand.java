package com.rany.ops.api.command.application;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateApplicationCommand extends BaseCommand {

    /**
     * 账号名
     */
    private String appName;

    /**
     * 账号类型
     */
    private String appCode;

    /**
     * 手机号
     */
    private String authType;
}
