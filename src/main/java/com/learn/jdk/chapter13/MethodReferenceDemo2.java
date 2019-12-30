package com.learn.jdk.chapter13;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @ClassName: MethodReferenceDemo2
 * @Description: 方法引用
 * @Author: lin
 * @Date: 2019/12/30 16:37
 * History:
 * @<version> 1.0
 */
public class MethodReferenceDemo2 {
    public static void main(String[] args) {
        /**
         * 第一种、类名::静态方法名
         */
        StudentDe student1 = new StudentDe("zairian", 20);
        StudentDe student2 = new StudentDe("lisp", 80);
        StudentDe student3 = new StudentDe("wangle", 50);
        StudentDe student4 = new StudentDe("zambia", 40);

        List<StudentDe> studentDes = Arrays.asList(student1, student2, student3, student4);

        // 这种方式是使用传统的lambda 方式来进行排序
        // 本质上就是接受两个参数，然后它调用了一个方法，而这个方法本身是客观存在的，
        // 所以下面方法引用通过类名来进行调用
//        studentDes.sort((studentParam1, studentParam2) ->
//                StudentDe.compareStudent1ByScore(studentParam1, studentParam2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用传统的lambda方式进行排序==="+studentDe.getScore()));

        System.out.println("----------------");
        // 使用方法引用的方式进行调用
        // 方法引用使用的要求，你使用的lambda表达式的这个实现也就是方法体
        // 它恰好有一个对应的方法跟他完成相同功能的 这么一个对应方法是客观存在的，
        // 那么这个时候就可以将lambda 替换为方法引用
//        studentDes.sort(StudentDe::compareStudent1ByScore);
//        studentDes.forEach(studentDe ->
//                System.out.println("使用方法引用的方式进行排序==="+studentDe.getScore()));

        // 根据字母排序
//        studentDes.sort((studentParam1, studentParam2) ->
//                StudentDe.compareStudent1ByName(studentParam1, studentParam2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用传统的lambda方式进行字母排序==="+studentDe.getName()));
//        studentDes.sort(StudentDe::compareStudent1ByName);
//        studentDes.forEach(studentDe ->
//        System.out.println("使用方法引用的方式根据字母排序==="+studentDe.getName()));


        /**
         * 第二种方式: 引用名(对象名，其实就是对象的一个引用)::实例方法名
         */
        // 先使用传统lambda方式
        StudentComparator studentComparator = new StudentComparator();
        //studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByScore(st1, st2));
        //studentDes.forEach(studentDe ->
        //System.out.println("使用lambda方式排序"+studentDe.getScore()));
        // 使用方法引用方式
        studentDes.sort(studentComparator::compareStudent1ByScore);
        studentDes.forEach(studentDe ->
                System.out.println("使用方法引用方式排序"+studentDe.getScore()));

        // 根据字母排序
        studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByName(st1, st2));
        studentDes.forEach(studentDe ->
                System.out.println("使用方法引用方式排序"+studentDe.getName()));


    }
}
