package com.springboot.Test;

import com.springboot.D2.User;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {
        Boolean[] ccc = new Boolean[1];
        if (ccc[0]){
            System.out.println("ttt");
        }
        User user1 = new User("a");
        User user2 = new User("a");
        System.out.println(user1.equals(user2));
        Integer a = 1 ;
        Integer b = 2 ;
        System.out.println("before : a = "+ a +" b = " + b);
        swap2(a , b);
        System.out.println("after : a = "+ a +" b = " + b);

        //int 常量池测试   Integer [-128,127]在常量池中
        //testIntegerCache();
    }

    /**
     * 按值传递意味着将当前的参数传递给方法的时候，方法中的变量接收的是传过来变量的副本值（相当于拷贝了一份值）。
     * 因此，我们修改了方法里面的变量的值，并不会改变外面变量的值。
     */
    private static void swap(Integer num1, Integer num2) {
        Integer temp = num1;
        num1 = num2 ;
        num2 = temp;
    }

    private static void swap2(Integer num1, Integer num2) {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            int tmp = num1.intValue();
            field.set(num1,num2);
            field.set(num2,tmp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void testIntegerCache(){
        //创建  1 个对象，存放在常量池中。引用c1,c2存放在栈内存中。
        Integer c1 = 1;
        Integer c2 = 1;
        System.out.println("c1 = c2 ? " + (c1 == c2)); //true

        //创建 2  个对象，存放在堆内存中。2 个引用存放在栈内存中。
        Integer b1 = 130; //130不在(-128~127)之间
        Integer b2 = 130;
        System.out.println("b1 = b2 ? " + (b1 == b2)); //false

        //创建2个对象，存放在堆内存中。
        Integer b3 = new Integer(2);
        Integer b4 = new Integer(2);
        System.out.println("b3 = b4 ? " + (b3 == b4));  //false
        //下面两行代码证明了使用new Integer(int i) (i 在-128~127之间)创建对象不会保存在常量池中。
        Integer b5 = 2;
        System.out.println("b3 = b5 ? " + (b3 == b5));  //false

        //Integer的自动拆箱，b3自动转换成数字 2。
        System.out.println("b3 = 2 ? " + (b3 == 2));   //true
        Integer b6 = 210;
        System.out.println("b6 = 210 ? " + (b6 == 210));   //true
    }
}
