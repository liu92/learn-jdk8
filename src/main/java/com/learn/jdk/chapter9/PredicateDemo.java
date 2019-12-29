package com.learn.jdk.chapter9;

import java.util.function.Predicate;

/**
 * chapter predicate
 * @ClassName: PredicateDemo
 * @Description: predicate 函数式接口
 * @Author: lin
 * @Date: 2019/12/29 21:53
 * History:
 * @<version> 1.0
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = p ->p.length() > 6;
        // test方法的实现是 p ->p.length() > 6;
        System.out.println(predicate.test("hello11"));
    }
}
