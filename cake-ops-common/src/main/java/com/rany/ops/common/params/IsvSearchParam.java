package com.rany.ops.common.params;

import com.rany.ops.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IsvSearchParam
 *
 * @author zhongwang
 * @date 2024/12/6
 * @slogan Why Not
 * @email zhongshengwang.zsw
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IsvSearchParam extends BasePageQuery {
    private String name;
    private String status;
}
