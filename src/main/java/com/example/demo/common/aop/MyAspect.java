package com.example.demo.common.aop;

import com.example.demo.common.response.Response;
import com.example.demo.common.util.ErrorBroadCastUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class MyAspect {

	@Resource
	private ErrorBroadCastUtils errorBroadCastUtils;

	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void log() {
		
	}
	@Before("log()")
	public void doBefore() {

	}
	public static final String REQUEST_ID = "request_id";
	@Around("log()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		addMdcId();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		StopWatch watch = new StopWatch();
		log.info("请求开始,{},[{} {}]",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),request.getMethod(),request.getRequestURI());
		watch.start();
		Object result =null;
		try {
			result=joinPoint.proceed();
			return result;
		} finally {
			watch.stop();
			Response resp = (Response) result;
			if (result != null && resp.getCode() > 1000) {
				errorBroadCastUtils.broadcast(joinPoint, resp);
			}
		}

	}

	@After("log()")
	public void doAfter() {

	}
	private void addMdcId() {
		if (null == MDC.get(REQUEST_ID)) {
			MDC.put(REQUEST_ID, UUID.randomUUID().toString().replace("-", ""));
		}
	}

}
