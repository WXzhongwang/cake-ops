package com.rany.ops.api.command.account;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加账号
 *
 * @author zhongshengwang
 * @description 添加账号
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAccountCommand extends BaseCommand {

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 账号名
     */
    private String accountName;

    /**
     * 账号类型
     */
    private String accountType;

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
     * 是否是租户管理员
     */
    private Boolean isAdmin;

    /**
     * 初始账号
     */
    private String loginName;

    /**
     * 初始密码
     */
    private String loginPwd;

    /**
     * 用户在当前开放应用所属企业的唯一标识。
     */
    private String dingUnionId;

    /**
     * 用户在当前开放应用内的唯一标识。
     */
    private String openId;
}
