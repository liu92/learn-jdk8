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
8、Function接口详解
```java
package com.learn.jdk.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * chapter6
 * Function接口详解
 * @ClassName: Demo6
 * @Description:
 * @Author: lin
 * @Date: 2019/12/24 22:32
 * History:
 * @<version> 1.0
 */
public class Demo6 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "hello world");
        // String::toUpperCase 这个 双冒号是方法引用
        // 通过一个String对象去调用 toUpperCase这个方法，这里String是个类，应该toUpperCase不是静态方法
        // 那么为什么通过方法引用就可以呢？ 这里要注意 对于这种通过 String 类等这种类型 然后 :: 跟着实例方法
        // 情况下 它的第一个输入参数 一定就是调用了这个toUpperCase方法的对象，
        // 换句话说就是在真正调用toUpperCase的时候 一定会存在一个字符串类型的对象，然后通过这个对象去调用toUpperCase方法
        //
        // Function 有两个参数，一个是输入参数，一个是返回参数。
        // 下面定义输入和输出都是string类型
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);
        // 结果interface java.util.function.Function
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
8.1 
```java
package com.learn.jdk.chapter6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * chapter6 function接口详解
 * @ClassName: StringComparator
 * @Description: 字符串比较
 * @Author: lin
 * @Date: 2019/12/26 23:32
 * History:
 * @<version> 1.0
 */
public class StringComparator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        // 第一种、对集合排序
        // Collections.sort(list, new Comparator<String>() {
        // @Override
        // public int compare(String o1, String o2) {
        //   // 倒序排序
        //   return o2.compareTo(o1);
        //  }
        //  });
        //第二种排序, 这种排序方式 比较冗余，
        // 2 它会提示 你使用Comparator.reverseOrder() 方法来进行替换
        Collections.sort(list, (String o1, String o2)-> {
            return o2.compareTo(o1);
        });
        // 2.1 上面的还可以修改为, 因为这list本身是字符串类型的
        // 所以可以省略 o1,o2 类型声明
        Collections.sort(list , (o1, o2)->{
           return o2.compareTo(o1);
        });
        //2.2 还可以修改为下面的
        // expression o2.compareTo(o1)
        // statement {return o2.compareTo(o1);} 这是一个完整的语句 必须有分号
        Collections.sort(list, (o1,o2)-> o2.compareTo(o1));

        // Comparator.reverseOrder() 方法来替换上面的排序,
        // 这一行代码就可以代替上面的排序，但是这种方法脱离了lambda表达
        //Collections.sort(list, Comparator.reverseOrder());
         System.out.println(list);
    }
}

```
 Tips: 
 java lambda 表达是一种匿名函数；他是没有声明的方法，既没有访问修饰符，返回值声明和名字。
 
 lambda表达式 作用
  ● 传递行为，而不仅仅是值
   ● 提升抽象层次
   ● API重用性更好
   ● 更加灵活
 
 Java Lambda结构
 ```
● 一个Lambda表达式可以有零个或者多个参数
● 参数的类型可以明确声明，也可以根据上下文推断。例如：(int a) 和 (a) 效果相同
● 所有参数需要包括在圆括号内,参数之间用逗号相隔。 例如：(a,b) 或 (int a, int b) 或
 (String a, int b, float c)
● 空圆括号代表参数集为空。 例如：()->42  参数为空返回42
● 当只有一个参数,且其类型可推导时，圆括号()可省略. 例如：a->return a*a
● Lambda表达式的主体可包含零条或多条语句
● 如果Lambda表达式的主体只有一条语句，花括号{} 可以省略. 匿名函数的返回类型与该主体表达式一致
● 如果Lambda表达的主体包含一条以上语句,则表达式必须包含在花括号{}中(形成代码块)。 匿名函数的返回类型与代码的返回类型一致，若没有返回则为空
```
8.2 
```java
package com.learn.jdk.chapter6;

import java.util.function.Function;

/**
 * function接口详解
 * @ClassName: FunctionDemo
 * @Description: function接口详解
 * @Author: lin
 * @Date: 2019/12/27 10:12
 * History:
 * @<version> 1.0
 */
public class FunctionDemo {
    public static void main(String[] args) {
       FunctionDemo functionDemo = new FunctionDemo();
        // 参数1 是传递值，而Function是传递方法
        // 将 va->{return  2 * va ;} 作为一个动作或者行为传递给了下面的compute方法
        // 这种传递方式 和以前定义好 方法不同，这种方法事先不知道行为，
        // 而以前的方法 比如method1，method2 方法 这种是已经定义好的 行为方式
        System.out.println(functionDemo.compute(1, va->{return  2 * va ;}));
        System.out.println(functionDemo.compute(2, va -> 5 + va));
        System.out.println(functionDemo.compute(3 ,va->va* va));

        System.out.println(functionDemo.convert(5, va ->String.valueOf(va+ "hello")));
    }



