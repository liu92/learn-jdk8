package com.learn.jdk.chapter28;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * chapter28
 * @ClassName: CustomizeCollector
 * @Description: 自定义 收集器
 * 这里Collector<T, Set<T>, Set<T>>
 *      中 第一个去遍历的流当中每一个元素类型设置为T类型
 *      第二个 中间的结果类型Set<T>,
 *      第三个返回的最终结果类型 这里设定和第二个类型一样
 * @Author: lin
 * @Date: 2020/1/8 13:48
 * History:
 *
 * @<version> 1.0
 */
public class CustomizeCollector<T> implements Collector<T, Set<T>, Set<T>> {
    /**
     * 用于返回中间收集的结果容器
     *
     * @return
     */
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        // ()-> new HashSet<>()
        // 这个返回的是中间结果容器的一个初始化对象，
        // 后面是不断的往这个结果容器中追加内容
        return HashSet<T>::new;
    }

    /**
     * 累加器，不断的从流中遍历元素，将这个元素从<A,T>  T加到 A当中
     * 这里BiConsumer<A, T>, Set<T>就是A, T是T.
     * @return
     */
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        // :: 指定到BiConsumer, add实际上对应的BiConsumer中的accept(T t, U u)。
        return Set<T>::add;
        // 这个和上面的等价
        //return (item1, item2) -> item1.add(item2);

        // 这种写法不可以，不能编译,
        // Set<T>本身是接口，HashSet是Set的一个实现，当然也有add.
        // 但是为什么不能使用HashSet<T>::add
        // 这是因为上面的Supplier<<Set<T>> 返回的是一个中间结果容器
        // 并且这个方法是往这个中间结果容器中添加内容，
        // 当这个返回的类型和中间结果返回的容器类型不一致那么就不允许这么做
        // 但是如果改为Set<T>这个就符合Set<T>接口的要求，
        // 因此这里就不能使用一个具体类型的set. 要使用给的泛型类型。
//        return  HashSet<T>::add;
    }

    /**
     * 表示对并行流，多个线程所执行的部分结果合并到一起
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
     * 是一个可选的操作，根据实际情况提供一个实现。
     * finisher是一个完成器，将结果Function<A, R> , 结果A转换成R ，
     * 在有些时候这个A就是R
     * 1、执行是在combiner 后面执行，
     * 将所有的结果都合并到一起，如果是单线程就不用合并，
     * 合并完之后去返回一个最终类型，
     * 在这个程序中最终的结果类型是一样的，
     * 就是我们只需把中间的累加之后的结果容器返回给用户就可以了.
     *
     * 2、除非这个累加完之后得到的结果容器跟你想要得到的对象类型是不一致的，
     * 那么finisher函数你必须要显示的正确的把它实现出来
     * @return
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoked!");
//        return ite -> ite;
        // identity返回的就是一个函数，这个函数总是把它的输入参数给原封不动的返回
        return  Function.identity();
    }

    /**
     * 返回一个set对象，Characteristics表示当收集器的一个特性，这个特性就是三个枚举值
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        //返回一个集合，这个集合标识着当前收集器的很多特性
        // 所以返回一个不可变的集合
        return Collections.unmodifiableSet(
                EnumSet.of(IDENTITY_FINISH,UNORDERED));
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome");
        Set<String> set = list.stream().collect(new CustomizeCollector<>());
        System.out.println(set);
    }
//运行结果
//supplier invoked!
//accumulator invoked!
//combiner invoked!
//characteristics invoked!
//characteristics invoked!
//[world, hello, welcome]
    // 运行结果可以从代码的实现类中了解到，在ReferencePipeline中的collect的方法实现中知道
    // 如果有IDENTITY_FINISH，就会调用finisher,如果没有就不会调用，
    // 并且在实现类的方法中我们可以知道 在进行强制类型转换时一定要确保成功因为在源码中根本没有去进行验证，
//    return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
//               ? (R) container
//               : collector.finisher().apply(container);
    // 如果 A和R类型不一样那么转换就会抛出异常。所以这里要注意

}
