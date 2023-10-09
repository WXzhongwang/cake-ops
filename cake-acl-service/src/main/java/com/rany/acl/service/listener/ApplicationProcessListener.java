package com.rany.acl.service.listener;

import com.rany.acl.common.util.SnowflakeIdWorker;
import com.rany.acl.domain.service.ApplicationDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:32
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class ApplicationProcessListener {

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;
    @Resource
    private ApplicationDomainService applicationDomainService;
}
