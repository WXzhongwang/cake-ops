package com.rany.ops.service.aop;

import com.cake.framework.common.exception.BusinessException;
import com.rany.ops.common.enums.CommonStatusEnum;
import com.rany.ops.common.enums.DeleteStatusEnum;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.service.aop.annotation.IsvValidCheck;
import com.rany.ops.domain.aggregate.Isv;
import com.rany.ops.domain.pk.IsvId;
import com.rany.ops.domain.service.IsvDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 10:26
 * @email 18668485565163.com
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class IsvValidCheckAop extends BaseChecker {

    private static final String SPRING_EL_FLAG = "#";
    private static final ExpressionParser PARSER = new SpelExpressionParser();
    @Resource
    private IsvDomainService domainService;

    @Pointcut("@annotation(com.rany.ops.service.aop.annotation.IsvValidCheck)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        IsvValidCheck check = method.getAnnotation(IsvValidCheck.class);
        if (check.ignore()) {
            log.info("跳过isv有效性检查");
            return joinPoint.proceed();
        }

        // 获取方法入参，key为参数名，value为参数值
        LinkedHashMap<String, Object> params = resolveParams(joinPoint);
        // 求值上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        if (params.size() == 1) {
            // 当参数只有一个时，设置根对象，例如入参为对象，则此时可以使用 #root.id 来获取对象的id
            params.forEach((k, v) -> context.setRootObject(v));
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        String result = resolveValue(check.expression(), context);
        log.info("EL表达式解析结果: {}", result);
        if (result == null || result.isEmpty()) {
            throw new BusinessException(BusinessErrorMessage.ISV_CHECK_NOT_PASS);
        }
        try {
            Long isvId = Long.parseLong(result);
            Isv isv = domainService.findById(new IsvId(isvId));
            if (Objects.isNull(isv)) {
                throw new BusinessException(BusinessErrorMessage.ISV_NOT_FOUND);
            }
            if (StringUtils.equals(isv.getIsDeleted(), DeleteStatusEnum.YES.getValue())) {
                throw new BusinessException(BusinessErrorMessage.ISV_DELETED);
            }
            if (StringUtils.equals(isv.getStatus(), CommonStatusEnum.DISABLED.getValue())) {
                throw new BusinessException(BusinessErrorMessage.ISV_DISABLED);
            }
        } catch (Exception exception) {
            log.error("检查有效性失败:{}", exception.getMessage(), exception);
            throw new BusinessException(BusinessErrorMessage.ISV_CHECK_NOT_PASS);
        }
        return joinPoint.proceed();
    }


}
