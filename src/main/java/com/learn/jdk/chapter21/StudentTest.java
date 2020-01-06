package com.learn.jdk.chapter21;

/**
 * @ClassName: StudentTest
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 22:16
 * History:
 * @<version> 1.0
 */
public class StudentTest {
    private String name;
    private int score;
    private int age;

    public StudentTest(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
