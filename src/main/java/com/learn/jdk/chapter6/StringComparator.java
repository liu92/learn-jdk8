package com.learn.jdk.chapter6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * chapter6 function接口详解
 * @ClassName: StringComparator
 * @Description: 字符串比较
 * @Author: lin
 * @Date: 2019/12/26 23:32
 * History:
 * @<version> 1.0
 */
public class StringComparator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        // 第一种、对集合排序
        // Collections.sort(list, new Comparator<String>() {
        // @Override
        // public int compare(String o1, String o2) {
        //   // 倒序排序
        //   return o2.compareTo(o1);
        //  }
        //  });
        // System.out.println(list);

    }
}
