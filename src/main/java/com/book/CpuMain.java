package com.book;

import java.util.ArrayList;

/**
 * @author: yinchengjian
 * @description:  http://book.yinchengjian.site/Java%E5%88%86%E5%B8%83%E5%BC%8F%E5%BA%94%E7%94%A8/5.1%E5%AF%BB%E6%89%BE%E6%80%A7%E8%83%BD%E7%93%B6%E9%A2%88/5-1-1-CPU%E6%B6%88%E8%80%97%E5%88%86%E6%9E%90.html
 * @date: 2019/1/7
 * @modified By:
 */
public class CpuMain {
    public static void main(String[] args) throws Exception{
        CpuMain demo = new CpuMain();
        demo.runTest();
    }

    private void runTest() throws Exception{
        //可用的处理器数量
        int count = Runtime.getRuntime().availableProcessors();
        for (int i =0; i<count; i++) {
            Thread tt = new Thread(new CpuTask());
            tt.setName("CPU线程" + i);
            tt.start();
        }

        for (int i=0; i<50 ; i++) {
            Thread tt = new Thread(new NoCpuTask());
            tt.setName("非CPU线程" + i);
            tt.start();
        }
    }

    class CpuTask implements Runnable {

        @Override
        public void run() {
            String str ="fasdsfasfxcvfhetrwerwerweqrrqwrvsd14rewr235234qwe41ewdgrey456456" +
                    "fdsferewrqdsfcrgreg34rrewgdsglfkljijppomnmmniojmnmnm,nlljppkpokl;lklknlklk" +
                    "sdfjhlkjlkj.sdfjskdfjdfioewljlkjlkjnbbn,nn,mnjkjjopojkljadkaj";
            float i = 0.002f;
            float j =232.12343f;
            while (true) {
                j = i * j;
                str.indexOf("#");
                ArrayList<String> list = new ArrayList<>();
                for (int k=0 ; k<10000;k++) {
                    list.add(str+String.valueOf(k));
                }
                list.contains("iii");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class NoCpuTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}