package com.rany.ops.api.facade.application;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.application.*;
import com.rany.ops.api.query.application.ApplicationBasicQuery;
import com.rany.ops.api.query.application.ApplicationPageQuery;
import com.rany.ops.api.query.application.ApplicationQuery;
import com.rany.ops.common.dto.application.ApplicationDTO;

import java.util.List;

/**
 * 应用管理
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface ApplicationFacade {


    /**
     * 创建应用
     *
     * @param command 指令
     * @return 应用id
     */
    Long createApplication(CreateApplicationCommand command);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery 查询
     * @return 应用信息
     */
    ApplicationDTO getApplication(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery 查询
     * @return 应用信息
     */
    ApplicationDTO getApplicationByAppCode(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 应用禁用
     *
     * @param disableApplicationCommand 指令
     * @return boolean
     */
    Boolean disableApplication(DisableApplicationCommand disableApplicationCommand);

    /**
     * 启用应用
     *
     * @param enableApplicationCommand 指令
     * @return boolean
     */
    Boolean enableApplication(EnableApplicationCommand enableApplicationCommand);

    /**
     * 删除应用
     *
     * @param deleteApplicationCommand 指令
     * @return boolean
     */
    Boolean deleteApplication(DeleteApplicationCommand deleteApplicationCommand);

    /**
     * 更新应用基本信息
     *
     * @param modifyApplicationCommand 指令
     * @return boolean
     */
    Boolean modifyApplication(ModifyApplicationCommand modifyApplicationCommand);


    /**
     * 分页查询应用
     *
     * @param applicationQuery 查询
     * @return 列表
     */
    List<ApplicationDTO> findApplications(ApplicationQuery applicationQuery);

    /**
     * 查询应用分页
     *
     * @param applicationPageQuery 查询
     * @return 分页
     */
    Page<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery);

}
