package com.rany.ops.web.aspect;


import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.ops.common.base.BaseCommand;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zhongshengwang
 */
@Aspect
@Component
public class UserInjectionAspect {

    @Before("execution(* com.rany.ops.web.controller.*.*Controller.*(..))")
    public void injectUserIntoBaseCommand(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object[] args = joinPoint.getArgs();
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        for (Object arg : args) {
            if (arg instanceof BaseCommand) {
                if (currentUser != null) {
                    ((BaseCommand) arg).setUser(currentUser.getUserId());
                }
            }
        }
    }
}
