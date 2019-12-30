package com.learn.jdk.extension;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * BinaryOperator：
 * 对两个相同类型的操作数进行的运算, 产生与该操作数相同的类型的结果
 * 这个是BiFunction的一种具体或者特例, BiFunction接收三个参数,前两个参数是输入参数类型,第三个参数是返回参数类型
 * @ClassName: BinaryOperatorDemo
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 10:34
 * History:
 * @<version> 1.0
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
       BinaryOperatorDemo binaryOperatorDemo = new BinaryOperatorDemo();
        //操作本身要符合BinaryOperator 中的apply的定义
        System.out.println("两数相加====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 + v2));

        System.out.println("两数相减====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 - v2));
        System.out.println("两数相乘====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 * v2));
        System.out.println("两数相除====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 / v2));

        // 取出长度小的
        System.out.println(binaryOperatorDemo.getSort("hello123", "world", (a, b) -> a.length() - b.length()));
        //上面的可以替换为 Comparator.comparingInt 方式 ,方法引用
        // binaryOperatorDemo.getSort("hello", "world", Comparator.comparingInt(String::length));

        // 首字母在ascii码 上排前的就是小的
        System.out.println(binaryOperatorDemo.getSort("hello", "world", (a, b) -> a.charAt(0) - b.charAt(0)));

    }

    /**
     * 操作本身是一种抽象
     * @param a
     * @param b
     * @param binaryOperator
     * @return
     */
    public int calculation(int a, int b ,  BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(a, b);
    }

    public String getSort(String a, String b, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(a, b);
    }
}
