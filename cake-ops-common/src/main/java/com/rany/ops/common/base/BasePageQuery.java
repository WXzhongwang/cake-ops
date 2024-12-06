package com.rany.ops.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BasePageQuery
 *
 * @author zhongshengwang
 * @description BasePageQuery
 * @date 2022/12/31 16:43
 * @email 18668485565163.com
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageQuery extends BaseQuery {

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private Boolean needTotal = true;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setNeedTotal(Boolean needTotal) {
        this.needTotal = needTotal;
    }
}
