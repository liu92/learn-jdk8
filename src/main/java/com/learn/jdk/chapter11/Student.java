package com.learn.jdk.chapter11;

/**
 * 通过使用supplier来生成 student的实例
 * @ClassName: Student
 * @Description: 通过使用supplier来生成 student的实例
 * @Author: lin
 * @Date: 2019/12/30 9:17
 * History:
 * @<version> 1.0
 */
public class Student {
    private String name = "zairian";
    private int age = 20;

    public  Student(){}

    public  Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
