package com.example.aopdemo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    @AfterThrowing(pointcut = "execution(* com.example.aopdemo.service.GeneralService.*(..))", throwing = "ex")
    public void logAfterThrowingServiceLayer(JoinPoint joinPoint, Throwable ex) {
        // Log the exception message
        LOG.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getCause() != null ? ex.getCause() : "NULL");

    }
}
