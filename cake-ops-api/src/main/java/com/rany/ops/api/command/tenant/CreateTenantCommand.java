package com.rany.ops.api.command.tenant;

import com.rany.ops.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:38
 * @email 18668485565163.com
 */
@Data
public class CreateTenantCommand extends DTO {
    private Long isvId;
    private String name;
    private String shortName;
    private String email;
    private String phone;
    private String source;
    private String address;
    private Boolean initialAccount;
}
