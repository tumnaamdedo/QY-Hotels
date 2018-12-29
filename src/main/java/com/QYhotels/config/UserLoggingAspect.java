package com.QYhotels.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration 
public class UserLoggingAspect {

	@Around("execution(* com.QYhotels.service..*.*(..))")
	public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		Signature methodSignature = joinPoint.getSignature();
		logger.info("Entering Service method... {} : {}() ", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object ret=joinPoint.proceed();
		stopWatch.stop();
		logger.info("Exiting Service method... {} : {}(), Time Taken: {} ms.", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName(), stopWatch.getTotalTimeMillis());
		return ret;
	}
	
	@Around("execution(* com.QYhotels.controller..*.*(..))")
	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		Signature methodSignature = joinPoint.getSignature();
		logger.info("Entering Controller... {} : {}() ", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object ret = joinPoint.proceed();
		stopWatch.stop();
		logger.info("Exiting Controller method... {} : {}(), Time Taken: {} ms", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName(), stopWatch.getTotalTimeMillis());
		return ret;
	}

	@Around("execution(* com.QYhotels.util..*.*(..))")
	public Object aroundDao(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		Signature methodSignature = joinPoint.getSignature();
		logger.info("Entering HTTP method... {} : {}()", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object ret = joinPoint.proceed();
		stopWatch.stop();
		logger.info("Data received is {}", ret.toString());
		logger.info("Entering HTTP method... {} : {}(), Time Taken: {} ms", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName(), stopWatch.getTotalTimeMillis());
		return ret;
	}

	@AfterThrowing(pointcut = "execution(* com.QYhotels.service..*.*(..))", throwing = "ex")
	public void serviceExceptionHandler(JoinPoint joinPoint, Throwable ex) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		Signature methodSignature = joinPoint.getSignature();
		logger.info("Entering Service method... {} : {}()", methodSignature.getDeclaringType().getCanonicalName(), methodSignature.getName());
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());
		logger.error("Write something in the log... We have caught exception in method: {}  with arguments {} and the full toString: {},  exception is: {}" , methodName, arguments, stuff, ex.getMessage());

	}
}
