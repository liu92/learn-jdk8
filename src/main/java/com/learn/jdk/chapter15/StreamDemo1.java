package com.learn.jdk.chapter15;

import java.util.stream.IntStream;

/**
 * @ClassName: StreamDemo1
 * @Description: IntStream 操作方式示例
 * @Author: lin
 * @Date: 2019/12/31 15:21
 * History:
 * @<version> 1.0
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 5, 7, 9};
        IntStream.of(ints).forEach(System.out::println);
        System.out.println("-----------------");
        // 在3,8 范围内的包含3,不包含8
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("-----------------");
        // 包含3和8
        IntStream.rangeClosed(3, 8).forEach(System.out::println);
    }






}
