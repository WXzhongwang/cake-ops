package com.rany.ops.service.listener;

import com.rany.ops.common.util.SnowflakeIdWorker;
import com.rany.ops.domain.service.ApplicationDomainService;
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
