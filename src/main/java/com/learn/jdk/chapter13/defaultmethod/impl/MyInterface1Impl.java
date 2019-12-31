package com.learn.jdk.chapter13.defaultmethod.impl;

import com.learn.jdk.chapter13.defaultmethod.MyInterface1;

/**
 * @ClassName: MyInterfaceImpl
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 13:39
 * History:
 * @<version> 1.0
 */
public class MyInterface1Impl implements MyInterface1 {
    @Override
    public void defaultMethod() {
        System.out.println("----MyInterface1Impl----");
    }
}
