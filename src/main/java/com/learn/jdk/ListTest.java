package com.learn.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * jdk1.8 遍历集合
 * @ClassName: ListTest
 * @Description:
 * @Author: lin
 * @Date: 2019/12/2 22:20
 * History:
 * @<version> 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 2, 3 , 4, 5, 6, 7, 8);
        // 以前的写法
        // 第一种
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
        System.out.println("---------------------");
        // 第二种
        for (Integer list : lists) {
            System.out.println(list);
        }

        System.out.println("---------------------");

        // 第三种, 这里
        lists.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("---------------------");
    }

}
