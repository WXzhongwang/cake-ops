package com.rany.ops.api.command.isv;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DisableIsvCommand
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:27
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnableIsvCommand extends BaseCommand {

    private Long id;
}
