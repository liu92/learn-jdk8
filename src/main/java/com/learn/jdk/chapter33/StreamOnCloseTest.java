package com.learn.jdk.chapter33;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *  chapter33
 * @ClassName: StreamOnCloseTest
 * @Description: BaseStream 中方法onclose使用
 * @Author: lin
 * @Date: 2020/1/16 10:52
 * History:
 * @<version> 1.0
 */
public class StreamOnCloseTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

         //1、
        // NullPointerException nullPointerException = new NullPointerException(" my exception");

         //2、
        // BaseStream接口中 onclose方法使用 和  try-with-resources statement.方式
        // 在BaseStream中 onClose方法中 抛出异常不影响其 后面onClose的执行
        // 这样要注意 如果没有执行 close方法 那么就不会执行onClose方法
        try(Stream<String> stream = list.stream()){
            stream.onClose(() -> {
                System.out.println("aaa");
                // 如果两个地方抛出了相同的异常,那么异常是不会被压制的。直接就抛出去了
                //1、比如 这种是相同的对象
                // throw nullPointerException; 这种 下面的也一样
                //2、
                //throw  new NullPointerException("first exception");

                //3、抛出相同的异常 这种方式 下面的异常会被压制
                throw  new NullPointerException("first exception");
            }).onClose(() -> {
                System.out.println("bbb");
                //1、 Suppressed 压制的异常,只有第一个异常进行传递,其他的异常被压制
                // throw new ArithmeticException("second exception");

                //2、 这个和上面抛出同一个对象
                // throw nullPointerException;

                //3、抛出相同的异常
                throw  new NullPointerException("second exception");
            }).forEach(System.out::println);
        }
    }
}
