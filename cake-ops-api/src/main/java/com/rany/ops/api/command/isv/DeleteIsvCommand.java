package com.rany.ops.api.command.isv;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DeleteIsvCommand
 *
 * @author zhongshengwang
 * @description DeleteIsvCommand
 * @date 2022/11/15 22:27
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteIsvCommand extends BaseCommand {

    private Long id;
}
