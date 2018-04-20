package com.springboot.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 18/4/6
 * @Modified By:
 */
@Aspect
@Component
public class WithinAspect {

    //切入点
    @Pointcut(value = "within(com.springboot.D3.HelloApplication)")
    public void test(){

    }

    @Before(value = "test()")
    public void before(){
        System.out.println("This is before within test");
    }

}
