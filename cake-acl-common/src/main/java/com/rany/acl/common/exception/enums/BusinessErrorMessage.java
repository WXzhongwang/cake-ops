package com.rany.acl.common.exception.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhongshengwang
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorMessage {

    ISV_NOT_FOUND("2500", "isv未找到"),
    ISV_DELETED("2501", "isv已删除"),
    ISV_DISABLED("2502", "isv已禁用"),
    ISV_INVALID("2503", "isv不可用"),
    ISV_CHECK_NOT_PASS("2504", "isv校验不通过"),

    TENANT_NOT_FOUND("3500", "租户未找到"),
    TENANT_DELETED("3501", "租户已删除"),
    TENANT_DISABLED("3502", "租户已禁用"),

    TENANT_INVALID("3503", "租户不可用"),
    TENANT_CHECK_NOT_PASS("3504", "isv校验不通过"),
    TENANT_SHORT_NAME_EXISTED("3505", "租户简称已存在，换个试试"),
    TENANT_OVER_MAX_SIZE("3506", "租户个数已超过限制"),

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
    ROLE_DUPLICATED("6507", "角色已存在"),
    ;

    private final String code;
    private final String message;

    public String getCode() {
        return StrUtil.join("-", ErrorCodeEnum.BIZ.getCode(), this.code);
    }
}
