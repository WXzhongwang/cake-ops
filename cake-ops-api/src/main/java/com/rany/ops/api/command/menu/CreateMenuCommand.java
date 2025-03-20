package com.rany.ops.api.command.menu;

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
public class CreateMenuCommand extends BaseCommand {

    /**
     * 账号名
     */
    private String appCode;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 菜单名称
     */
    private String path;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * icon
     */
    private String icon;

    /**
     * hidden
     */
    private Boolean hidden;

    /**
     * 排序号
     */
    private Integer sort;
}
