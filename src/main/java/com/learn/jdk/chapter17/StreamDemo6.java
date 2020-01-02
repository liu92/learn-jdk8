package com.learn.jdk.chapter17;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo6
 * @Description: Stream 实例剖析
 * @Author: lin
 * @Date: 2020/1/2 9:13
 * History:
 * @<version> 1.0
 */
public class StreamDemo6 {
    public static void main(String[] args) {
        // 将集合中元素转换成大写
//        List<String> list = Arrays.asList("hello", "world", "lin", "stream");
//        System.out.println(list.stream()
//                .map(String::toUpperCase)
//                .collect(Collectors.toList()));
//        System.out.println("-------------");

        // 2.每个数字的平方，然后在打印出来
//        List<Integer> list1 = Arrays.asList(2, 3, 4, 6, 7);
//        System.out.println("每个数的平方========="+list1.stream().map(item -> item * item)
//                .collect(Collectors.toList()));

        System.out.println("--------------------");

        //3.使用flatMap, 将下面的集合乘方 之后，作为整体输入出来 ，
        // 也就是形成一个list
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3),
                 Arrays.asList(4, 5, 6));
        stream.flatMap(Collection::stream)
                .map(item -> item * item).forEach(System.out::println);


    }
}
