package com.learn.jdk.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * chapter17
 * @ClassName: StreamDemo5
 * @Description: Stream 实例剖析
 * @Author: lin
 * @Date: 2020/1/2 8:45
 * History:
 * @<version> 1.0
 */
public class StreamDemo5 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "lin");
        // 这里使用Collectors.toCollection(Supplier<C> collectionFactory)
        // 这里可以自定义转换成相应的集合
//        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
//        list.forEach(System.out::println);

        // 转换成TreeSet
//        Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set.getClass());
//        set.forEach(System.out::println);

        //3.将stream中字符串，拼接成一个字符输出出来,
        // Collectors.joining() 一个接着一个拼接
        String str = stream.collect(Collectors.joining()).toString();
        System.out.println("拼接字符串======"+str);
    }
}
