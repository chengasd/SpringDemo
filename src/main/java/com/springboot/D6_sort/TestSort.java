package com.springboot.D6_sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 排序测试
 */
public class TestSort {
    public static void main(String[] args) {
        Integer[] array = {66,1,39,44,23,15,49,80,0,12};
        //冒泡排序
        //bubbleSort(array);
        //选择排序
        //selectionSort(array);
        //快速排序
        //quickSortFast(array);

//        List<Integer> sorts = Arrays.asList(array);
        // 采用TimSort  当数组元素小于32时 是二分插入排序。  大于32时，先计算合并排序， 先找到排好序的run分区。
        // 当数组元素小于32时 是二分插入排序
//        Collections.sort(sorts);
        Arrays.sort(array);
        Arrays.stream(array).forEach(System.out::println);

    }

    /**
     * 冒泡排序
     *  核心： 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *        每一轮会把当前最大的数放到最后
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }

    /**
     * 选择排序
     *  核心： 找出当前最小的数， 然后放到当前轮次的第一个交换
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int[] quickSortFast(int[] array) {
        return quickSort(array,0,array.length - 1);
    }

    /**
     * 快速排序
     * 核心：以第一个为基准为， 两个类似游标一样从左右开始扫描，左边如果遇到比自己打的则停止，右边遇到比自己小的则停止，然后交换值，继续扫描。 直到i = j
     *      进行递归判断
     */
    public static int[] quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return arr;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
        return arr;
    }
}

