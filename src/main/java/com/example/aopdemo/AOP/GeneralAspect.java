package com.example.aopdemo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class GeneralAspect {
    private final Logger LOG  =  LoggerFactory.getLogger(getClass());
    @Before(value="execution(* com.example.aopdemo.service.GeneralService.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
       LOG.info("********** GeneralServiceAspect | Before GeneralService method got called");
    }

    @AfterReturning(value="execution(* com.example.aopdemo.service.GeneralService.*(..))")
    public void afterReturningAdvice(JoinPoint joinPoint){
        LOG.info("********** GeneralServiceAspect  | AfterReturning GeneralService method got called");
    }

    @After(value="execution(* com.example.aopdemo.service.GeneralService.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        LOG.info("********** GeneralServiceAspect  | After GeneralService method got called");
    }
}
