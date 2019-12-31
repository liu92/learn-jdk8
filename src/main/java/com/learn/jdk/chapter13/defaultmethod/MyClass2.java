package com.learn.jdk.chapter13.defaultmethod;

import com.learn.jdk.chapter13.defaultmethod.impl.MyInterface1Impl;

/**
 * 继承实现类，实现接口
 * @ClassName: MyClass2
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 13:41
 * History:
 * @<version> 1.0
 */
public class MyClass2 extends MyInterface1Impl implements MyInterface2{
    public static void main(String[] args) {
        // 如果一个类继承了一个类和实现了一个接口,
        // 而继承的这个类重写了接口的默认的方法, 那么MyClass2就会使用
        // 重写了方法的 方法，而不会使用MyInterface2接口中默认方法
        MyClass2 myClass2 = new MyClass2();
        myClass2.defaultMethod();
        // 这里有一个java的设定: 实现类优先级要比接口的优先级高,
        // 这是因为实现类更为具体
    }
}
