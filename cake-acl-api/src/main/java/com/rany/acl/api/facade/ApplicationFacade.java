package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.application.*;
import com.rany.acl.api.query.application.ApplicationBasicQuery;
import com.rany.acl.api.query.application.ApplicationPageQuery;
import com.rany.acl.api.query.application.ApplicationQuery;
import com.rany.acl.common.dto.application.ApplicationDTO;

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
     * @param createApplicationCommand
     * @return
     */
    PojoResult<Long> createApplication(CreateApplicationCommand createApplicationCommand);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery
     * @return
     */
    PojoResult<ApplicationDTO> getApplication(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery
     * @return
     */
    PojoResult<ApplicationDTO> getApplicationByAppCode(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 应用禁用
     *
     * @param disableApplicationCommand
     * @return
     */
    PojoResult<Boolean> disableApplication(DisableApplicationCommand disableApplicationCommand);

    /**
     * 启用应用
     *
     * @param enableApplicationCommand
     * @return
     */
    PojoResult<Boolean> enableApplication(EnableApplicationCommand enableApplicationCommand);

    /**
     * 删除应用
     *
     * @param deleteApplicationCommand
     * @return
     */
    PojoResult<Boolean> deleteApplication(DeleteApplicationCommand deleteApplicationCommand);

    /**
     * 更新应用基本信息
     *
     * @param modifyApplicationCommand
     * @return
     */
    PojoResult<Boolean> modifyApplication(ModifyApplicationCommand modifyApplicationCommand);


    /**
     * 分页查询应用
     *
     * @param applicationQuery
     * @return
     */
    ListResult<ApplicationDTO> findApplications(ApplicationQuery applicationQuery);

    /**
     * 查询应用分页
     *
     * @param applicationPageQuery
     * @return
     */
    PageResult<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery);

}
