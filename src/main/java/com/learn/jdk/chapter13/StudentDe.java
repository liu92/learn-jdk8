package com.learn.jdk.chapter13;

/**
 *
 * @ClassName: Student
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 9:17
 * History:
 * @<version> 1.0
 */
public class StudentDe {
    private String name;
    private int score;

    public StudentDe(){}

    public StudentDe(String name, int score){
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

    /**
     * 根据分数排序
     * 对集合中的若干个学生进行排序，所以是两两比较
     * @param student1
     * @param student2
     * @return
     */
    public static int compareStudent1ByScore(StudentDe student1, StudentDe student2){
        return  student1.getScore() - student2.getScore();
    }

    /**
     * 根据名字排序
     * @param student1
     * @param student2
     * @return
     */
    public static int compareStudent1ByName(StudentDe student1, StudentDe student2){
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
