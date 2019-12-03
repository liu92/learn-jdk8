package com.learn.jdk;

/**
 * @ClassName: MyInteface
 * @Description:
 * @Author: lin
 * @Date: 2019/12/3 22:30
 * History:
 * @<version> 1.0
 */
@FunctionalInterface
public interface MyInterface {
    /**
     * 只有一个抽象方法，这个抽象方法交给子类去实现
     * te
     */
    void  test();

    /**
     *  1、如果一个接口声明了一个抽象方法，但是这个抽象方法它复写了
     *  java.lang.Object类 中的某一个public方法，
     *  那么这是不会将接口的抽象方法加一的。 所以这个方法重写了  java.lang.Object类中 toString方法。
     *  所以这个 接口中还是只有一个抽象方法 test()方法。
     *  2、如果这个MyInterface 接口有实现类，那么这里的两个方法都会被实现出来，
     *  然而 java.lang.Object 类是java类中所有类的父类。那么这个接口的实现类
     *  一定是或间接的继承Object类。那么就将toString方法继承过来了。它的实现就是Object类中的toString实现。
     *  而test方法和Object类和Object没有关系，而是交由子类去实现。
     * to
     * @return
     */
    @Override
    String toString();
}
