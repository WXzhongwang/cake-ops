package com.rany.ops.common.exception;

import cn.hutool.core.util.StrUtil;
import com.cake.framework.common.exception.ErrorCodeEnum;
import com.cake.framework.common.exception.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhongshengwang
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorMessage implements ResponseCode {
    ISV_NOT_FOUND("2500", "isv未找到"),
    ISV_DELETED("2501", "isv已删除"),
    ISV_DISABLED("2502", "isv已禁用"),
    ISV_INVALID("2503", "isv不可用"),
    ISV_CHECK_NOT_PASS("2504", "isv校验不通过"),

    TENANT_NOT_FOUND("3500", "租户未找到"),
    TENANT_DELETED("3501", "租户已删除"),
    TENANT_DISABLED("3502", "租户已禁用"),

    TENANT_INVALID("3503", "租户不可用"),
    TENANT_CHECK_NOT_PASS("3504", "租户校验不通过"),
    TENANT_SHORT_NAME_EXISTED("3505", "租户简称已存在，换个试试"),
    TENANT_OVER_MAX_SIZE("3506", "租户个数已超过限制"),

    ACCOUNT_NAME_DUPLICATED("4500", "账号名已重复"),
    ACCOUNT_NOT_FOUND("4501", "账号未找到"),
    ACCOUNT_DELETED("4502", "账号已删除"),
    ACCOUNT_DISABLED("4503", "账号已禁用"),

    ACCOUNT_STRATEGY_NOT_FOUND("5500", "账号登录方式未找到"),


    APP_NAME_DUPLICATED("4500", "应用名已重复"),
    APP_NOT_FOUND("4501", "应用未找到"),
    APP_DELETED("4502", "应用已删除"),
    APP_DISABLED("4503", "应用已禁用"),

    MENU_DISABLED("5501", "菜单已禁用"),
    MENU_NOT_FOUND("5502", "菜单未找到"),
    MENU_DELETED("5502", "菜单已删除"),
    MENU_CONTAINS_CHILDREN("5503", "菜单包含子级，暂不支持删除"),
    PARENT_MENU_DISABLED("5504", "父级菜单已禁用"),
    PARENT_MENU_NOT_FOUND("5505", "父级菜单未找到"),
    PARENT_MENU_DELETED("5506", "父级菜单已删除"),


    ROLE_DISABLED("6501", "角色已禁用"),
    ROLE_NOT_FOUND("6502", "角色未找到"),
    ROLE_DELETED("6502", "角色已删除"),
    ROLE_CONTAINS_CHILDREN("5503", "角色包含子级，暂不支持删除"),
    PARENT_ROLE_DISABLED("6504", "父级角色已禁用"),
    PARENT_ROLE_NOT_FOUND("6505", "父级角色未找到"),
    PARENT_ROLE_DELETED("6506", "父级角色已删除"),
    ROLE_KEY_DUPLICATED("6507", "角色Key已存在"),

    PERMISSION_DISABLED("6501", "权限已禁用"),
    PERMISSION_NOT_FOUND("6502", "权限未找到"),
    PERMISSION_DELETED("6502", "权限已删除"),

    PERMISSION_REF_MENU_NOT_FOUND("6505", "权限关联菜单未找到"),
    PERMISSION_REF_MENU_NOT_MATCHED("6505", "权限关联菜单仅支持关联本应用菜单"),
    PERMISSION_REF_MENU_REQUIRED("6505", "权限需关联菜单"),
    PERMISSION_RESOURCE_TYPE_ILLEGAL("6505", "权限点类型仅支持行为action"),

    USER_ROLE_EXIST("6505", "用户已存在该角色"),
    USER_ROLE_NOT_EXIST("6505", "用户角色关系不存在"),
    USER_ROLE_REL_NOT_EXIST("6506", "用户角色关系不存在"),

    ;

    private final String code;
    private final String message;

    public String getCode() {
        return StrUtil.join("-", ErrorCodeEnum.BIZ.getCode(), this.code);
    }

    @Override
    public String getErrorCode() {
        return this.getCode();
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
