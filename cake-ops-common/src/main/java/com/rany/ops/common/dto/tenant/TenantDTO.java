package com.rany.ops.common.dto.tenant;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 18:29
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantDTO extends DTO {
    private String id;
    private Long isvId;
    private String name;
    private String shortName;
    private String email;
    private String source;
    private String phone;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    private String address;
    private String status;
}