    private int compute(int a , Function<Integer, Integer> function){
        // 将a 做为参数传入 ,
        // apply 执行什么样的动作或者行为 提前是不知道的，
        // 而是通过 方法的传递来告知的
        int result = function.apply(a);
        return  result;
    }

    private String convert(int b, Function<Integer, String> function){
        return  function.apply(b);
    }

    private int method1(int a){
        return  2 * a;
    }

    private int method2(int a){
        return  5 + a;
    }
}
```

Tips:高阶函数
```
如果一个函数接收一个函数作为参数，或者返回一个函数作为返回值，那么该函数就叫高阶函数
```
8.3 Function中 函数组合
```
// compose 和将多个function串联在一起,
// 这个function函数式接口有一个输入, apply之后会得出一个返回结果，
// 然后这个输出结果又传递给了当前对象的apply方法. 这样做就形成了两个
// Function的串联，比如有function1 和function2, 那么把function2作为
// function1的 compose方法的参数传入进去,那么他的执行结果是先对输入参数
// 执行function2的apply, 
// 执行完之后 再将返回结果 再去作为当前的 funcion的输入参数
default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

// andThen 方法
// 首先应用的是当前的Function,然后对应用完当前的function所得到的结果,
default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
```
8.4 BiFunction :接收两个参数并且得到一个结果
```
 /**
  * Represents a function that accepts two arguments and produces a result.
  * This is the two-arity specialization of {@link Function}.
  *
  * <p>This is a <a href="package-summary.html">functional interface</a>
  * whose functional method is {@link #apply(Object, Object)}.
  *
  * @param <T> the type of the first argument to the function
  * @param <U> the type of the second argument to the function
  * @param <R> the type of the result of the function
  *
  * @see Function
  * @since 1.8
  */
 @FunctionalInterface
 public interface BiFunction<T, U, R>{}
```
 8.5 Function和BiFunction
 ```java
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

```
 8.6 BiFunction函数式接口实例
 ```java
package com.learn.jdk.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * BiFunction函数式接口实例演示
 * @ClassName: PersonDemo
 * @Description: BiFunction函数式接口实例演示
 * @Author: lin
 * @Date: 2019/12/27 17:07
 * History:
 * @<version> 1.0
 */
public class PersonDemo {
    public static void main(String[] args) {
        Person person1 = new Person("zairian", 20);
        Person person2 = new Person("lisa", 30);
        Person person3 = new Person("wangle", 40);
        List<Person> persons = Arrays.asList(person1, person2, person3);

        PersonDemo test = new PersonDemo();
//        List<Person> personResult = test.getPersonByUserName("lisa", persons);
//        personResult.forEach(person -> System.out.println(person.getUsername()));

        List<Person> personResult2 = test.getPersonByAge(20, persons);
        personResult2.forEach(person -> System.out.println(person.getAge()));

        List<Person> personResult3 = test.getPersonAge2(20 , persons, (age, personList)->
            personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList()));

        personResult3.forEach(person -> System.out.println(person.getAge()));

        System.out.println("===================================================");

        List<Person> personResult4 = test.getPersonAge2(20 , persons, (age, personList)->
                personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList()));

        personResult4.forEach(person -> System.out.println(person.getAge()));
    }

    /**
     * 根据参数 来判断符合条件的元素，然后返回集合
     * @param userName
     * @param persons
     * @return
     */
    public List<Person> getPersonByUserName(String userName, List<Person> persons){
        // 这行代码的意思就是 将persons转换成流,然后过滤流中每一个元素,
        // filter 也是返回stream 对象, 这是stream的中间操作,每次操作完成后又返回这个对象,
        // 最后想把stream转换成 集合 可通过collect(Collectors.toList())进行转换。
        // 每一个元素要符合这个判断条件, 最后把它转换成list进行返回
         return  persons.stream().filter(person -> person.getUsername().equals(userName)).
                 collect(Collectors.toList());
    }

    /**
     * 判断集合中大于传入的 age 参数。
     * @param age
     * @param persons
     * @return
     */
    public  List<Person> getPersonByAge(int age, List<Person> persons){
        // 首先定义了一个BiFunction 这样一个对象, 然后他有两个参数,
        // 对于这两个参数 直接使用stream(流)的方式 去找到里面的每一个对象,
        // 这个对象的年龄要大于 ageOfPerson, 然后.collect(Collectors.toList()) 转换成一个集合.
        // 如果是statement {return o2.compareTo(o1);} 这是一个完整的语句, 那么必须有return
        // 下面可以替换 expression lambda , 也就是去掉花括号 {} 和return
        BiFunction<Integer, List<Person>, List<Person>> biFunction =
//                (ageOfPerson, personList)->{
//           return   personList.stream().filter(person -> person.getAge()> ageOfPerson).
//                    collect(Collectors.toList());
//        };
        (ageOfPerson, personList)-> personList.stream().filter(person -> person.getAge()> ageOfPerson).
                    collect(Collectors.toList());


        // 上面只是定义好了BiFunction, 这里才是应用它
        return  biFunction.apply(age, persons);
    }


    public List<Person> getPersonAge2(int age,List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return  biFunction.apply(age, persons);
    }
}

