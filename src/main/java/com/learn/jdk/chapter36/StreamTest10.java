package com.learn.jdk.chapter36;

import java.util.Arrays;
import java.util.List;

/**
 * chapter36 stream源码分析
 * @ClassName: StreamTest10
 * @Description: Stream 源码分析 ,流调用机制和原理
 * @Author: lin
 * @Date: 2020/1/16 14:00
 * History:
 * @<version> 1.0
 */
public class StreamTest10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList( "hello", "world", "welcome");

       System.out.println(list.getClass());
        // 打印出来的class java.util.Arrays$ArrayList 这个是Arrays类中的一个内部类,
        // 只是这个名字和java.util.ArrayList类的名字相同,但是这两者是不同的类

//        list.stream().forEach(System.out::println);
        list.stream().map(item -> item).forEach(System.out::println);
    }
}
