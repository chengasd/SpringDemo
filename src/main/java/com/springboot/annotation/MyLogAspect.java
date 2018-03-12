package com.springboot.annotation;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: miachelyin
 * @Description: 日志切入类
 * @Date: 2018/2/28
 * @Modified By:
 */
@Aspect
@Component
public class MyLogAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(MyLogAspect.class);

	public static final String WARN = "warn";

	//切入点
	@Pointcut(value = "@annotation(com.springboot.annotation.MyLog)")
	private void pointcut() {

	}

	/**
	 * 在方法执行前后
	 *
	 * @param point
	 * @param myLog
	 * @return
	 */
	@Around(value = "pointcut() && @annotation(myLog)")
	public Object around(ProceedingJoinPoint point, MyLog myLog) {
		//拦截的类名
		Class clazz = point.getTarget().getClass();
		//拦截的方法
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		Object[] objects = point.getArgs();
		String level = myLog.level();
		if (WARN.equals(level.toLowerCase())) {
			LOGGER.warn(clazz.getName() + "|" + method.getName() + "|" + "input:{}", JSONObject.toJSONString(objects));
		} else {
			LOGGER.info(clazz.getName() + "|" + method.getName() + "|" + "input:{}", JSONObject.toJSONString(objects));
		}

		try {
			Long start = System.currentTimeMillis();

			Object obj = point.proceed(); //执行程序

			Long end = System.currentTimeMillis() - start;
			if (WARN.equals(level.toLowerCase())) {
				LOGGER.warn(clazz.getName() + "|" + method.getName() + "|usedTime|" + "{}", end);
			} else {
				LOGGER.info(clazz.getName() + "|" + method.getName() + "|usedTime|" + "{}", end);
			}
			return obj;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return throwable.getMessage();
		}
	}

	/**
	 * 方法执行后
	 *
	 * @param joinPoint
	 * @param myLog
	 * @param result
	 * @return
	 */
	@AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
	public Object afterReturning(JoinPoint joinPoint, MyLog myLog, Object result) {

		//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//        HttpSession session = request.getSession();

		//拦截的类名
		Class clazz = joinPoint.getTarget().getClass();
		//拦截的方法
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

		String level = myLog.level();
		if (WARN.equals(level.toLowerCase())) {
			LOGGER.warn(clazz.getName() + "|" + method.getName() + "|" + "output:{}", JSONObject.toJSONString(result));
		} else {
			LOGGER.info(clazz.getName() + "|" + method.getName() + "|" + "output:{}", JSONObject.toJSONString(result));
		}

		return result;
	}

	/**
	 * 方法执行后 并抛出异常
	 *
	 * @param joinPoint
	 * @param myLog
	 * @param ex
	 */
	@AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, MyLog myLog, Exception ex) {
		//拦截的类名
		Class clazz = joinPoint.getTarget().getClass();
		//拦截的方法
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		LOGGER.error(clazz.getName() + "|" + method.getName() + "|" + "error:{}", JSONObject.toJSONString(ex));

	}
}