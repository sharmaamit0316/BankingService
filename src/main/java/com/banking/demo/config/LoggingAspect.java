package com.banking.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
public class LoggingAspect {

    private static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);

    @Before("com.banking.demo.config.CommonJoinPointConfig.businessLayerExecution()")
    public void before(JoinPoint joinPoint){
        LOGGER.info(" Starting execution for {}", joinPoint);
    }

    @AfterReturning(value = "com.banking.demo.config.CommonJoinPointConfig.businessLayerExecution()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        LOGGER.info("{} returned with value {}", joinPoint, result);
    }

    @AfterThrowing(value = "com.banking.demo.config.CommonJoinPointConfig.businessLayerExecution()",throwing="ex")
    public void after(JoinPoint joinPoint,Throwable ex){
        LOGGER.error("After Throwing exception in method:"+joinPoint.getSignature().toShortString());
        LOGGER.error("Exception is:"+ex.getMessage());

    }
}
