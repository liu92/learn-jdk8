package com.learn.jdk.chapter13;

import java.util.Arrays;
import java.util.List;

/**
 * chapter13 方法引用
 * @ClassName: MethodReferenceDemo
 * @Description: 方法引用的使用
 * @Author: lin
 * @Date: 2019/12/30 16:09
 * History:
 * @<version> 1.0
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        // 使用一般lambda表达式
        //list.forEach(item -> System.out.println(item));
        // 可以将方法引用看作是一个【函数指针】，function pointer
        // 也就是说指向一个函数的指针
        list.forEach(System.out::println);
    }
}
