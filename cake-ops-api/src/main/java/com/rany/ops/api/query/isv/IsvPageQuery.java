package com.rany.ops.api.query.isv;

import com.rany.ops.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IsvPageQuery
 *
 * @author zhongwang
 * @date 2024/12/6
 * @slogan Why Not
 * @email zhongshengwang.zsw
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IsvPageQuery extends BasePageQuery {

    private String name;
}
