package com.learn.jdk.chapter20;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamDemo12
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 17:41
 * History:
 * @<version> 1.0
 */
public class StreamDemo12 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello",
                "hello world hello", "hello welcome");
        // 找出列表中所有的单词 并进行去重

        // 1、这种方式不对
//        List<String[]> collect = list.stream().map(item -> item.split(" "))
//                .distinct().collect(Collectors.toList());
//        collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));


        // 2、下面使用flatMap
        //  Arrays::stream(T[] array) 接收一个数组的 返回一个stream对象。
        // flatMap 将多个中间的stream合并成一个stream.
        List<String> result = list.stream().map(item -> item.split(" "))
                .flatMap(Arrays::stream).distinct()
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
