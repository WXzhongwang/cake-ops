package com.rany.ops.api.command.application;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DeleteApplicationCommand
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteApplicationCommand extends BaseCommand {
    /**
     * 应用
     */
    private String id;
}
