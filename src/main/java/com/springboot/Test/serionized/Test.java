package com.springboot.Test.serionized;

import com.alibaba.fastjson.util.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 18/3/28
 * @Modified By:
 */
public class Test implements Serializable , InitializingBean{

    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;

    public static void main(String[] args) {
        System.out.println(Child.b);

        try {



            List<String> stringList = new ArrayList<String>();
            stringList.add("hello");
            stringList.add("world");
            stringList.add("hollis");
            stringList.add("chuang");
            System.out.println("init StringList" + stringList);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
            objectOutputStream.writeObject(stringList);

            IOUtils.close(objectOutputStream);
            File file = new File("stringlist");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            List<String> newStringList = (List<String>)objectInputStream.readObject();
            IOUtils.close(objectInputStream);
            if(file.exists()){
                file.delete();
            }
            System.out.println("new StringList" + newStringList);


            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("result.obj"));
            out.writeObject(new Test());
            out.close();
            //序列化后修改为10
            Test.staticVar = 10;
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "result.obj"));
            Test t = (Test) oin.readObject();
            //再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    static class Father{

        static{
            a = 2;
        }
        public static int a = 1;
    }

    static class Child extends Father{
        public static int b = a;
    }

}
