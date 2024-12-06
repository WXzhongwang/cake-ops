package com.rany.ops;

import com.cake.framework.mybatis.ParamLimitInterceptor;
import com.rany.cake.dingtalk.starter.EnableCakeSso;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * UIC+ACL,应用账号，角色，菜单，授权，统一管理
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2021/11/3 10:40 下午
 * @email 18668485565@163.com
 */
@EnableCakeSso
@EnableDubboConfig
@EnableTransactionManagement
@SpringBootApplication(exclude = {ParamLimitInterceptor.class})
@MapperScan(basePackages = {"com.rany.ops.infra.dao", "com.rany.ops.infra.mapper"})
@ComponentScan({"com.rany.ops.domain", "com.rany.ops.infra", "com.rany.ops.service", "com.rany.ops.web"})
public class CakeAclApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CakeAclApplication.class, args);
    }
}