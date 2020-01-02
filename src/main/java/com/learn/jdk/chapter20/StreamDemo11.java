package com.learn.jdk.chapter20;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo11
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 16:56
 * History:
 * @<version> 1.0
 */
public class StreamDemo11 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        //1、打印这个列表中 长度为5的第一个单词, 并且把长度打印出来

//        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5)
//                .findFirst().ifPresent(System.out::println);
        // 如果长度没有5的，那么打印出来的是空的

        //2、这种方式也可以
//        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5)
//                .limit(1).forEach(it -> System.out.println(it));

        //3、
        // Stream 可以看作为一个容器，这个容器存放的是对每个元素的操作
        // mapToInt是一种操作, filter是一种操作。
        // 比如说对流的处理 如迭代，判断、过滤等它会拿着容器中操作 逐个应用到stream中的每一个元素上，
        // 并且将这些操作串行化，一个元素应用了一个操作之后 接着第二个操作等。
        // "而不是" 对于一个元素应用完第一个操作，第二个元素应用完第二个操作。

        //注意: stream是存在短路运算的，只要找到符合条件的 后面的就不会再去执行
        list.stream().mapToInt(item -> {
          int length = item.length();
          System.out.println(item);
          return  length;
        }).filter(length -> length == 5)
                .findFirst().ifPresent(System.out::println);
    }
}
