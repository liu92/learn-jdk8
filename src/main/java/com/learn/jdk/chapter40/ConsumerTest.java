package com.learn.jdk.chapter40;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * chapter40
 * @ClassName: ConsumerTest
 * @Description: Consumer 和 IntConsumer
 * @Author: lin
 * @Date: 2020/1/17 15:02
 * History:
 * @<version> 1.0
 */
public class ConsumerTest {

    public void test(Consumer<Integer> consumer){
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();
         // 定义一个lambda表达式 既可以赋值给Consumer对象,也可以赋值给IntConsumer对象
        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

         // 面向对象的方式
         consumerTest.test(consumer);
          //运行报错,intConsumer是lambda表达式是不能转换为Consumer对象的
//         consumerTest.test((Consumer) intConsumer);

         //函数式方式
         consumerTest.test(consumer::accept);
         //函数式方式
         consumerTest.test(intConsumer::accept);
    }






}
