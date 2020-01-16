package com.learn.jdk.chapter36;

import java.util.Arrays;
import java.util.List;

/**
 * chapter36 stream源码分析
 * @ClassName: StreamTest10
 * @Description: Stream 源码分析 s
 * @Author: lin
 * @Date: 2020/1/16 14:00
 * History:
 * @<version> 1.0
 */
public class StreamTest10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList( "hello", "world", "welcome");

        list.stream().forEach(System.out::println);
    }
}
