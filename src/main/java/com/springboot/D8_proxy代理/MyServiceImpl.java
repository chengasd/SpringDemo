package com.springboot.D8_proxy代理;

import com.springboot.annotation.MyLog;

public class MyServiceImpl implements IMyService{

    @Override
    @MyLog
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    @MyLog
    public int delete(int num1, int num2) {
        return num1 - num2;
    }
}
