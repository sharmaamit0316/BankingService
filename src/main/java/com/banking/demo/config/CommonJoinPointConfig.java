package com.banking.demo.config;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class CommonJoinPointConfig {

    @Pointcut("execution(* com.banking.demo.*.*.*(..))")
    public void businessLayerExecution(){}
}
