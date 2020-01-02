package com.learn.jdk.chapter17;

import java.util.stream.IntStream;

/**
 * @ClassName: StreamDemo9
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 14:19
 * History:
 * @<version> 1.0
 */
public class StreamDemo9 {
    public static void main(String[] args) {
        // 下面操作是不会结束的。是因为distinct 只是去重，而前面的iterate 一直返回0,1,
        // 没有一个限定范围
//        IntStream.iterate(0, i -> (i + 1) % 2)
//                .distinct()
//                .limit(6)
//                .forEach(System.out::println);

        // 该为这种方式就可以了, 限定流产生6个元素，再去重
        IntStream.iterate(0, i -> (i + 1) % 2)
                .limit(6).distinct().forEach(System.out::println);
    }
}
