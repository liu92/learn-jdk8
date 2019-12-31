package com.learn.jdk.chapter13.defaultmethod;

/**
 *  默认方法 学习
 * @ClassName: MyInterface
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 11:06
 * @History:
 * @<version> 1.0
 */
public interface MyInterface1 {
    /**
     * 默认方法
     */
    default  void  defaultMethod(){
        System.out.println("MyInterface1");
    }
}
