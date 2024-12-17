package com.rany.ops.common.enums;

import lombok.Getter;

/**
 * CommonStatusEnum
 *
 * @author zhongshengwang
 * @description CommonStatusEnum
 * @date 2022/12/28 20:15
 * @email 18668485565163.com
 */
@Getter
public enum CommonStatusEnum {

    ENABLE("启用", "0"),
    DISABLED("禁用", "1");

    private final String code;
    private final String value;

    CommonStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
