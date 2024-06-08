package com.rany.ops.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.common.params.ApplicationPageSearchParam;
import com.rany.ops.common.params.ApplicationSearchParam;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.pk.ApplicationId;
import com.rany.ops.domain.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class ApplicationDomainService {

    @Resource
    private ApplicationRepository applicationRepository;

    public Boolean save(Application application) {
        applicationRepository.save(application);
        return Boolean.TRUE;
    }

    public Boolean update(Application application) {
        applicationRepository.update(application);
        return Boolean.TRUE;
    }

    public Application findById(ApplicationId applicationId) {
        return applicationRepository.find(applicationId);
    }

    public Application findByAppCode(String appCode) {
        return applicationRepository.findByAppCode(appCode);
    }


    public List<ApplicationDTO> selectApplications(ApplicationSearchParam searchParam) {
        return applicationRepository.findApplications(searchParam);
    }

    public Page<ApplicationDTO> pageApplications(ApplicationPageSearchParam searchParam) {
        return applicationRepository.pageApplications(searchParam);
    }
}
