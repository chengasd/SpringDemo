package com.springboot.D8_proxy代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * @Author: miachelyin
 * @Description: 动态代理处理类
 * @Date: 2018/2/28
 * @Modified By:
 */
public class MyServiceInvocationHandler implements InvocationHandler{

    //代理目标类
    private Object target;

    public MyServiceInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("**********计算开始***********");
        Object result = method.invoke(target,args);
        lazy();
        long end = System.currentTimeMillis() - start;
        System.out.println("总计耗时=" + end);
        System.out.println("result=" + result);
        System.out.println("**********计算结束***********");
        return result;
    }

    private void lazy() {
        try {
            Thread.sleep((int)(Math.random()*2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
