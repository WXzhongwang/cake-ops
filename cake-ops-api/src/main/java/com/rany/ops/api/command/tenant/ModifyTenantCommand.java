package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ModifyTenantCommand
 *
 * @author zhongshengwang
 * @description ModifyTenantCommand
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifyTenantCommand extends BaseCommand {
    /**
     * 租户Id
     */
    private Long tenantId;


    /**
     * 租户Id
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;
}
