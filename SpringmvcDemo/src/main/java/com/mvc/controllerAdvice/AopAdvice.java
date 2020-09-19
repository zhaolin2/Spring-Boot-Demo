package com.mvc.controllerAdvice;

import org.apache.tomcat.jni.Time;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/7/12
 */

@Aspect
@Component
public class AopAdvice {

    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用

    @Pointcut("execution(* com.mvc.controller.*.*(..))")
    public void pointCut(){};

    @AfterThrowing(value = "pointCut()",throwing="e")
    public void throwException(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常方法调用:" + methodName + " 异常信息" + e);
    }
}
