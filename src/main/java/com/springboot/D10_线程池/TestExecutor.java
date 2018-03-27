package com.springboot.D10_线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestExecutor {

    public static volatile int value = 1;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<FutureTask<String>> list = new ArrayList<>();
        for (int i = 1 ; i<10 ; i++){
            MyCallable callThread = new MyCallable("线程"+i);
            //Future<String> future = executorService.submit(callThread);
            //FutureTask 实现了Future接口和Runnable接口
            FutureTask<String> task = new FutureTask<>(callThread);
            executorService.submit(task);

            list.add(task);
        }
        for (FutureTask<String> task : list){
            try {
                System.out.println(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}

class MyCallable implements Callable<String>{

    public MyCallable(String name){
        this.name = name;
    }

    private String name ;

    @Override
    public String call() throws Exception {
        TestExecutor.value += 1;
        System.out.println(name + "当期value = " + TestExecutor.value);
        lazy();
        return String.valueOf(TestExecutor.value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void lazy() {
        try {
            Thread.sleep((int)(Math.random()*2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
