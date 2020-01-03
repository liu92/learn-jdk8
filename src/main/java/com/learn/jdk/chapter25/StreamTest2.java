package com.learn.jdk.chapter25;

import com.learn.jdk.chapter22.StudentTe;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * chapter25
 * @ClassName: StreamTest2
 * @Description: 收集器用法详解与多分组和分区
 * @Author: lin
 * @Date: 2020/1/3 16:21
 * History:
 * @<version> 1.0
 */
public class StreamTest2 {
    public static void main(String[] args) {

        // 测试
        StudentTe  te1 = new StudentTe("zings", 74);
        StudentTe  te2 = new StudentTe("lisa", 74);
        StudentTe  te3 = new StudentTe("wang", 100);
        StudentTe  te4 = new StudentTe("zarla", 80);
        StudentTe  te5 = new StudentTe("zarla", 80);

        List<StudentTe> studentTes = Arrays.asList(te1, te2, te3, te4, te5);
        //求出分数最低的学生

        studentTes.stream().collect(minBy(Comparator.comparingInt(StudentTe::getScore))).
                ifPresent(System.out::println);
        studentTes.stream().collect(maxBy(Comparator.comparingInt(StudentTe::getScore))).
                ifPresent(System.out::println);
        System.out.println(studentTes.stream().collect(averagingInt(StudentTe::getScore)));

        System.out.println(studentTes.stream().collect(summingInt(StudentTe::getScore)));
        // 求出摘要信息
        IntSummaryStatistics intSummaryStatistics = studentTes.stream().collect(summarizingInt(StudentTe::getScore));
        System.out.println(intSummaryStatistics);

        System.out.println("-----------------------");
        //拼接名字
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining()));
        //joining(", ")
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining(", ")));
        //加入前缀和后缀
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining(", ", "<begin> ", " <end>")));

        System.out.println("-----------------------");
        // 分组， 第一次分组外层key 是一个integer类型，然后在对第一次分组的进行第二次分组
        // 第二次分组key是String类型 ,value是 List<StudentTe>.
        Map<Integer, Map<String, List<StudentTe>>> collect = studentTes.stream().
                collect(groupingBy(StudentTe::getScore, groupingBy(StudentTe::getName)));
        System.out.println("分组= " + collect);

        System.out.println("----------------------------");
        // 分区
        Map<Boolean, List<StudentTe>> collect1 = studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80));
        System.out.println("分区: "+ collect1);
        // 在根据分数大于80，进行再次分区。

        Map<Boolean, Map<Boolean, List<StudentTe>>> mapMap =
        studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80,
                        partitioningBy(studentTe -> studentTe.getScore() > 90)));
        //返回的结果就是嵌套的了
        System.out.println("再分区: " + mapMap);

        System.out.println("---------------------");
        // 求出每个分区中，学生的个数
        Map<Boolean, Long> longMap = studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80, counting()));
        System.out.println("求出每个分区中，学生的个数 " + longMap);

        // 根据名字分组，在得到学生的分数
        // 使用collectingAndThen()去收集最小值，收集完之后 然后在从里面把它包含的值取出来，
        // 这个get一定是有值的，这是因为groupingBy()分组. 每一组中是一定有数据的。
        Map<String, StudentTe> collect2 = studentTes.stream().
                collect(groupingBy(StudentTe::getName,
                        collectingAndThen(minBy(Comparator.comparingInt(StudentTe::getScore)),
                                Optional::get)));
        System.out.println("分组: " + collect2);
    }
}
