package com.learn.jdk.chapter20;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * chapter 20
 * @ClassName: SteamDemo10
 * @Description: stream的短路和并发流
 * @Author: lin
 * @Date: 2020/1/2 16:29
 * History:
 * @<version> 1.0
 */
public class SteamDemo10 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        int count = 5000000;
        for (int i = 0; i < count; i++) {
             list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        // 纳秒
        long startTime = System.nanoTime();
        // 串行流
//        list.stream().sorted().count();
        // 开始排序
        //排序耗时:6787

        // 并行流
        list.parallelStream().sorted().count();
        //开始排序
        //排序耗时:3782

        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("排序耗时:" + millis);
    }
}
