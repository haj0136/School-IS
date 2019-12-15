package com.vea.is;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	@Before("execution (* com.vea.cv3.*.*(..))")
	public void logger(JoinPoint joinPoint) {
		logger.info("Method: " + joinPoint.getSignature());
		logger.info("Params: " + Arrays.toString(joinPoint.getArgs()));
	}
	*/

	@Around("execution (* com.vea.is..*(..))")
	public Object profileAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();

		final StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		Object result = joinPoint.proceed();
		stopwatch.stop();

		logger.info("Method: " + methodSig.getName() + " Class: " + methodSig.getClass());
		logger.info("Params: " + Arrays.toString(joinPoint.getArgs()));
		logger.info("Execution time: " + stopwatch.getTotalTimeMillis());
		return result;
	}
}
