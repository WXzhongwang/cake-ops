package com.rany.acl.api.command.menu;

import com.rany.acl.common.base.BaseCommand;
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
public class EnableMenuCommand extends BaseCommand {

    /**
     * menuId
     */
    private Long menuId;
}