```
9、 predicate 函数式接口
```java
package com.learn.jdk.chapter9;

import java.util.function.Predicate;

/**
 * chapter predicate
 * @ClassName: PredicateDemo
 * @Description: predicate 函数式接口
 * @Author: lin
 * @Date: 2019/12/29 21:53
 * History:
 * @<version> 1.0
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = p ->p.length() > 6;
        // test方法的实现是 p ->p.length() > 6;
        System.out.println(predicate.test("hello11"));
    }
}

```
9.1 predicate 深入剖析和 函数式编程本质
```java

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


```
9.2 其他的默认方法
``` 
1、表示的逻辑与，也就是当前的判断为假，后面的判断就不去计算了
/**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
2、表示的逻辑非，也就是取反
    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
3、表示的逻辑或者的关系
    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

```
10. supplier 与函数式接口总结
```java
package com.learn.jdk.chapter11;

import java.util.function.Supplier;

/**
 * 通过使用supplier来生成 student的实例
 * @ClassName: StudentDemo
 * @Description: 通过使用supplier来生成 student的实例
 * @Author: lin
 * @Date: 2019/12/30 9:19
 * History:
 * @<version> 1.0
 */
public class StudentDemo {
    public static void main(String[] args) {
        //第一种
       Supplier<Student> supplier = () -> new Student();
       System.out.println(supplier.get().getName());
       System.out.println("-------------------");
       //第二种：方法引用,            构造方法引用，将鼠标放在new关键字上，就可以看到其实调用的是构造方法
        // Supplier 接口中定义，不接受参数 ，返回一个对象。所以调用构造方法符合这个定义
        // 如果 student类中 有多个构造方法，编译器会自动去找 不传入参数的构造方法。
        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getName());
    }
}
```
10.1
```java

/**
 * Chapter11 supplier
 * @ClassName: SupplierDemo
 * @Description: supplier 与函数式接口总结
 * @Author: lin
 * @Date: 2019/12/30 9:12
 * History:
 * @<version> 1.0
 */
public class SupplierDemo {
    public static void main(String[] args) {
        // 不接受参数，然后返回结果
        Supplier<String> supplier = () -> "hello world";
        //得到一个结果
        System.out.println(supplier.get());
    }
}
```
11、optional学习
```java
package com.learn.jdk.chapter12;

import java.util.Optional;

/**
 * chapter 12
 * @ClassName: OptionalDemo
 * @Description: optional 使用
 * @Author: lin
 * @Date: 2019/12/30 14:31
 * History:
 * @<version> 1.0
 */
public class OptionalDemo {
    public static void main(String[] args) {
        //下面的是传统的面向对象方式
        // of方法表示传入的参数不能为空
        Optional<String> optional = Optional.of("hhh");
        // 判断是否存在，如果存在则打印
        // 这种方式不是函数式 编程方式
//        if(optional.isPresent()){
//            System.out.println(optional.get());
//        }

        // 这里使用函数式的风格来重写
        // 推荐的Optional使用方式
        optional.ifPresent(item -> System.out.println(item));

        System.out.println("----------------------");
        // 如果optional 中不存在，则打印 下面的world
        System.out.println(optional.orElse("world"));
        System.out.println("---------");
        // 不接收参数
        // 如果optional 没有值，则调用supplier函数式接口 获取一个值打印出来。
        // 如果optional里面的值存在， 那么就不打印
        System.out.println(optional.orElseGet(() -> "li"));

    }
}

```
11.1 optional 深入详解
```java
package com.learn.jdk.chapter12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * chapter12
 * @ClassName: OptionalDemo2
 * @Description: optional 使用
 * @Author: lin
 * @Date: 2019/12/30 15:23
 * History:
 * @<version> 1.0
 */
