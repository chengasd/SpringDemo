package com.springboot.D8_proxy代理;

import com.springboot.D8_proxy代理.Cglib代理.CglibProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestMyService {
    public static void main(String[] args) {
        IMyService service = new MyServiceImpl();
        InvocationHandler handler = new MyServiceInvocationHandler(service);
        IMyService proxy = (IMyService) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),handler);
        //proxy.add(2 , 4);
        //proxy.delete(3 , 0);

        IMyService cglibProxy = (IMyService)new CglibProxy().getProxy(service);
        cglibProxy.add(2 , 4);
        cglibProxy.delete(3 , 0);
    }
}
