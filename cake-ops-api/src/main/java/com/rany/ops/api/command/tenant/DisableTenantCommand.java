package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DisableTenantCommand
 *
 * @author zhongshengwang
 * @description DisableTenantCommand
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DisableTenantCommand extends BaseCommand {
    /**
     * 租户Id
     */
    private Long tenantId;
}