public class OptionalDemo2 {
    public static void main(String[] args) {
      Employee employee = new Employee();
      employee.setName("lisa");

      Employee employee1 = new Employee();
      employee1.setName("zairian");

      Company company = new Company();
      company.setName("company1");
      List<Employee> employees  = Arrays.asList(employee, employee1);
      company.setEmployees( employees);
      // 如果company中不包含 任何employee信息, 那么就返回 一个空的集合
      // 如果存在就返回一个列表
      List<Employee> list = company.getEmployees();

      Optional<Company> optionals = Optional.ofNullable(company);
      //
      System.out.println(optionals.map(Company::getEmployees).
                orElse(Collections.emptyList()));
    }


    //Tips: 不要将Optional试图作为方法参数，也不要在类中定义Optional成员变量，
    // Optional 通常只作为方法的返回值，用来规避空指针异常这样一个问题
    // 编写Optional 一定要使用函数式的方式来编写
}


```

12、方法引用(method reference)
方法引用实际上是Lambda表达式的一种语法糖
```
/**
可以将方法引用看作是一个【函数指针】，function pointer
方法引用共分为4类:
1. 类名::静态方法名（注意不能传入参数，编译器会自动去识别）
2. 引用名(对象名，其实就是对象的一个引用)::实例方法名
3. 类名::实例方法名
4. 构造方法引用: 类名::new (实际上就是调用这个类的构造方法来生成对象)
*/

```
12. 默认方法
1、类方法实现接口，并且接口中有相同的方法
```java
package com.learn.jdk.chapter13.defaultmethod;

/**
 * @ClassName: MyClass
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 11:10
 * History:
 * @<version> 1.0
 */
public class MyClass implements MyInterface1 ,MyInterface2 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        // 如果该类实现了 MyInterface1和MyInterface2,
        // 并且两个接口有相同的方法，这种情况下会发生什么呢？
        // 注意: 在这种情况下 实现接口的类会去重写这个 默认方法。
        //  因为myclass 有一个defaultMethod实现，但是不知取那个，
        //  编译器是无从得知，所以我们显示的告诉编译， defaultMethod使用的哪一个
        myClass.defaultMethod();
    }

    @Override
    public void defaultMethod() {
        // 如果要使用MyInterface2中的方法那么
        // 使用这种方式,这样就可以使用MyInterface2中的方法了
        MyInterface2.super.defaultMethod();
//        System.out.println("hhhhh");
    }
}
```
12.2 默认方法:一个类继承实现类，并实现接口

```java

package com.learn.jdk.chapter13.defaultmethod;

import com.learn.jdk.chapter13.defaultmethod.impl.MyInterface1Impl;

/**
 * 继承实现类，实现接口
 * @ClassName: MyClass2
 * @Description:
 * @Author: lin
 * @Date: 2019/12/31 13:41
 * History:
 * @<version> 1.0
 */
public class MyClass2 extends MyInterface1Impl implements MyInterface2{
    public static void main(String[] args) {
        // 如果一个类继承了一个类和实现了一个接口,
        // 而继承的这个类重写了接口的默认的方法, 那么MyClass2就会使用
        // 重写了方法的 方法，而不会使用MyInterface2接口中默认方法
        MyClass2 myClass2 = new MyClass2();
        myClass2.defaultMethod();
        // 这里有一个java的设定: 实现类优先级要比接口的优先级高,
        // 这是因为实现类更为具体
    }
}
```
12.3 为什么接口可以有默认方法的实现? 这样设计的意义在于什么地方?
```
1. 在新的特性要保证向后兼容, 保证既有接口中的方法不被破坏。
比如: 我们有一个类实现一个接口，而这个类要实现接口中相应方法，
如果在接口中有默认方法实现,那么实现接口的时候,
这个实现类就继承了默认方法。并这个类什么都不用做
```

13. stream 流
```
流由3部分构成:
1.源
2.零个或者多个中间操作。(操作的就是这个源)
3.终止操作

流操作的分类:
1.惰性求值
  stream.xx().yyy().count(), 中间的xx().yyy()操作就是惰性操作,
  只有真正去调用count()计算是,中间操作才会被执行。
  如果没有count(),那么stream.xx().yyy() 就不会被执行,因为这里面没有终止操作
2.及早求值
  就是stream.xx().yyy().count()中的count() 立马要得到值

.Collection提供了新的stream()方法
.流不存储值，通过管道的方式获取值
.本质函数式的,对流的操作会生成一个结果，
 不过并不会修改底层的数据源,集合可以作为流的底层数据源
.延迟查找，很多流操作(过滤，映射，排序等)都可以延迟实现
```