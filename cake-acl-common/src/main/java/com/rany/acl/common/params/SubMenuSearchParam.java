package com.rany.acl.common.params;

import com.rany.acl.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/5 20:50
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubMenuSearchParam extends BaseQuery {

    private Long menuId;
}
