package com.learn.jdk.chapter22;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * chapter22
 * @ClassName: StreamTest1
 * @Description: 通过源码分析 ,深入理解stream, collect等
 * @Author: lin
 * @Date: 2020/1/3 8:53
 * History:
 * @<version> 1.0
 */
public class StreamTest1 {
    public static void main(String[] args) {
        //
        StudentTe t1 = new StudentTe("zings", 70);
        StudentTe t2 = new StudentTe("lisa", 90);
        StudentTe t3 = new StudentTe("wang", 100);
        StudentTe t4 = new StudentTe("zarla", 90);

        List<StudentTe> studentTes = Arrays.asList(t1, t2, t3, t4);

        // 静态导入 Collectors类
        // 所以这里显示Collectors.toList()
//        List<StudentTe> studentTeList = studentTes.stream().
//                collect(toList());
//        studentTeList.forEach(System.out::println);

//        System.out.println("---------------------");
//
//        System.out.println("count: " + studentTes.stream().collect(counting()));
//        System.out.println("count: " + studentTes.stream().count());
    }

}
