package com.learn.jdk.chapter12;

import java.util.Optional;

/**
 * chapter 12
 * @ClassName: OptionalDemo
 * @Description: optional 使用
 * @Author: lin
 * @Date: 2019/12/30 14:31
 * History:
 * @<version> 1.0
 */
public class OptionalDemo {
    public static void main(String[] args) {
        //下面的是传统的面向对象方式
        // of方法表示传入的参数不能为空
        Optional<String> optional = Optional.of("hhh");
        // 判断是否存在，如果存在则打印
        // 这种方式不是函数式 编程方式
//        if(optional.isPresent()){
//            System.out.println(optional.get());
//        }

        // 这里使用函数式的风格来重写
        // 推荐的Optional使用方式
        optional.ifPresent(item -> System.out.println(item));

        System.out.println("----------------------");
        // 如果optional 中不存在，则打印 下面的world
        System.out.println(optional.orElse("world"));
        System.out.println("---------");
        // 不接收参数
        // 如果optional 没有值，则调用supplier函数式接口 获取一个值打印出来。
        // 如果optional里面的值存在， 那么就不打印
        System.out.println(optional.orElseGet(() -> "li"));

    }
}
