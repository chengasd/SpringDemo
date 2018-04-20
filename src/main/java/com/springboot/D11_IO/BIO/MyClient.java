package com.springboot.D11_IO.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 2018/4/19
 * @Modified By:
 */
public class MyClient extends Thread{
    private static volatile boolean flag = true;

    public MyClient(String client1) {
        super(client1);
    }

    @Override
    public void run(){
        while(flag){
            Socket client = null;
            PrintWriter printWriter = null;
            BufferedReader bufferedReader = null;
            try {
                client = new Socket();
                client.connect(new InetSocketAddress("localhost",8686));
                printWriter = new PrintWriter(client.getOutputStream(),true);
                printWriter.println("hello");
                printWriter.flush();

                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));            //读取服务器返回的信息并进行输出
                System.out.println("来自服务器的信息是："+bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;


                printWriter.close();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}