package com.springboot.D7_并发;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Author: yinchengjian
 * @Description: 交换器
 * @Date: 2018/5/3
 * @Modified By:
 */
public class ExchangerTest {
    public static void main(String[] args) {
        final Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Thread(){
            @Override
            public void run(){
                List<Integer> l1 = new ArrayList<Integer>();
                l1.add(1);
                l1.add(2);
                l1.add(3);

                try {
                    l1 = exchanger.exchange(l1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1"+l1);
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                List<Integer> l1 = new ArrayList<Integer>();
                l1.add(10);
                l1.add(20);
                l1.add(30);

                try {
                    l1 = exchanger.exchange(l1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2"+l1);
            }
        }.start();
    }
}
