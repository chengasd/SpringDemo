package com.springboot.D7_并发;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    /**
     * ThreadLocal 为什么会存在内存泄漏？
     * 1、 每个线程单独维护一份ThreadLoaclMap值， 其中key为ThreadLocal 的弱引用 ， Value 为对应的值
     *            static class Entry extends WeakReference<ThreadLocal<?>>
     * 2、 当ThreadLocal没有相对应的强引用时，ThreadLocal会被回收。  而ThreadLoaclMap中的key就为null，而Value就会一直存在，回收不了
     * 3、 所以每次使用完ThreadLocal时，用remove方法。
     */


    public static ThreadLocal<String> sum = new ThreadLocal<String>();

    public static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        MyThread a1 = new MyThread("Thread1");
        MyThread a2 = new MyThread("Thread2");
        MyThread a3 = new MyThread("Thread3");
        a1.start();
        a2.start();
        a3.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*******总数*******"+sum.get());
        sum.remove();
        //总数为什么是null ?   因为主线程的ThreadLocalMap中没有
    }
}


class MyThread extends Thread {

    private static AtomicInteger ai = new AtomicInteger();

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i <= ai.get(); i++) {
                if (i >= 20){
                    ThreadLocalTest.latch.countDown();
                    break;
                }
                ThreadLocalTest.sum.set(ai.addAndGet(1) + "");
                System.out.println(this.getName() + " get value--->" + ThreadLocalTest.sum.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}