package com.rany.ops.infra.dao;

import com.rany.ops.common.params.IsvSearchParam;
import com.rany.ops.domain.aggregate.Isv;
import com.rany.ops.infra.po.IsvPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/7 22:07
 * @email 18668485565163.com
 */
public interface IsvDao extends BaseMapper<IsvPO> {

    /**
     * isv 保存
     *
     * @param isv
     * @return
     */
    int save(Isv isv);

    List<IsvPO> page(IsvSearchParam param);

    List<IsvPO> findByIds(@Param("ids") List<Long> ids);
}
