package com.book;


import java.util.Random;

/**
 * @author: yinchengjian
 * @description:
 * @date: 2019/1/7
 * @modified By:
 */
public class CpuMainV2 {
    private static int threadCount = 50;

    public static void main(String[] args) {
        CpuMainV2 demo = new CpuMainV2();
        demo.runTest();
    }

    private Random random = new Random();

    private Object[] locks;

    private void runTest() {
        locks = new Object[threadCount];
        for (int i = 0; i < threadCount; i++) {
            locks[i] = new Object();
        }
        for (int i = 0; i < threadCount; i++) {
            new Thread(new ATask(i), "锁线程" + i).start();
            new Thread(new BTask(i), "释放线程" + i).start();
        }
    }

    class ATask implements Runnable {
        private Object lockObject = null;

        public ATask(int i) {
            lockObject = locks[i];
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (lockObject) {
                        lockObject.wait(random.nextInt(10));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class BTask implements Runnable {
        private Object lockObject = null;

        public BTask(int i) {
            lockObject = locks[i];
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lockObject) {
                    lockObject.notifyAll();
                }
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
