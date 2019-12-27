package com.learn.jdk.chapter8;

/**
 * 定义实体
 * @ClassName: Person
 * @Description:
 * @Author: lin
 * @Date: 2019/12/27 17:05
 * History:
 * @<version> 1.0
 */
public class Person {
    private String username;
    private int age;

    public Person(String username, int age){
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
