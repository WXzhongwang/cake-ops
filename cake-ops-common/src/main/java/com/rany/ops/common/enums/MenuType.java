package com.rany.ops.common.enums;

import lombok.Getter;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/3/20 22:01
 * @slogon 找到银弹
 */
@Getter
public enum MenuType {
    MENU("菜单"),
    PAGE("页面");

    private String name;

    MenuType(String name) {
        this.name = name;
    }
}
