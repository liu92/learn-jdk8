package com.learn.jdk.chapter6;

import java.util.function.Function;

/**
 * function接口详解
 * @ClassName: FunctionDemo
 * @Description: function接口详解
 * @Author: lin
 * @Date: 2019/12/27 10:12
 * History:
 * @<version> 1.0
 */
public class FunctionDemo {
    public static void main(String[] args) {
       FunctionDemo functionDemo = new FunctionDemo();
        // 参数1 是传递值，而Function是传递方法
        // 将 va->{return  2 * va ;} 作为一个动作或者行为传递给了下面的compute方法
        // 这种传递方式 和以前定义好 方法不同，这种方法事先不知道行为，
        // 而以前的方法 比如method1，method2 方法 这种是已经定义好的 行为方式
        System.out.println(functionDemo.compute(1, va->{return  2 * va ;}));
        System.out.println(functionDemo.compute(2, va -> 5 + va));
        System.out.println(functionDemo.compute(3 ,va->va* va));

        System.out.println(functionDemo.convert(5, va ->String.valueOf(va+ "hello")));
    }



    private int compute(int a , Function<Integer, Integer> function){
        // 将a 做为参数传入 ,
        // apply 执行什么样的动作或者行为 提前是不知道的，
        // 而是通过 方法的传递来告知的
        int result = function.apply(a);
        return  result;
    }

    private String convert(int b, Function<Integer, String> function){
        return  function.apply(b);
    }

    private int method1(int a){
        return  2 * a;
    }

    private int method2(int a){
        return  5 + a;
    }
}
