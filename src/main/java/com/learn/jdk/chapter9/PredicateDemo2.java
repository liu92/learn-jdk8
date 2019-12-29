package com.learn.jdk.chapter9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**chapter10
 * @ClassName: PredicateDemo2
 * @Description:  predicate 深入剖析和 函数式编程本质
 * @Author: lin
 * @Date: 2019/12/29 22:05
 * History:
 * @<version> 1.0
 */
public class PredicateDemo2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PredicateDemo2 predicateDemo2 = new PredicateDemo2();
        predicateDemo2.conditionFilter(list, item -> item % 2 == 0);
        System.out.println("------------------------------");
        predicateDemo2.conditionFilter(list, item -> item % 2 != 0);
        System.out.println("------------------------------");
        predicateDemo2.conditionFilter(list, item -> item > 5 );
        System.out.println("------------------------------");
        predicateDemo2.conditionFilter(list, item -> item > 3 );

        //所有的判断都为真
        predicateDemo2.conditionFilter(list, item-> true );
        //所有的判断都为假
        predicateDemo2.conditionFilter(list, item-> false );

        System.out.println("------------------------------");
        predicateDemo2.conditionFilter2(list, item -> item > 5, item -> item % 2 ==0);

        //
        System.out.println("isEqual======" + predicateDemo2.isEqual("test").test("test"));
    }



    /**
     * 函数编程和一起面向对象编程的区别，
     * 面向对象编程以前只需要接收 list, 具体怎么对这个参数进行处理，是由方法里面的代码来决定的
     * 函数编程实际上是把怎么进行处理，在调用的时候动态传入进来，相比原来在方法体里面编写业务处理逻辑，
     * 现在是将业务处理逻辑 抽取出来，然后放在调用者这一端。通过参数给动态的传入进来
     * @param list
     * @param predicate
     */
    public void  conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            //函数式编程它提供了一种更高层次的抽象化
            if(predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void  conditionFilter2(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2){
        for (Integer integer : list) {
            //既要符合predicate的要求，也要符合predicate2的要求。然后才打印，否则不打印
            // predicate.and(predicate2).test(integer) ====> 6 ,8,10
            // predicate.or(predicate2).test(integer) ====> 2, 4, 6, 7, 8 ,9, 10
            // predicate.and(predicate2).negate().test(integer) ====> 1, 2, 3, 4, 5,  7, 9
            if(predicate.and(predicate2).negate().test(integer)){
                System.out.println(integer);
            }
        }
    }

    public Predicate<String> isEqual(Object object){
        return  Predicate.isEqual(object);
    }
}
