package com.rany.acl.api.facade.isv;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.isv.CreateIsvCommand;
import com.rany.acl.api.command.isv.DeleteIsvCommand;
import com.rany.acl.api.command.isv.DisableIsvCommand;
import com.rany.acl.api.command.isv.EnableIsvCommand;
import com.rany.acl.common.dto.isv.IsvDTO;
import com.rany.acl.api.query.isv.IsvBasicQuery;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public interface IsvFacade {


    /**
     * 创建ISV
     *
     * @param createIsvCommand
     * @return
     */
    PojoResult<Boolean> createIsv(CreateIsvCommand createIsvCommand);

    /**
     * ISV 删除
     *
     * @param deleteIsvCommand
     * @return
     */
    PojoResult<Boolean> deleteIsv(DeleteIsvCommand deleteIsvCommand);


    /**
     * 禁用ISV
     *
     * @param disableIsvCommand
     * @return
     */
    PojoResult<Boolean> disableIsv(DisableIsvCommand disableIsvCommand);


    /**
     * 启用ISV
     *
     * @param enableIsvCommand
     * @return
     */
    PojoResult<Boolean> enableIsv(EnableIsvCommand enableIsvCommand);


    /**
     * 查询ISV基本信息
     *
     * @param isvBaseQuery
     * @return
     */
    PojoResult<IsvDTO> findIsv(IsvBasicQuery isvBaseQuery);
}
