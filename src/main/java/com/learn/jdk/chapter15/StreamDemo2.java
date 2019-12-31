package com.learn.jdk.chapter15;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo2
 * @Description: IntStream 应用
 * @Author: lin
 * @Date: 2019/12/31 15:35
 * History:
 * @<version> 1.0
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        //示例:将整型list中元素都乘以2，然后计算乘以2以后集合中元素和
        List<Integer> list = Arrays.asList(2, 4, 3, 5, 7);
//        int sum = 0;
//        int temp = 0;
//        // 原来的for循环方法处理
//        for (Integer integer : list) {
//             sum += integer*2;
//        }
//        System.out.println("集合元素相加===="+sum);

        // 现在使用stream来进行元素求和.
        // 这里我们就可以知道流的构成
        // list 就是 源
        // map(itm -> itm * 2): 中间操作
        // reduce() 就是终止操作
        // reduce(0, Integer::sum) 这个reduce代码的意思就是0 + itm*2,
        System.out.println("元素求和==="+list.stream().map(itm -> itm * 2).
                reduce(0, Integer::sum));
    }
}
