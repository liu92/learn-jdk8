package com.learn.jdk.chapter44;

/**
 * chapter44
 * @ClassName: LambdaTest
 * @Description:
 * @Author: lin
 * @Date: 2020/1/19 14:05
 * History:
 * @<version> 1.0
 */
public class LambdaTest {
    /**
     * 这个this表示 当前类对象的实例。 lambda表达式和匿名内部类 完全不是同一个事
     *  匿名内部类是一个新的作用域，而lambda的作用域是外层作用域。
      */
    Runnable r1 = () -> System.out.println(this);

    /**
     * 这个 new Runnable(){} 表示实现里Runnable接口的一个实现类实例,
     * 这个类实际上是没有名字的 也就是匿名类
     */
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            // 这个this 表示匿名内部类 所对应的对象。
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();

        System.out.println("**************************");

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();

        // 问题:两个this的输出是什么,输出是一样的吗？ 如果不一样那么分别是什么？
        // 1、第一个r1打印的是 com.learn.jdk.chapter44.LambdaTest@5983b966
        // 2、第二个r2输出的是 com.learn.jdk.chapter44.LambdaTest$1@2cacedc9
        // 从第二个输出可以知道 LambdaTest$1其实是一个匿名内部类的特点, 这个匿名类本身没有名字所以是匿名类,
        // 那么在java中匿名类 表示是就是用外层的public类(文件名字)后面跟上一个$符合,
        // 然后按照顺序 如果第一个匿名类 就有$1来表示,如果在当前文件中出现第二个匿名类那么就是$2这样排下来
    }

}
