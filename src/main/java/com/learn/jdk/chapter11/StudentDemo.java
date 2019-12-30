package com.learn.jdk.chapter11;

import java.util.function.Supplier;

/**
 * 通过使用supplier来生成 student的实例
 * @ClassName: StudentDemo
 * @Description: 通过使用supplier来生成 student的实例
 * @Author: lin
 * @Date: 2019/12/30 9:19
 * History:
 * @<version> 1.0
 */
public class StudentDemo {
    public static void main(String[] args) {
        //第一种
       Supplier<Student> supplier = () -> new Student();
       System.out.println(supplier.get().getName());
       System.out.println("-------------------");
       //第二种：方法引用,            构造方法引用，将鼠标放在new关键字上，就可以看到其实调用的是构造方法
        // Supplier 接口中定义，不接受参数 ，返回一个对象。所以调用构造方法符合这个定义
        // 如果 student类中 有多个构造方法，编译器会自动去找 不传入参数的构造方法。
        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getName());


    }





}
