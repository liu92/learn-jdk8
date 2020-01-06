package com.learn.jdk.chapter21;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamDemo14
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 22:17
 * History:
 * @<version> 1.0
 */
public class StreamDemo14 {
    public static void main(String[] args) {
        StudentTest test1 = new StudentTest("zaneta", 100, 20);
        StudentTest test2 = new StudentTest("lise", 80, 20);
        StudentTest test3 = new StudentTest("wadge", 90, 30);
        StudentTest test4 = new StudentTest("zeal", 70, 40);

        List<StudentTest> list = Arrays.asList(test1, test2, test3, test4);

        // 根据名称分组
//        Map<String, List<StudentTest>> map = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getName));
//        System.out.println(map);
        //根据分数分组
//        Map<Integer, List<StudentTest>> collect = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getScore));
//        System.out.println(collect);

        //根据名字分组，然后 count
//        Map<String, Long> collect2 = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getName, Collectors.counting()));
//        System.out.println(collect2);

        //根据名称分组，再求分数的平均
        Map<String, Double> collect3 = list.stream().
                collect(Collectors.groupingBy(StudentTest::getName,
                        Collectors.averagingLong(StudentTest::getScore)));
        System.out.println(collect3);

        // 分区partition, 就是Boolean值，要么true, 要么false
        Map<Boolean, List<StudentTest>> listMap = list.stream().
                collect(Collectors.partitioningBy(studentTest -> studentTest.getScore() >= 90));
        System.out.println(listMap);


        // 分组：group by
        // 分区：partition by (结果只会有两个分区，在程序语言中表达就是true 和false. 分区是分组的一种特例)
    }
}
