package com.learn.jdk.chapter17;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo7
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 10:02
 * History:
 * @<version> 1.0
 */
public class StreamDemo7 {
    public static void main(String[] args) {
        //1. Stream.generate() 方法使用
//        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        // stream.findFirst() 返回流里面的第一个元素。
        // 为什么返回Optional，是为了避免NPE(空指针)
        // System.out.println(stream.findFirst().get());
//        stream.findFirst().ifPresent(System.out::println);

        // 2. Stream.iterate() 使用,如果不加限制那么就会产生无限流
        // 加上面limit就表示限制产生6个
        // 结果 1,3,5,7,9, 11
//        Stream.iterate(1, item -> item + 2)
//                .limit(6).forEach(System.out::println);
        // 找出该流中大于2的元素，然后将每个元素乘以2, 然后忽略掉流中的前两个元素,
        // 然后再取出流中的前两个元素，最后求出流中元素的总和。
//        System.out.println("求和==========" + Stream.iterate(1, item -> item + 2).limit(6)
//                .filter(ite -> ite > 2)
//                .map(ite -> ite * 2)
//                .skip(2)
//                .limit(2).reduce(0, Integer::sum));
        //第二种方式 使用mapToInt(), 将其转换为int, 如果使用map()那么里面会有一个自动拆箱和装箱的操作，
        // 这个转换会有一点点的性能损耗，所以jdk它会去避免这些损耗，因此提供了针对特定的原生类型 mapToInt()
        // 所以这里使用mapToInt()

        Stream<Integer> stream1 = Stream.iterate(1, item -> item + 2).limit(6);
//        System.out.println(stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).sum());

        // 这种方法是推荐使用的写法
        // 找出流中最小的元素
        // min() 方法返回的是Optional,因为这样更好的去规避空指针，
        // 而sum()返回的是int,就算stream中没有元素，他也只是返回 0.
        // 所以这就是这两种的区别
//         stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).min().ifPresent(System.out::println);
         
         //3.使用summaryStatistics()方法
//         IntSummaryStatistics intSummaryStatistics = stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).summaryStatistics();
//         System.out.println(intSummaryStatistics.getMin());
//         System.out.println(intSummaryStatistics.getCount());
//         System.out.println(intSummaryStatistics.getMax());

         //4.stream不能重复使用, 下面的的代码会报错,因为stream1.filter()会产生一个新的stream,
         //而 strem1.distinct 又重复使用了原来的stream1, 所以会报错
//           System.out.println(stream1);
//           System.out.println(stream1.filter(item -> item > 2));
//           System.out.println(stream1.distinct());

         //5.下面的方式可以 避免上面重复使用stream问题
         System.out.println(stream1);
         Stream<Integer> stream2 = stream1.filter(item -> item > 2);
         System.out.println(stream2);
         Stream<Integer> stream3 = stream2.distinct();
         System.out.println(stream3);

    }
}
