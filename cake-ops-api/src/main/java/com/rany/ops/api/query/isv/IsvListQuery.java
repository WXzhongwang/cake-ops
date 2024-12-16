package com.rany.ops.api.query.isv;

import com.rany.ops.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IsvListQuery
 *
 * @author zhongwang
 * @date 2024/12/6
 * @slogan Why Not
 * @email zhongshengwang.zsw
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IsvListQuery extends BaseQuery {

    private String name;
}
