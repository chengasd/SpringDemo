package com.springboot.D9_classloader类加载;

import java.util.List;

public class D9StaticDispatch {

    public static void main(String[] args) {
        /**
         * People 为静态类型   而Man 和Worman为实际类型
         * 静态类型是在编译期可以确定的 ，  而实际类型需要在运行期才可以确定
         * 而方法的重载是根据变量的静态类型选择！！。  ——————  这就是静态分派
         * 而方法的重写是根据变量的实际类型选择 ——动态分派
         */
        People man = new Man();
        People worman = new Worman();
        D9StaticDispatch test = new D9StaticDispatch();
        test.sayHello(man);
        test.sayHello(worman);
        man.say();
        worman.say();
    }

    static abstract class People{
        public abstract void say();
    }
    static class Man extends People{
        @Override
        public void say() {
            System.out.println("动态分派——man");
        }
    }
    static class Worman extends People{
        @Override
        public void say() {
            System.out.println("动态分派——worman");
        }
    }
    public void sayHello(People people){
        System.out.println("Hello people");
    }
    public void sayHello(Man man){
        System.out.println("Hello man");
    }
    public void sayHello(Worman worman){
        System.out.println("Hello worman");
    }

}
