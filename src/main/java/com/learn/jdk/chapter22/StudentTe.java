package com.learn.jdk.chapter22;

/**
 * @ClassName: StudentTe
 * @Description:
 * @Author: lin
 * @Date: 2020/1/3 8:44
 * History:
 * @<version> 1.0
 */
public class StudentTe {
    private String name;
    private int score;

    public StudentTe(String name, int score) {
        this.name = name;
        this.score = score;
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

    @Override
    public String toString() {
        return "StudentTe{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
