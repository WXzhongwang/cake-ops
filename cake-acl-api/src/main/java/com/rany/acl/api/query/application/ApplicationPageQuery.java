package com.rany.acl.api.query.application;

import com.rany.acl.common.base.BasePageQuery;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:54
 * @email 18668485565163.com
 */
@Data
public class ApplicationPageQuery extends BasePageQuery {

    private String appName;

    private String appCode;

    private String authType;

}
