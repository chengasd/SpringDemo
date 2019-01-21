package com.book;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 * @author: yinchengjian
 * @description:
 * @date: 2019/1/8
 * @modified By:
 */
public class IoMain {
    private static int THREADCOUNT = Runtime.getRuntime().availableProcessors();

    private static String FILENAME = "/tmp/iowait.log";

    private Random random = new Random();

    public static void main(String[] args) throws Exception {
        IoMain demo = new IoMain();
        demo.runTest();
    }

    private void runTest() throws Exception {
        File file = new File(FILENAME);
        file.createNewFile();
        for (int i = 0; i < THREADCOUNT; i++) {
            new Thread(new Task(), "IO线程" + i).start();
        }
    }

    class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true));
                    StringBuilder builder = new StringBuilder("====== begin ====== \n");
                    String threadname = Thread.currentThread().getName();
                    for (int i = 0; i < 1000; i++) {
                        builder.append(threadname);
                        builder.append("\n");
                    }
                    builder.append("===== end ===== \n");
                    writer.write(builder.toString());
                    writer.close();
                    Thread.sleep(random.nextInt(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
