package com.rany.acl.api.command.menu;

import com.rany.acl.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
public class ModifyMenuCommand extends DTO {

    /**
     * menuId
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String name;

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
