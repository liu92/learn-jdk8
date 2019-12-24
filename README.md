# learn-jdk8
1、lambda 表达式的基本结构：

 （param1,param2,param3）->{

}

如下基本示例

```java
package com.learn.jdk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName: SwingTest
 * @Description:
 * @Author: lin
 * @Date: 2019/12/2 21:20
 * History:
 * @<version> 1.0
 */
public class SwingTest {
    public static void main(String[] args) {
        JFrame jframe = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");
        // 给button增加一个事件处理器，就是点击一个按钮的时候执行特定的代码
        // 在jdk1.8 之前会这样使用匿名类，
        // 其实这里我们只需要 打印语句其他的都不需要去管。
//        jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button ");
//            }
//        });
        // 而使用jdk1.8的lambda表达式 的写法很简洁
        // 左边是参数，中间 ->是箭头符号，右边是lambda表达式的执行体,
        jButton.addActionListener(e -> System.out.println("Button Hello"));
//        jButton.addActionListener(e -> {
//            System.out.println("Button Hello");
//            System.out.println("Button Hello");
//            System.out.println("Button Hello");
//        });
        // 在没有定义参数类型时 也可以执行是因为 java编译系统 有称为类型推断一种机制，
        // 他就是能推断 event类型一定是ActionEvent，不过一般不去定义因为写出来感觉有点多余
        // 注意：java编译系统不是都能推断处理类型，有些时候根据上下文它是推断不出来的，这个时候需要你显示的去定义
        
        jButton.addActionListener((ActionEvent event) -> System.out.println("Button Hello"));
        // 将按钮添加到JFrame上
        jframe.add(jButton);
        jframe.pack();
        // 事件的可行性
        jframe.setVisible(true);
        // 默认的 ，当关闭的时候整个程序退出
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

```



不过不要局限于这个结构还有其他很多的结构。

2、遍历集合



```java
package com.learn.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * jdk1.8 遍历集合
 * @ClassName: ListTest
 * @Description:
 * @Author: lin
 * @Date: 2019/12/2 22:20
 * History:
 * @<version> 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 2, 3 , 4, 5, 6, 7, 8);
        // 以前的写法
        // 第一种
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
        System.out.println("---------------------");
        // 第二种
        for (Integer list : lists) {
            System.out.println(list);
        }

        System.out.println("---------------------");

        // 第三种, 这里
        lists.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("---------------------");
    }

}

```

第三种方式是jdk1.8 中的写法，这个方法中 使用了匿名类的方式来编写,  lists.forEach 会针对每一个元素去调用对应的Consumer中的 accept方法，然后将每一个被迭代的元素打印出来。

那么这个Consumer接口又是怎么定义的呢？ 这个接口上有注解是 @FunctionalInterface 函数式接口。

```java
/**
@FunctionalInterface
public interface Consumer<T>
*/
```

凡是一个接口它上面加了@FunctionalInterface 注解，我们称这个接口是函数式接口。

```java
// 凡是一个接口它上面加了@FunctionalInterface 注解，我们称这个接口是函数式接口。
 /* An informative annotation type used to indicate that an interface
 * type declaration is intended to be a <i>functional interface</i> as
 * defined by the Java Language Specification.
    
* Conceptually, a functional interface has exactly one abstract method    
    // 他有个一默认的实现，所以他不是抽象的 
* Since {@linkplain java.lang.reflect.Method#isDefault()
 * default methods} have an implementation, they are not abstract. If
    // 如果一个接口声明了一个抽象的方法，并且这个抽象方法覆盖了java.lang.Object中的public方法
 * an interface declares an abstract method overriding one of the
    // 那么这种也不能称为一个函数式接口，因为接口的任何实 都会有来自Object类 以及其他的实现。
 * public methods of {@code java.lang.Object}, that also does
 * <em>not</em> count toward the interface's abstract method count
 * since any implementation of the interface will have an
 * implementation from {@code java.lang.Object} or elsewhere.  
    //函数式的接口实例可以通过 lambda表达式来创建，或者方法引用来创建，或者构造方法引用来创建
 * <p>Note that instances of functional interfaces can be created with
 * lambda expressions, method references, or constructor references.   
 * // 如果某个类型被加上了 FunctionalInterface 注解的话，编译器就会被强制生成一个错误信息
 * <p>If a type is annotated with this annotation type, compilers are
 * required to generate an error message unless:
 *  // 什么错误信息呢？如果不能满足下面两个条件，编译器就会生成错误信息
 * <ul> 
      // 如果类型 但是不是一个注解类型、枚举或者类 那编译器就会报错
 * <li> The type is an interface type and not an annotation type, enum, or class.
     // 被注解的类型满足了函数式接口的这种要求
 * <li> The annotated type satisfies the requirements of a functional interface.
 * </ul>   
     // 然而 ,编译器会对应满足函数式接口定义的任意的接口都会把它当做一个函数式接口，而不管是不是咋这个接口上加上FunctionalInterface注解
  * <p>However, the compiler will treat any interface meeting the
 * definition of a functional interface as a functional interface
 * regardless of whether or not a {@code FunctionalInterface}
 * annotation is present on the interface declaration.    
    
 public @interface FunctionalInterface {}    
*/
```

