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
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        // set 表示中间结果容器类型
        // item 表示stream中下一个元素的类型
        return (set, item) -> {
            // 一共10元素，按照顺序执行，要往中间容器中累加10个，那么这个方法就会被调用10次
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
        System.out.println("combiner invoked!");
        return (set1, set2) -> {
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
                // IDENTITY_FINISH
                EnumSet.of(UNORDERED,CONCURRENT));

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
        //错误: Exception in thread "main" java.util.ConcurrentModificationException: java.util.ConcurrentModificationException
        // 原因:
    }

    public static void main(String[] args) {
        int count = 100;
        for (int i = 0; i <count; i++) {
            List<String> list = Arrays.asList("hello", "world", "welcome","zairian","lisa",
                    "wadge","zambia","cc","b","d");
            Set<String> set = new HashSet<>();
            set.addAll(list);

            System.out.println("set" + set);
//        Map<String, String> map = set.stream().collect(new CustomizeCollector2<>());
            //并行流
            Map<String, String> map = set.parallelStream().collect(new CustomizeCollector2<>());
            System.out.println(map);
        }

    }
}
