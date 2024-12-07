package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EnableTenantCommand
 *
 * @author zhongshengwang
 * @description EnableTenantCommand
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnableTenantCommand extends BaseCommand {
    /**
     * 租户Id
     */
    private Long tenantId;
}
