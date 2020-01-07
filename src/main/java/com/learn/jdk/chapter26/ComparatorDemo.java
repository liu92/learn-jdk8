package com.learn.jdk.chapter26;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * chapter26
 * @ClassName: ComparatorDemo
 * @Description: Comparator 详解与类型推断
 * @Author: lin
 * @Date: 2020/1/5 10:58
 * History:
 * @<version> 1.0
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //根据字母进行升序的排序
//        Collections.sort(list);
//        System.out.println(list);
        // 字符串长度
//        Collections.sort(list, (item1 , item2) -> item1.length() - item2.length());
//        Collections.sort(list, (item1 , item2) -> item2.length() - item1.length());
//        System.out.println(list);

        // 使用方法引用, 降序
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
//        System.out.println(list);

        //这里使用item -> item.length()报错，
        // lambda表达式的参数推断, 如果编译器不能推断类型是，必须要明确指定参数类型
        // 这里推断 item 的类型 是根据 comparingInt(ToIntFunction<? super T> keyExtractor)方法来进行的，
        // 而这个方法里面的泛型T 是字符串类型，而? 是 T的父类，或者父类的父类。
        // 所以这里无法去推断类型，只能是给一个同样的类型Object。
        // 加上reversed()方法会影响类型的推断。
//        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());
//        System.out.println(list);

        //注意： 【这个编译报错】，
        // Collections.sort(list, Comparator.comparingInt((Boolean item) -> 1).reversed());


        //先按照字符串的长度，然后在按照字母的升序顺序排序(这里使用thenComparing)，使用jdk的不区分大小写
        //Collections.sort(list, Comparator.comparingInt(String::length).
        // thenComparing(String.CASE_INSENSITIVE_ORDER));


//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));

        // 方法引用的方式
//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing(Comparator.comparing(String::toLowerCase)));

        //Comparator.comparing()的重载方法， Comparator.reverseOrder 将顺序逆转过来
//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        // 首先根据字符串长度排序升序，再reversed进行降序排序，然后thenComparing是对于前面结果等于0的才去引用thenComparing，
        // 如果不等于0就不会应用thenComparing，
        // 将字母转换为小写，再逆序排序
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
//                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        // 输出结果 [welcome, world, nihao, hello]


        //thenComparing 比较器的串联
        // 再加一个thenComparing, 结果还是和上面的一样，为什么呢？
        // 这是因为在Comparator.comparingInt(String::length).reversed()，
        // thenComparing 第一次的应用的时候 其中的元素[welcome, nihao, hello, world].这个时候对其后面的三个元素进行比较
        // 也就是两两比较不会为零， 返回的  res = compare(c1, c2); res 不为零。 所以第二个thenComparing 不会引用到。
        // 因此结果还是一样的
        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));
        //

        System.out.println(list);






    }
}
