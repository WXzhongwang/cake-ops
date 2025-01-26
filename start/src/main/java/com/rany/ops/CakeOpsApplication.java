package com.rany.ops;

import com.cake.framework.mybatis.ParamLimitInterceptor;
import com.rany.cake.dingtalk.starter.EnableCakeSso;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 统一应用管理
 *
 * @author zhongshengwang
 * @description 统一应用管理
 * @date 2021/11/3 10:40 下午
 * @email 18668485565@163.com
 */
@EnableCakeSso
@EnableDubboConfig
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {ParamLimitInterceptor.class})
@MapperScan(basePackages = {"com.rany.ops.infra.dao", "com.rany.ops.infra.mapper"})
@ComponentScan({"com.rany.ops.domain", "com.rany.ops.infra", "com.rany.ops.service", "com.rany.ops.web"})
public class CakeOpsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CakeOpsApplication.class, args);
    }
}