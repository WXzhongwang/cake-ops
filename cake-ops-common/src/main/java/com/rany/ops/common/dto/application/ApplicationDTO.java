package com.rany.ops.common.dto.application;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/7 21:32
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationDTO extends DTO {
    private Long id;
    private String authType;
    private String appName;
    private String appCode;
    private String status;
}
