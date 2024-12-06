package com.rany.ops.api.facade.isv;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.isv.*;
import com.rany.ops.api.query.isv.IsvBasicQuery;
import com.rany.ops.api.query.isv.IsvPageQuery;
import com.rany.ops.common.dto.isv.IsvDTO;

/**
 * IsvFacade
 *
 * @author zhongshengwang
 * @description IsvFacade
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public interface IsvFacade {


    /**
     * 创建ISV
     *
     * @param createIsvCommand 指令
     * @return isv id
     */
    Long createIsv(CreateIsvCommand createIsvCommand);

    /**
     * 更新ISV
     *
     * @param command 指令
     * @return 是否成功
     */
    Boolean updateIsv(UpdateIsvCommand command);


    /**
     * ISV 删除
     *
     * @param deleteIsvCommand 指令
     * @return 是否成功
     */
    Boolean deleteIsv(DeleteIsvCommand deleteIsvCommand);


    /**
     * 禁用ISV
     *
     * @param disableIsvCommand 指令
     * @return 是否成功
     */
    Boolean disableIsv(DisableIsvCommand disableIsvCommand);


    /**
     * 启用ISV
     *
     * @param enableIsvCommand 指令
     * @return 是否成功
     */
    Boolean enableIsv(EnableIsvCommand enableIsvCommand);


    /**
     * 查询ISV基本信息
     *
     * @param isvBaseQuery isv查询
     * @return isv
     */
    IsvDTO findIsv(IsvBasicQuery isvBaseQuery);


    /**
     * 分页查询ISV信息
     *
     * @param isvPageQuery 查询参数
     * @return 分页结果
     */
    Page<IsvDTO> pageIsv(IsvPageQuery isvPageQuery);
}
