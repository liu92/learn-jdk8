package com.learn.jdk.chapter15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * chapter15
 * @ClassName: StreamDemo
 * @Description: stream 操作方式示例
 * @Author: lin
 * @Date: 2019/12/31 15:12
 * History:
 * @<version> 1.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        // 创建stream 对象
        Stream stream = Stream.of("hello", "world");
        String[] str = new String[]{"hello", "li"};
        Stream stream1 = Stream.of(str);
        Stream stream2 = Arrays.stream(str);
        
        // 通过list创建
        List<String> list = Arrays.asList(str);
        Stream<String> stream3 = list.stream();
    }
}