那么到底什么是函数接口 ，如下注释说明，

**从概念上讲，一个接口它只有一个抽象方法** .

```java
/* Conceptually, a functional interface has exactly one abstract method
*/    
```

一个接口只有一个抽象方法？在java8中 接口不仅可以包含抽象方法，还可以包含有具体实现的方法，这种方法称为默认方法。

所以 这里我们对函数式接口就有个基本的概念，一个接口里面只有唯一的一个抽象方法。那么这个就是一个抽象接口。 	



关于函数式接口：

1)、一个接口它只有一个抽象方法，那么该接口是一个函数式接口

2)、如果我们在某个接口上声明一个FunctionalInterface注解，那么编译器就会安装函数式接口的方式来要求改接口

3)、如果某个接口只有一个抽象方法，但是我们并没有给这个接口声明FunctionalInterface注解，那么编译器依旧会将该接口看作是函数式接口。


3、函数式接口 的使用
```java
package com.learn.jdk;

/**
 * @ClassName: MyInterfaceDemo
 * @Description:
 * @Author: lin
 * @Date: 2019/12/3 22:48
 * @History:
 * @<version> 1.0
 */
@FunctionalInterface
 interface MyInterfaceDemo {
    /**
     * te
     */
    void  test();

    /**
     * to
     * @return
     */
    @Override
    String toString();
}

/**
 * test2类
 * @return
 * @exception
 * @author lin
 * @Date 22:52 2019/12/3
 **/
public class Test2{
    public  void  myTest(MyInterfaceDemo myInterfaceDemo){
       System.out.println(1);
       myInterfaceDemo.test();
       System.out.println(2);
    }

   public static void main(String[] args) {
      Test2 t = new Test2();
      // 函数式编程，以前要写匿名函数就是下面 的写法,
      // 所以使用函数式编程方式来编写匿名类就比较方便
//      t.myTest(new MyInterfaceDemo() {
//         @Override
//         public void test() {
//            System.out.println("mytest");
//         }
//      });

      // 这里使用() 是因为函数式接口中的方法是没有参数的。
      t.myTest(()->{
         System.out.println("mytest");
      });

      MyInterfaceDemo demo = ()->{
         System.out.println("hello");
      };
      System.out.println("MyInterfaceDemo的类型==="+demo.getClass());
      System.out.println("父类型===="+demo.getClass().getSuperclass());
      System.out.println("实现了那些接口========="+demo.getClass().getInterfaces()[0]);
   }
}
```
4、接口中默认方法

接口中默认方法实现一方面保证了jdk8新特性的加入比如lambda表达式，函数式接口，另一方面由保证了和老版本的兼容性。
```java
// Iterble接口类中 默认方法实现 
 /**   针对于Iterble的每一个元素，去执行给定的动作，是一种动作，把行为传递给了方法，而不是把数据作为参数传递给方法
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     // 如果没有被这个实现类所指定的话，那么动作就会按照迭代的顺序来去执行。
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     // 是否抛出异常取决于调用者
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @implSpec
     // 这是默认的行为
     * <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
  /**default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
*/
```
5、Lambda的作用：

  ● Lambda表达式为Java添加了缺失的函数式编程特性，使我们能将函数当做一等公民看待。

  ● 在将函数作为一等公民的语言中，Lambda表达式的类型是函数。<font color=#FF0000 >但是在java中，Lambad表达式是对象</font> ，他们必须依附于一类特别的对象类型----函数式接口（Functional Interface）

