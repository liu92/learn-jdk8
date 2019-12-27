package com.learn.jdk.chapter7;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * chapter7  function与 biFunction函数式接口详解
 * @ClassName: FunctionDemo2
 * @Description:  function与 biFunction函数式接口详解
 * @Author: lin
 * @Date: 2019/12/27 15:01
 * History:
 * @<version> 1.0
 */
public class FunctionDemo2 {
    public static void main(String[] args) {
        FunctionDemo2 function  = new FunctionDemo2();
        // 调用compose方法
        // 先计算后面的 va*va, 然后将其计算的结果传递当前对象的apply方法, 再去计算
        // va * 3
        // 2 * 2 -> 4 然后 4 * 3 =12
        System.out.println(function.compute1(2, va->va * 3, va -> va * va));
        // 调用andThen方法
        // 先计算前面的 va * 3, 然后将其计算的结果传递当前对象的apply方法, 再去计算
        // va * va
        // 2 * 3 =6 --> 6*6 =36
        System.out.println(function.compute2(2, va->va * 3, va -> va * va));

        // 输入两个参数 返回一个结果
        System.out.println(function.compute3(1, 2 ,(va1, va2) -> va1 + va2));
        System.out.println(function.compute3(1, 2 ,(va1, va2) -> va1 - va2));
        System.out.println(function.compute3(1, 2 ,(va1, va2) -> va1 * va2));
        System.out.println(function.compute3(1, 2 ,(va1, va2) -> va1 / va2));
        //  BiFunction lambda表达式 (v1, v2)-> v1 + v2
        // Function lambda表达式 v3-> v3 * v3
        // 首先将输入应用到 当前的biFunction, 也就是(v1, v2)-> v1 + v2
        // (v1, v2)-> v1 + v2的结果作为 参数的function 传入, 也就是 v3-> v3 * v3的输入
        System.out.println(function.compute4(2, 3, (v1, v2)-> v1 + v2, v3-> v3 * v3));
        // 思考： 为什么BiFunction没有 compose方法?
        // 个人理解： 如果有compose方法，那么在接收参数后，首先会调用apply(a),
        // 但是其引用返回的值只有一个，这样就你不能传入到 BiFunction中apply(a,b)方法去
    }

    /**
     * compose使用
     * @param t
     * @param function1
     * @param function2
     * @return
     */
    public  int compute1(int t, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        // 先去对输入参数应用 function2的 apply,
        // 然后把function2的结果作为function1的apply输入
        return  function1.compose(function2).apply(t);
    }

    /**
     * andThen使用
     * @param t
     * @param function1
     * @param function2
     * @return
     */
    public  int compute2(int t, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        // 先去对输入参数应用 function1的 apply,
        // 然后把function1的结果作为function2的apply输入
        return  function1.andThen(function2).apply(t);
    }

    /**
     * BiFunction使用
     * @param a
     * @param b
     * @param biFunction
     * @return
     */
    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return  biFunction.apply(a, b);
    }

    /**
     *  使用BiFunction中 andThen方法，
     *  注意这里andThen方法中接收的是Function,因为apply的返回值只有一个,
     *  然后这个值作andThen(Function<? super R, ? extends V> after) 方法中after为参数
     *  的输入，所以只有一个输入值 所以是Function类型。
     * @param a
     * @param b
     * @param biFunction
     * @param function
     * @return
     */
    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function ){
        // 将BiFunction 调用andThen 方法，然后将其内部方法中调用apply(a,b)方法
        // 这个方法返回值只有一个，然后将这个返回值作为after参数 ,通过after调用 apply方法
        return biFunction.andThen(function).apply(a, b);
    }
}
