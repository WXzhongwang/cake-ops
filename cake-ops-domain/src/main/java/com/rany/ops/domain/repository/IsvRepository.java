package com.rany.ops.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.domain.aggregate.Isv;
import com.rany.ops.domain.pk.IsvId;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface IsvRepository extends Repository<Isv, IsvId> {


    /**
     * 更新isv
     *
     * @param isv
     * @return
     */
    Boolean updateIsv(Isv isv);
}
