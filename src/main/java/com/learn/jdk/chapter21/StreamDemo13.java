package com.learn.jdk.chapter21;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * chapter21
 * @ClassName: StreamDemo12
 * @Description: stream分组与分区详解
 * @Author: lin
 * @Date: 2020/1/2 21:44
 * History:
 * @<version> 1.0
 */
public class StreamDemo13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hi", "Hello", "哈哈，你好");
        List<String> list2 = Arrays.asList("zairian", "lisa",
                "wangle", "zarla");
        // 将两个集合组合起来，形成各自 人名打招呼。

        List<String> result = list.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).
                collect(Collectors.toList());
//        result.forEach(System.out::println);

        List<String> collect = list.stream().map(i->i).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}
