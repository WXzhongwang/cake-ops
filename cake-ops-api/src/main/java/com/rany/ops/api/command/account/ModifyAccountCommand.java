package com.rany.ops.api.command.account;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 更新账号基本信息
 *
 * @author zhongshengwang
 * @description ModifyAccountCommand
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifyAccountCommand extends BaseCommand {

    /**
     * accountId
     */
    private Long accountId;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headImage;


    /**
     * dingding
     */
    private String dingding;

    /**
     * wechat
     */
    private String wechat;

    /**
     * qq
     */
    private String qq;

    /**
     * birthday
     */
    private Date birthday;

    /**
     * tags
     */
    private String tags;

    /**
     * feature
     */
    private String feature;
}
