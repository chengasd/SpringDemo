package com.springboot.D9_classloader类加载;

public class D9Test {
    public static void main(String[] args) {
        /**
         * 1 通过子类获取父类的静态变量 不会造成子类的初始化
         *  父类superClass 静态代码块
         */
//        System.out.println(SubClass.superValue);

        /**
         * 2 定义数组对象，也不会初始化。
         */
//        SubClass[] a = new SubClass[10];

        /**
         * 3 获取静态常量，也不会初始化
         *   子类superClass 静态常量
         */
//        System.out.println(SubClass.subvalue);

        /**
         * 4 当初始化子类时，如果父类未初始化，也会先初始化父类
         *   父类superClass 静态代码块
         *   子类SubClass 静态代码块
         *   子类superClass 静态变量
         */
//        System.out.println(SubClass.value);
        /**
         * 5 查看初始化的顺序
         *  父类superClass 静态代码块
         *  子类SubClass 静态代码块
         *  父类superClass 实例初始化代码块
         *  父类superClass 构造函数
         *  子类SubClass 实例初始化代码块
         *  子类SubClass 构造函数
         */
//        SuperClass A = new SubClass();
//        A.sys();
    }
}
