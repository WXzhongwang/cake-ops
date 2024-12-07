package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DeleteTenantCommand
 *
 * @author DeleteTenantCommand
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteTenantCommand extends BaseCommand {
    /**
     * 租户Id
     */
    private Long tenantId;
}
