package com.learn.jdk.chapter29;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;
import static java.util.stream.Collectors.groupingBy;

/**
 * chapter29
 * @ClassName: CustomizeCollector2
 * @Description: 自定义收集器深度剖析与并行看流陷阱
 * 定义类的条件：
 * 输入：Set<String>
 * 输出：Map<String,String>
 * @Author: lin
 * @Date: 2020/1/8 16:25
 * History:
 * @<version> 1.0
 */
public class CustomizeCollector2<T> implements Collector<T, Set<T>, Map<T,T>> {
    /**
     * 用于返回中间收集的结果容器
     * @return
     */
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
//        return HashSet::new;
        return () ->{
            // 如果是串行流,那么这里只打印一次,也就是说产生是结果容器只有一个。
            // 如果是并行流,那么这里会打印多次, 这里会打印多个结果容器
            System.out.println("-------------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        // set 表示中间结果容器类型
        // item 表示stream中下一个元素的类型
        return (set, item) -> {
            // 一共10元素，按照顺序执行,要往中间容器中累加10个,那么这个方法就会被调用10次
            // 这里打印出 set 就会有异常。
            // 如果是并行的话,那么绝对不要再accumulator中 对目标或者中间的结果容器执行任何的额外操作,
            // 比如 一边执行打印,一边添加。 只单纯的执行本身该执行的操作
            // 否则在并行的情况下会抛出异常。

            // 因此在没有CONCURRENT的情况下 就变成多个线程会有多个中间结果容器,
            // 比如说有3个线程那么就有3个中间结果容器, 每一次的set是属于线程本身的set。3个线程会有3个set。他们之间是互补干扰的
            System.out.println("accumulator: " +set+", " + Thread.currentThread().getName());
            set.add(item);
        };
    }

    /**
     * combiner只有在并行stream时才会被调用，
     * 如果是串行stream是不会被调用的，因为串行流只有一个线程，
     * 这个一个线程会执行所以的任务，所以不需要合并的操作，这个时候combiner是不会被调用的
     * @return
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        // 这里打印只是表示combiner被调用,但不表示lambda表达式被调用
        System.out.println("combiner invoked!");
        // 在并行情况下并且收集器本身没有CONCURRENT这个特性的情况下,combiner才会被调用,
        // 如果是并行流并且存在CONCURRENT这个特性,那么combiner不会被调用
        return (set1, set2) -> {
            System.out.println("set1: " + set1);
            System.out.println("set2: " + set2);
            set1.addAll(set2);
            return  set1;
        };
    }

    /**
     * finisher的调用是, 在没有IDENTITY_FINISH时才会真正调用
     * @return
     */
    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");
        //转换，如果类型不一致那么就是抛出异常
        return set -> {
//            Map<T,T> map = new HashMap<>(16);
            //排序
            Map<T,T> map = new TreeMap<>();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    /**
     * 收集器有什么特性，实际上就是你赋予的。
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(
                // IDENTITY_FINISH, CONCURRENT
                EnumSet.of(UNORDERED));

//        return Collections.unmodifiableSet(
//                // IDENTITY_FINISH
//                EnumSet.of(UNORDERED,IDENTITY_FINISH));
        // 这里先不加IDENTITY_FINISH, 其运行结果可以得到结果，
        // 如果这里加上了IDENTITY_FINISH,那么在finisher进行转换的时候 会怎样呢？
        // 加上之后 就会报错，Exception in thread "main" java.lang.ClassCastException: java.util.HashSet cannot be cast to java.util.Map,
        // 也就说在有IDENTITY_FINISH是 类型转换的时候报错了,因为中间容器类型和返回类型不一致


        //  return Collections.unmodifiableSet(
        //                // IDENTITY_FINISH
        //                EnumSet.of(UNORDERED,CONCURRENT));
        //思考:这里如果加上了CONCURRENT,那么在并行流情况下执行会出现错误。这个出现错误的原因是什么？
        //错误: Exception in thread "main" java.util.ConcurrentModificationException:
        // java.util.ConcurrentModificationException
        // 原因: 这里猜测是因为在并行流的情况下,多线程执行调用accumulator进行计算,
        // 但是并发这种情况下如果没有 锁的情况下,可能造成了并发异常
    }

    public static void main(String[] args) {
        System.out.println("超线程数: "+Runtime.getRuntime().availableProcessors());

        int count = 1;
        for (int i = 0; i <count; i++) {
            List<String> list = Arrays.asList("hello", "world", "welcome","zairian","lisa",
                    "wadge","zambia","cc","b","d");
            Set<String> set = new HashSet<>();
            set.addAll(list);

            System.out.println("set" + set);
//        Map<String, String> map = set.stream().collect(new CustomizeCollector2<>());
            //并行流
            Map<String, String> map = set.parallelStream().collect(new CustomizeCollector2<>());
            // 再上面的characteristics中如果不加上了CONCURRENT,
            // 还是可以使用并行流只不过这个并行流操作的就不是一个结果容器了,而是多个结果容器了,
            // 也就是说有多少个线程就有多少个结果容器被操作。
            // 如果加上CONCURRENT,就表示多个线程操作一个结果容器，那么最终的结果容器只有一个。
            // 如果结果容器只有一个，那么多个线程操作一个结果容器，combiner就无需合并了
//            System.out.println(map);

//            Map<String, String> map = set.stream().
//                    parallel().sequential().parallel().collect(new CustomizeCollector2<>());
            // 这里的这种写法,看最后一个串行的还是并行的,
            // 如果最后一个parallel就是并行,如果是sequential那么就是串行的
        }

    }
}
