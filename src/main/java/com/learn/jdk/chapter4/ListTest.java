package com.learn.jdk.chapter4;

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
        // 这个 forecah 方法是一个默认方法，它是 定义在Iterabel中，这个和抽象类有点类似
        // 抽象类中 可以有抽象方法和具体实现方法，抽象方法必须由子类实现 ，而具体实现可以直接拿过来使用。
        // 所以说这个 默认方法的定义和 抽象类中的 具体实现方法类似。

        lists.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("---------------------");

        // 通过方法引用来 创建函数接口的实例
        // method reference , 这个其实就是上面的另一种形式
        lists.forEach(System.out::println);
    }

}
