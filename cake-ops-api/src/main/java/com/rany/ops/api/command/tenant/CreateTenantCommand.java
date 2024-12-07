package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CreateTenantCommand
 *
 * @author zhongshengwang
 * @description CreateTenantCommand
 * @date 2022/11/15 22:38
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTenantCommand extends BaseCommand {
    private Long isvId;
    private String name;
    private String shortName;
    private String email;
    private String phone;
    private String source;
    private String address;
    private Boolean initialAccount;
}