为什么在java中 Lambda表示是对象呢？ 

     # 如下面的示例 我们将lambda表达式 赋一个对象引用。 并且这个lambda的类型是demo.getClass(), 父类型是demo.getClass().getSuperclass(),实现的接口是 MyInterfaceDemo. 所以这一点 和其他函数式编程语言的非常大的区别。
         
      MyInterfaceDemo demo = ()->{
         System.out.println("hello");
      };
      System.out.println("MyInterfaceDemo的类型==="+demo.getClass());
      System.out.println("父类型===="+demo.getClass().getSuperclass());
      System.out.println("实现了那些接口========="+demo.getClass().getInterfaces()[0]);

6.1外部迭代
```
# 使用迭代器 移动位置来进行迭代
List<Integer> lists = Arrays.asList(1, 2, 3 , 4, 5, 6, 7, 8);
for(int li:lists){
  System.out.println(li);
}
```
6.2 内部迭代

```
# 内部迭代 完全是通过lambda表达式将 元素取出来进行迭代
lists.forEach(new Consumer<Integer>() {    
 @Override    
 public void accept(Integer integer) {        
  System.out.println(integer);    
 }
});
```
6.3 通过方法引用方式
 ```
// 通过方法引用来 创建函数接口的实例
 // method reference
 lists.forEach(System.out::println);
```
7、lambda表达式 深入与流初步
```java
package com.learn.jdk.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * chapter5
 * lambda表达式 深入与流初步
 * @ClassName: Demo5
 * @Description:
 * @Author: lin
 * @Date: 2019/12/24 22:32
 * History:
 * @<version> 1.0
 */
public class Demo5 {
    public static void main(String[] args) {
         // 如果没有 MyInterfaceDemo1 这些上下文，那么编译器无法去推断类型
         // 并且MyInterfaceDemo1 函数式接口中的方法，不接受参数，没有返回值  () -> {}.
//         MyInterfaceDemo1 interfaceDemo1 = () -> {};
//         System.out.println(interfaceDemo1.getClass().getInterfaces()[0]);
//
//         MyInterfaceDemo2 myInterfaceDemo2 = () -> {};
//         System.out.println(myInterfaceDemo2.getClass().getInterfaces()[0]);

         // 变量集合的中元素，然后转换成大写 输出，这里使用lambda表达式来处理
         List<String> list = Arrays.asList("hello", "world", "hello world");
         // 1、将结合中元素 转换成大写 输出
//         list.forEach(item -> System.out.println(item.toUpperCase()));
         // 2、构造一个新的集合然后 将转换之后的这些元素 放到新的的集合中去
         //List<String> list2 = new ArrayList<>();
         // 第一种写法
         //list.forEach(item -> list2.add(item.toUpperCase()));
         //list2.forEach(item -> System.out.println(item));
         // 第二种写法 ，使用流(stream)的方式来编写。 stream() 是串行 也就是单线程，
         // parallelStream()是并行流，也就是流是多线程来操作的。
         // map() ,中function就是一个函数，给定一个输入然后得到一个输出
         // 结束，list 把它转换成一个流，这个流调用map方法，然后将流 它中的源(就是这里的集合 list)
         // 遍历集合中每一个元素 然后做一个映射 将其转换成大写， 转换成大写之后还是返回stream.
         // 所以调用foreach方法 把每个元素打印出来
         list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        // 第三 通过方法引用方式来写, 给定一个输入参数，得到一个返回结果
        // 这里的输入是指调用了toUpperCase 这个方法的“对象”作为输入参数。
        list.stream().map(String::toUpperCase).forEach(item-> System.out.println(item));
    }
}

/**
 * 函数式接口
 */
@FunctionalInterface
interface MyInterfaceDemo1{
    /**
     * 抽象方法
     * method
     */
    void method();
}

/**
 *
 */
@FunctionalInterface
interface MyInterfaceDemo2{
    /**
     * 抽象方法
     * method2
     */
    void method2();
}
```
