package com.stevebarreira.codename.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class GameLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.stevebarreira.codename.repository.*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("Checking Access");
        logger.info("Access for {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.stevebarreira.codename.repository.*.*(..))", returning = "returnValue")
    public void logResult(JoinPoint joinPoint, Object returnValue){
        logger.info("Signature: {}", joinPoint.getSignature());
        logger.info("Args: {}", joinPoint.getArgs());
        logger.info("Target: {}", joinPoint.getTarget());
        logger.info("{} Return Value: {}",(((MethodSignature) joinPoint.getSignature()).getReturnType().cast(returnValue)).toString(), returnValue);
    }

}
