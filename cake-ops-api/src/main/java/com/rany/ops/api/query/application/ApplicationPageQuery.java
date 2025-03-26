package com.rany.ops.api.query.application;

import com.rany.ops.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:54
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationPageQuery extends BasePageQuery {

    private String appName;

    private String appCode;

    private String authType;

}
