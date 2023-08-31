package com.rany.acl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.cake.framework.mybatis.ParamLimitInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * UIC Application
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2021/11/3 10:40 下午
 * @email 18668485565@163.com
 */
@EnableDubbo
@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ParamLimitInterceptor.class})
@MapperScan(basePackages = {"com.rany.acl.domain.dao", "com.rany.acl.infra.mapper"})
@ComponentScan({"com.rany.acl.domain", "com.rany.uic.infra", "com.rany.acl.service", "com.rany.acl.web"})
public class CakeAclApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CakeAclApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CakeAclApplication.class);
    }
}