package com.springboot.D11_IO.BIO;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 2018/4/19
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        Thread client1 = new MyClient("client1");
        Thread client2 = new MyClient("client2");
        Thread client3 = new MyClient("client3");
        client1.start();
        client2.start();
        client3.start();

    }
}
