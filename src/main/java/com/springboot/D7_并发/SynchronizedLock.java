package com.springboot.D7_并发;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: miachelyin
 * @Description:
 * @Date: 2018/3/8
 * @Modified By:
 */
public class SynchronizedLock {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(2);
		final SynchronizedLock m = new SynchronizedLock();
		new Thread() {
			public void run() {
				m.read(Thread.currentThread());
				latch.countDown();
			}

			;
		}.start();
		new Thread() {
			public void run() {
				m.read(Thread.currentThread());
				latch.countDown();
			}

			;
		}.start();

		try {
			System.out.println("**********************");
			long startTime = System.currentTimeMillis();
			System.out.println("等待2个子线程执行完毕...");
			latch.await();
			System.out.println("2个子线程已经执行完毕");
			long endTime = System.currentTimeMillis();
			System.out.println( "all used time:" + (endTime - startTime));
			System.out.println("*****继续执行主线程******");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void read(Thread thread) {
		int count = 0;
		long startTime = System.currentTimeMillis();
		System.out.println(thread.getName() + "线程  开始读操作");
		while (count < 100) {
			count ++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(thread.getName() + "线程  完成读操作");
//		long endTime = System.currentTimeMillis();
//		System.out.println(thread.getName() + "used time:" + (endTime - startTime));
	}
}