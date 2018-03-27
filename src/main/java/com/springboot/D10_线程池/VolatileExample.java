package com.springboot.D10_线程池;

public class VolatileExample extends Thread {
    //设置类静态变量,各线程访问这同一共享变量
    private static boolean flag = false;

    //无限循环,等待flag变为true时才跳出循环
    //注释中间i两行，会无限循环
    //因为线程中的变量副本对应的内存值和主内存中值未实时同步， 如果变量为volatile，读取变量值时，会判断变量副本是无效的，再从主内存中获取。
    public void run() {
        int i = 0;
        while (!flag) {
            i++;
            System.out.println(i);
        }
        ;
    }
    public static void main(String[] args) throws Exception {
        VolatileExample temp = new VolatileExample();
        temp.start();
        //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
        Thread.sleep(100);
        flag = true;
    }
}