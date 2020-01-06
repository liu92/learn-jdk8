package com.learn.jdk.chapter13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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
//        studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByScore(st1, st2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用lambda方式排序"+studentDe.getScore()));
        // 使用方法引用方式

//        studentDes.sort(studentComparator::compareStudent1ByScore);
//        studentDes.forEach(studentDe ->
//               System.out.println("使用方法引用方式排序"+studentDe.getScore()));

        // 根据字母排序
//        studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByName(st1, st2));
//        studentDes.forEach(studentDe ->
//               System.out.println("使用lambda方式排序"+studentDe.getName()));

//        studentDes.sort(studentComparator::compareStudent1ByName);
//        studentDes.forEach(studentDe ->
//                System.out.println("使用方法引用方式排序" + studentDe.getName()));

         // 第三种: 类名::实例方法名
         // 这里和上面两种的区别就是，上面的调用都很明确
         // 而下面这种就不是那么明确, 实例方法一定是对象来调用的,
         // 但是这里使用的是类名来调用实例方法。这是不可能直接调用实例方法的。
         // 那么这个实例方法一定是有那么一个对象来进行调用。
         // 那么这个对象就是sort 方法里面接收的 lambda表达式的"第一个参数" 来去调用的compareByScore
         // 如果接收多个参数 除了第一个参数之外，后续所有参数都做为这个方法的参数 传递进去
         // 就是类似 这样 (st1, st2)-> st1.compareByScore( st2),
         // 第一个参数 st1, 第二个参数st2 作为方法传进去的参数
//         studentDes.sort(StudentDe::compareByScore);
//         studentDes.forEach(studentDe ->
//                System.out.println("第三种使用类名::实例方法引用方式进行分数排序=="
//                        + studentDe.getScore()));

//         studentDes.sort(StudentDe::compareByName);
//         studentDes.forEach(studentDe ->
//                System.out.println("第三种使用类名::实例方法引用方式进行名字排序=="
//                        + studentDe.getName()));

         //例子
//         List<String> cis = Arrays.asList("sichuang","beijing","anhui","guangdong");
//         Collections.sort(cis, (cis1, cis2)-> cis1.compareToIgnoreCase(cis2));
//         cis.forEach(ci -> System.out.println(ci));
         // 这两种形式就是等价的
//         Collections.sort(cis, String::compareToIgnoreCase);
//         cis.forEach(System.out::println);


         MethodReferenceDemo2 methodDemo2 = new MethodReferenceDemo2();
         System.out.println(methodDemo2.getString(String::new));

         System.out.println(methodDemo2.getString2("hello", String::new));
    }

    public String getString(Supplier<String> supplier){
       return supplier.get() + "test";
    }

    public String getString2(String str, Function<String, String> function){
        return function.apply(str);
    }
}
