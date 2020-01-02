package com.learn.jdk.chapter17;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo8
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 13:59
 * History:
 * @<version> 1.0
 */
public class StreamDemo8 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "lin");
        // 将集合中元素的首字母转换成大写，其他的字母不变
        //1.打印
//        list.stream().map(item ->
//                item.substring(0,1).toUpperCase() + item.substring(1))
//                .forEach(System.out::println);

        //2.下面的方式 不会打印出东西，因为没有终止操作
        //如果在程序的下面 加上forEach 就可以有数据打印出了
         list.stream().map(item -> {
               String result = item.substring(0,1).toUpperCase() + item.substring(1);
               System.out.println("t");
               return result;
         });
    }
}
