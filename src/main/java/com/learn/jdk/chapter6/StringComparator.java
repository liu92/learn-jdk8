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
        //第二种排序, 这种排序方式 比较冗余，
        // 2 它会提示 你使用Comparator.reverseOrder() 方法来进行替换
        Collections.sort(list, (String o1, String o2)-> {
            return o2.compareTo(o1);
        });
        // 2.1 上面的还可以修改为, 因为这list本身是字符串类型的
        // 所以可以省略 o1,o2 类型声明
        Collections.sort(list , (o1, o2)->{
           return o2.compareTo(o1);
        });
        //2.2 还可以修改为下面的
        // expression o2.compareTo(o1)
        // statement {return o2.compareTo(o1);} 这是一个完整的语句 必须有分号
        Collections.sort(list, (o1,o2)-> o2.compareTo(o1));

        // Comparator.reverseOrder() 方法来替换上面的排序,
        // 这一行代码就可以代替上面的排序，但是这种方法脱离了lambda表达
        //Collections.sort(list, Comparator.reverseOrder());
         System.out.println(list);
    }
}
