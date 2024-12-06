package com.rany.ops.api.command.isv;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CreateIsvCommand
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:27
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateIsvCommand extends DTO {
    private Long id;
    private String name;
    private String shortName;
    private String email;
    private String url;
    private String phone;
    private String country;
    private String registerIp;
}
