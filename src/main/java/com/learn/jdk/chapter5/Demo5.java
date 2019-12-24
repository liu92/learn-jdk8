package com.learn.jdk.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * chapter5
 * lambda表达式 深入与流初步
 * @ClassName: Demo5
 * @Description:
 * @Author: lin
 * @Date: 2019/12/24 22:32
 * History:
 * @<version> 1.0
 */
public class Demo5 {
    public static void main(String[] args) {
         // 如果没有 MyInterfaceDemo1 这些上下文，那么编译器无法去推断类型
         // 并且MyInterfaceDemo1 函数式接口中的方法，不接受参数，没有返回值  () -> {}.
//         MyInterfaceDemo1 interfaceDemo1 = () -> {};
//         System.out.println(interfaceDemo1.getClass().getInterfaces()[0]);
//
//         MyInterfaceDemo2 myInterfaceDemo2 = () -> {};
//         System.out.println(myInterfaceDemo2.getClass().getInterfaces()[0]);

         // 变量集合的中元素，然后转换成大写 输出，这里使用lambda表达式来处理
         List<String> list = Arrays.asList("hello", "world", "hello world");
         // 1、将结合中元素 转换成大写 输出
//         list.forEach(item -> System.out.println(item.toUpperCase()));
         // 2、构造一个新的集合然后 将转换之后的这些元素 放到新的的集合中去
         //List<String> list2 = new ArrayList<>();
         // 第一种写法
         //list.forEach(item -> list2.add(item.toUpperCase()));
         //list2.forEach(item -> System.out.println(item));
         // 第二种写法 ，使用流(stream)的方式来编写。 stream() 是串行 也就是单线程，
         // parallelStream()是并行流，也就是流是多线程来操作的。
         // map() ,中function就是一个函数，给定一个输入然后得到一个输出 (Function 中方法)
         // 结束，list 把它转换成一个流，这个流调用map方法，然后将流 它中的源(就是这里的集合 list)
         // 遍历集合中每一个元素 然后做一个映射 将其转换成大写， 转换成大写之后还是返回stream.
         // 所以调用foreach方法 把每个元素打印出来
         list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));

         // 第三 通过方法引用方式来写, 一个输入参数，得到一个返回结果
        list.stream().map(String::toUpperCase).forEach(item-> System.out.println(item));

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