package com.rany.ops.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.common.params.ApplicationPageSearchParam;
import com.rany.ops.common.params.ApplicationSearchParam;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.pk.ApplicationId;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:19
 * @email 18668485565163.com
 */

public interface ApplicationRepository extends Repository<Application, ApplicationId> {

    /**
     * 账号更新
     *
     * @param application
     * @return
     */
    int update(Application application);

    /**
     * 获取应用信息
     *
     * @param appCode
     * @return
     */
    Application findByAppCode(String appCode);

    /**
     * 查询列表
     *
     * @param applicationSearchParam
     * @return
     */
    List<ApplicationDTO> findApplications(ApplicationSearchParam applicationSearchParam);


    /**
     * 分页查询账号
     *
     * @param applicationPageSearchParam
     * @return
     */
    Page<ApplicationDTO> pageApplications(ApplicationPageSearchParam applicationPageSearchParam);

}
