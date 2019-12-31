package com.learn.jdk.chapter15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * chapter16
 * @ClassName: StreamDemo4
 * @Description: Stream 的深度解析和源码实践
 * @Author: lin
 * @Date: 2019/12/31 17:00
 * History:
 * @<version> 1.0
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        //1
//        Stream<String> stream = Stream.of("hello", "world", "lin");
        //将一个stream对象 转换成 字符串数组
        //String[] stringArray1 = stream.toArray(itm -> new String[itm]);
        //方法引用, 这个正好符合IntFunction<A[]> 的声明
        //
//        String[] stringArray = stream.toArray(String[]::new);
//        Arrays.asList(stringArray).forEach(System.out::println);

        //2:将这个流转换成list
        Stream<String> stream1 = Stream.of("hello", "world", "lin");
//        List<String> collect = stream1.collect(Collectors.toList());
        // 使用collect的下面方法进行处理,
        // 第一个参数:不接受参数返回一个结果,
        // 第二个参数:BiConsumer接收两个参数，accumulator是累加器
        // 就是对集合中每一个元素进行遍历, 然后把这个集合的每一个元素应用到这个结果上面
        // 将每一个元素添加到这个返回的集合中， 其中第一个参数theList就是要返回的集合，
        //  第二个item是遍历的stream的当中的元素，将每一个元素都添加到这个theList当中
        // 第三个参数:也是BiConsumer, 不过这个combiner是合并器，
        //  也就是将上一次的操作结果和下一次的操作结果合并在一起
        // theList1.addAll(theList2) 就是讲上一次遍历得到的每一个list都添加到最终的theList1中去,
        // 最后返回的就theList1, 这个theList1就是最终要返回的对象,实际上就是ArrayList这个对象
        //<R> R collect(Supplier<R> supplier,
        //                  BiConsumer<R, ? super T> accumulator,
        //                  BiConsumer<R, R> combiner);
//        List<String> collect = stream1.collect(()-> new ArrayList<>(), (theList, item) ->
//                theList.add(item), (theList1, theList2) -> theList1.addAll(theList2));

        //使用方法引用来实现
        List<String> collect = stream1.collect(LinkedList::new,
                LinkedList::add, LinkedList::addAll);
        collect.forEach(System.out::println);

    }
}
