package com.springboot.D9_classloader类加载;

public class SubClass extends SuperClass{
    static {
        System.out.println("子类SubClass 静态代码块");
    }
    {
        System.out.println("子类SubClass 实例初始化代码块");
    }
    public SubClass(){
        System.out.println("子类SubClass 构造函数");
    }

    public static final String subvalue = "子类superClass 静态常量";

    public static String value = "子类superClass 静态变量";


    public void sys(){
        System.out.println("SubClass");
    }

}
