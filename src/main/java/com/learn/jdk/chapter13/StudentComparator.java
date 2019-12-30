package com.learn.jdk.chapter13;

/**
 * 方法引用2： 引用名(对象名，其实就是对象的一个引用)::实例方法名
 * 将方法放在一个新的类中，并且方法不是静态方法，是实例方法
 * @ClassName: StudentComparator
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 17:28
 * History:
 * @<version> 1.0
 */
public class StudentComparator {
    /**
     * 根据分数进行比较
     * @param student1
     * @param student2
     * @return
     */
    public  int compareStudent1ByScore(StudentDe student1, StudentDe student2){
        return  student1.getScore() - student2.getScore();
    }

    /**
     * 根据字母进行比较
     * @param student1
     * @param student2
     * @return
     */
    public static int compareStudent1ByName(StudentDe student1, StudentDe student2){
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
