package com.rany.ops.common.params;

import com.rany.ops.common.base.BasePageQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/5 20:50
 * @email 18668485565163.com
 */
@Data
public class ApplicationPageSearchParam extends BasePageQuery {

    private String appName;

    private String appCode;

    private String authType;

    private String isDeleted;

    private String status;
}
