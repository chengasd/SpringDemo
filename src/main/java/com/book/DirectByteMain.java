package com.book;

import java.nio.ByteBuffer;

/**
 * @author: yinchengjian
 * @description: 堆外内存实验
 * @date: 2019/1/10
 * @modified By:
 */
public class DirectByteMain {
    public static void main(String[] args) throws Exception {
        Thread.sleep(20000);
        System.out.println("read to create bytes,JVM heap will be used");
        byte[] bytes = new byte[128 * 1000 * 1000];
        bytes[0] = 1;
        bytes[1] = 2;
        Thread.sleep(10000);
        System.out.println("read to allocate & put direct bytebuffer,no JVM heap should be used");
        ByteBuffer buffer = ByteBuffer.allocateDirect(128 * 1024 * 1024);
        buffer.put(bytes);
        buffer.flip();
        Thread.sleep(10000);
        System.out.println("ready to gc ,JVM heap will be freed");
        bytes = null;
        System.gc();
        Thread.sleep(10000);
        System.out.println("read to get bytes , then JVM heap will be used");
        byte[] resultbytes = new byte[128 * 1000 * 1000];
        buffer.get(resultbytes);
        System.out.println("resultbytes[1] is :" + resultbytes[1]);
        Thread.sleep(10000);
        System.out.println("read to gc all");
        buffer = null;
        resultbytes = null;
        System.gc();
        Thread.sleep(10000);
    }
}
