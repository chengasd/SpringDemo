package com.springboot.D7_并发;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: miachelyin
 * @Description: 
 * @Date: 2018/3/8
 * @Modified By:
 */
public class CyclicBarrierTest {
	//回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
	//和CountdownLounch区别：    
	//CyclicBarrier支持重用   CyclicBarrier计时初始为0 向上加，当达到预定值，会重置为0
	//CountdownLounch 是向下减，减为0就不能使用了
	
	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++) {
			new Writer(barrier).start();
		}
		
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
			try {
				Thread.sleep(5000);    
				System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("所有线程写入完毕，继续处理其他任务...");
		}
	}
}