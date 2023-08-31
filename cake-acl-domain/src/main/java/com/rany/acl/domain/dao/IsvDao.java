package com.rany.acl.domain.dao;

import com.rany.acl.domain.aggregate.Isv;
import com.rany.acl.infra.po.IsvPO;

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
}
