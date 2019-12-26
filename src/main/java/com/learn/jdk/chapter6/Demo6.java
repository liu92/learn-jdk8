package com.learn.jdk.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * chapter6
 * Function接口详解
 * @ClassName: Demo6
 * @Description:
 * @Author: lin
 * @Date: 2019/12/24 22:32
 * History:
 * @<version> 1.0
 */
public class Demo6 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "hello world");
        // String::toUpperCase 这个 双冒号是方法引用
        // 通过一个String对象去调用 toUpperCase这个方法，这里String是个类，应该toUpperCase不是静态方法
        // 那么为什么通过方法引用就可以呢？ 这里要注意 对于这种通过 String 类等这种类型 然后 :: 跟着实例方法
        // 情况下 它的第一个输入参数 一定就是调用了这个toUpperCase方法的对象，
        // 换句话说就是在真正调用toUpperCase的时候 一定会存在一个字符串类型的对象，然后通过这个对象去调用toUpperCase方法
        //
        // Function 有两个参数，一个是输入参数，一个是返回参数。
        // 下面定义输入和输出都是string类型
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);
        // 结果interface java.util.function.Function
    }
}

/**
 * 函数式接口
 */
@FunctionalInterface
interface MyInterfaceDemo1{
    /**
     * 抽象方法
     * method
     */
    void method();
}

/**
 *
 */
@FunctionalInterface
interface MyInterfaceDemo2{
    /**
     * 抽象方法
     * method2
     */
    void method2();
}