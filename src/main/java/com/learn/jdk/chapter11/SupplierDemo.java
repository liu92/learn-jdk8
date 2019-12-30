package com.learn.jdk.chapter11;

import java.util.function.Supplier;

/**
 * Chapter11 supplier
 * @ClassName: SupplierDemo
 * @Description: supplier 与函数式接口总结
 * @Author: lin
 * @Date: 2019/12/30 9:12
 * History:
 * @<version> 1.0
 */
public class SupplierDemo {
    public static void main(String[] args) {
        // 不接受参数，然后返回结果
        Supplier<String> supplier = () -> "hello world";
        //得到一个结果
        System.out.println(supplier.get());
    }
}
