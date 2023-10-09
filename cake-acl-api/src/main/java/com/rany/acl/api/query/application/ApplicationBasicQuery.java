package com.rany.acl.api.query.application;

import com.rany.acl.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationBasicQuery extends BaseQuery {

    private Long appId;
    private String appCode;
}
