package com.springboot.D8_proxy代理.Cglib代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor{
    /**
     * 代理目标
     */
    private Object target;

    /**
     * cglib原理是   在运行期间生成的代理对象是针对目标类扩展的子类。
     */
    public Object getProxy(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        //设置回调处理类
        enhancer.setCallback(this);
        //指定父类生成类型
        enhancer.setSuperclass(target.getClass());
        //创建对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("**********计算开始***********");
        System.out.println("开始调用方法"+method.getName());

        //在被代理对象上执行方法method并指定参数objects
        Object result=method.invoke(target,objects);
        lazy();
        long end = System.currentTimeMillis() - start;
        System.out.println("调用方法结束"+method.getName());

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
