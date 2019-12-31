package com.learn.jdk.chapter13;

import com.learn.jdk.chapter11.Student;

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
     * 下面的这个两个方法设计是有意而为之的，
     * 因为这两个方法在调用时和这类没有任何关系，
     * 也就是说将这两个方法发到其他的类中也是完全可以运行的 使用的。
     * 下面的可以编译运行，但是从设计上来说是错误的。
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


    /**
     * 这才是正确的设计
     * 当前的对象和 传入的对象比较，
     *
     * @param student
     * @return
     */
    public int compareByScore(StudentDe student){
        return  this.getScore() - student.getScore();
    }


    public int compareByName(StudentDe student){
        return  this.getName().compareToIgnoreCase(student.getName());
    }
}
