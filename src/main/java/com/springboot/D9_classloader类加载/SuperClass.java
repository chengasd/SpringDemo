package com.springboot.D9_classloader类加载;

/**
 * 父类
 */
public class SuperClass {
    public static String superValue = "父类superClass 静态变量";

    static {
        System.out.println("父类superClass 静态代码块");
    }

    {
        System.out.println("父类superClass 实例初始化代码块");
    }

    public SuperClass() {
        System.out.println("父类superClass 构造函数");
    }

    public void sys() {
        System.out.println("SuperClass");
    }

}
