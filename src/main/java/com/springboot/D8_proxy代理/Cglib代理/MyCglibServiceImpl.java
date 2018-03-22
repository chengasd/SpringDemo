package com.springboot.D8_proxy代理.Cglib代理;

/**
 * Cglib代理不需要实现接口
 */
public class MyCglibServiceImpl {

    public int add(int num1 , int num2){
        return num1 + num2;
    }

    public int delete(int num1 , int num2){
        return num1 - num2;
    }
}
