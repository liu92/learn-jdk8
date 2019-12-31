package com.learn.jdk.chapter13.defaultmethod;

/**
 * @ClassName: MyClass
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 11:10
 * History:
 * @<version> 1.0
 */
public class MyClass implements MyInterface1 ,MyInterface2 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        // 如果该类实现了 MyInterface1和MyInterface2,
        // 并且两个接口有相同的方法，这种情况下会发生什么呢？
        // 注意: 在这种情况下 实现接口的类会去重写这个 默认方法。
        //  因为myclass 有一个defaultMethod实现，但是不知取那个，
        //  编译器是无从得知，所以我们显示的告诉编译， defaultMethod使用的哪一个
        myClass.defaultMethod();
    }

    @Override
    public void defaultMethod() {
        // 如果要使用MyInterface2中的方法那么
        // 使用这种方式,这样就可以使用MyInterface2中的方法了
        MyInterface2.super.defaultMethod();
//        System.out.println("hhhhh");
    }
}
