package com.rany.ops.api.command.permission;

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
public class DeletePermissionCommand extends BaseCommand {

    /**
     * permissionId
     */
    private Long permissionId;
}
