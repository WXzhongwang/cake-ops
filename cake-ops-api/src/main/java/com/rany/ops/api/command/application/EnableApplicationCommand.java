package com.rany.ops.api.command.application;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnableApplicationCommand extends BaseCommand {
    /**
     * 账号Id
     */
    private Long id;

    /**
     * 租户Id
     */
    private Long tenantId;
}
