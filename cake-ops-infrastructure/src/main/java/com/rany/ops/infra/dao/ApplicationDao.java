package com.rany.ops.infra.dao;

import com.rany.ops.common.params.ApplicationPageSearchParam;
import com.rany.ops.common.params.ApplicationSearchParam;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.infra.po.ApplicationPO;
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
public interface ApplicationDao {

    /**
     * 保存
     *
     * @param application
     * @return
     */
    int save(Application application);

    /**
     * 更新
     *
     * @param application
     * @return
     */
    int update(Application application);

    /**
     * 获取应用配置
     *
     * @param appCode
     * @return
     */
    ApplicationPO selectByAppCode(@Param("appCode") String appCode);


    /**
     * 查询列表
     *
     * @param tenant
     * @return
     */
    List<ApplicationPO> selectList(ApplicationSearchParam tenant);

    /**
     * 分页查询
     *
     * @param searchParam
     * @return
     */
    List<ApplicationPO> selectPage(ApplicationPageSearchParam searchParam);


}
