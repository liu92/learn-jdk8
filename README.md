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
12.4 方法引用的使用
```java
package com.learn.jdk.chapter13;

import java.util.Arrays;
import java.util.List;

/**
 * chapter13 方法引用
 * @ClassName: MethodReferenceDemo
 * @Description: 方法引用的使用
 * @Author: lin
 * @Date: 2019/12/30 16:09
 * History:
 * @<version> 1.0
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        // 使用一般lambda表达式
        //list.forEach(item -> System.out.println(item));
        // 可以将方法引用看作是一个【函数指针】，function pointer
        // 也就是说指向一个函数的指针
        list.forEach(System.out::println);
    }
}

```
12.5 方法引用4中使用方式
```java
package com.learn.jdk.chapter13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @ClassName: MethodReferenceDemo2
 * @Description: 方法引用
 * @Author: lin
 * @Date: 2019/12/30 16:37
 * History:
 * @<version> 1.0
 */
public class MethodReferenceDemo2 {
    public static void main(String[] args) {
        /**
         * 第一种、类名::静态方法名
         */
        StudentDe student1 = new StudentDe("zairian", 20);
        StudentDe student2 = new StudentDe("lisp", 80);
        StudentDe student3 = new StudentDe("wangle", 50);
        StudentDe student4 = new StudentDe("zambia", 40);

        List<StudentDe> studentDes = Arrays.asList(student1, student2, student3, student4);

        // 这种方式是使用传统的lambda 方式来进行排序
        // 本质上就是接受两个参数，然后它调用了一个方法，而这个方法本身是客观存在的，
        // 所以下面方法引用通过类名来进行调用
//        studentDes.sort((studentParam1, studentParam2) ->
//                StudentDe.compareStudent1ByScore(studentParam1, studentParam2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用传统的lambda方式进行排序==="+studentDe.getScore()));

        System.out.println("----------------");
        // 使用方法引用的方式进行调用
        // 方法引用使用的要求，你使用的lambda表达式的这个实现也就是方法体
        // 它恰好有一个对应的方法跟他完成相同功能的 这么一个对应方法是客观存在的，
        // 那么这个时候就可以将lambda 替换为方法引用
//        studentDes.sort(StudentDe::compareStudent1ByScore);
//        studentDes.forEach(studentDe ->
//                System.out.println("使用方法引用的方式进行排序==="+studentDe.getScore()));

        // 根据字母排序
//        studentDes.sort((studentParam1, studentParam2) ->
//                StudentDe.compareStudent1ByName(studentParam1, studentParam2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用传统的lambda方式进行字母排序==="+studentDe.getName()));
//        studentDes.sort(StudentDe::compareStudent1ByName);
//        studentDes.forEach(studentDe ->
//        System.out.println("使用方法引用的方式根据字母排序==="+studentDe.getName()));


        /**
         * 第二种方式: 引用名(对象名，其实就是对象的一个引用)::实例方法名
         */
        // 先使用传统lambda方式
        StudentComparator studentComparator = new StudentComparator();
//        studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByScore(st1, st2));
//        studentDes.forEach(studentDe ->
//        System.out.println("使用lambda方式排序"+studentDe.getScore()));
        // 使用方法引用方式

//        studentDes.sort(studentComparator::compareStudent1ByScore);
//        studentDes.forEach(studentDe ->
//               System.out.println("使用方法引用方式排序"+studentDe.getScore()));

        // 根据字母排序
//        studentDes.sort((st1, st2) -> studentComparator.compareStudent1ByName(st1, st2));
//        studentDes.forEach(studentDe ->
//               System.out.println("使用lambda方式排序"+studentDe.getName()));

//        studentDes.sort(studentComparator::compareStudent1ByName);
//        studentDes.forEach(studentDe ->
//                System.out.println("使用方法引用方式排序" + studentDe.getName()));

         // 第三种: 类名::实例方法名
         // 这里和上面两种的区别就是，上面的调用都很明确
         // 而下面这种就不是那么明确, 实例方法一定是对象来调用的,
         // 但是这里使用的是类名来调用实例方法。这是不可能直接调用实例方法的。
         // 那么这个实例方法一定是有那么一个对象来进行调用。
         // 那么这个对象就是sort 方法里面接收的 lambda表达式的"第一个参数" 来去调用的compareByScore
         // 如果接收多个参数 除了第一个参数之外，后续所有参数都做为这个方法的参数 传递进去
         // 就是类似 这样 (st1, st2)-> st1.compareByScore( st2),
         // 第一个参数 st1, 第二个参数st2 作为方法传进去的参数
//         studentDes.sort(StudentDe::compareByScore);
//         studentDes.forEach(studentDe ->
//                System.out.println("第三种使用类名::实例方法引用方式进行分数排序=="
//                        + studentDe.getScore()));

//         studentDes.sort(StudentDe::compareByName);
//         studentDes.forEach(studentDe ->
//                System.out.println("第三种使用类名::实例方法引用方式进行名字排序=="
//                        + studentDe.getName()));

         //例子
//         List<String> cis = Arrays.asList("sichuang","beijing","anhui","guangdong");
//         Collections.sort(cis, (cis1, cis2)-> cis1.compareToIgnoreCase(cis2));
//         cis.forEach(ci -> System.out.println(ci));
         // 这两种形式就是等价的
//         Collections.sort(cis, String::compareToIgnoreCase);
//         cis.forEach(System.out::println);


         MethodReferenceDemo2 methodDemo2 = new MethodReferenceDemo2();
         System.out.println(methodDemo2.getString(String::new));

         System.out.println(methodDemo2.getString2("hello", String::new));
    }

    public String getString(Supplier<String> supplier){
       return supplier.get() + "test";
    }

    public String getString2(String str, Function<String, String> function){
        return function.apply(str);
    }
}

```
12.6 方法引用中定义的比较方法
```java
package com.learn.jdk.chapter13;

/**
 * 方法引用2： 引用名(对象名，其实就是对象的一个引用)::实例方法名
 * 将方法放在一个新的类中，并且方法不是静态方法，是实例方法
 * @ClassName: StudentComparator
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 17:28
 * History:
 * @<version> 1.0
 */
public class StudentComparator {
    /**
     * 根据分数进行比较
     * @param student1
     * @param student2
     * @return
     */
    public  int compareStudent1ByScore(StudentDe student1, StudentDe student2){
        return  student1.getScore() - student2.getScore();
    }

    /**
     * 根据字母进行比较
     * @param student1
     * @param student2
     * @return
     */
    public  int compareStudent1ByName(StudentDe student1, StudentDe student2){
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}

```
12.7 方法引用中使用的实体
```java
package com.learn.jdk.chapter13;

import com.learn.jdk.chapter11.Student;

/**
 *
 * @ClassName: Student
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 9:17
 * History:
 * @<version> 1.0
 */
public class StudentDe {
    private String name;
    private int score;

    public StudentDe(){}

    public StudentDe(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 下面的这个两个方法设计是有意而为之的，
     * 因为这两个方法在调用时和这类没有任何关系，
     * 也就是说将这两个方法发到其他的类中也是完全可以运行的 使用的。
     * 下面的可以编译运行，但是从设计上来说是错误的。
     * 根据分数排序
     * 对集合中的若干个学生进行排序，所以是两两比较
     * @param student1
     * @param student2
     * @return
     */
    public static int compareStudent1ByScore(StudentDe student1, StudentDe student2){
        return  student1.getScore() - student2.getScore();
    }

    /**
     * 根据名字排序
     * @param student1
     * @param student2
     * @return
     */
    public static int compareStudent1ByName(StudentDe student1, StudentDe student2){
        return student1.getName().compareToIgnoreCase(student2.getName());
    }


    /**
     * 这才是正确的设计
     * 当前的对象和 传入的对象比较，
     *
     * @param student
     * @return
     */
    public int compareByScore(StudentDe student){
        return  this.getScore() - student.getScore();
    }


    public int compareByName(StudentDe student){
        return  this.getName().compareToIgnoreCase(student.getName());
    }
}

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
13.1 stream 简单使用
```java

package com.learn.jdk.chapter15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * chapter15
 * @ClassName: StreamDemo
 * @Description: stream 操作方式示例
 * @Author: lin
 * @Date: 2019/12/31 15:12
 * History:
 * @<version> 1.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        // 创建stream 对象
        Stream stream = Stream.of("hello", "world");
        String[] str = new String[]{"hello", "li"};
        Stream stream1 = Stream.of(str);
        Stream stream2 = Arrays.stream(str);
        
        // 通过list创建
        List<String> list = Arrays.asList(str);
        Stream<String> stream3 = list.stream();
    }
}

```
13.2 Stream 使用1
```java
package com.learn.jdk.chapter15;

import java.util.stream.IntStream;

/**
 * @ClassName: StreamDemo1
 * @Description: IntStream 操作方式示例
 * @Author: lin
 * @Date: 2019/12/31 15:21
 * History:
 * @<version> 1.0
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 5, 7, 9};
        IntStream.of(ints).forEach(System.out::println);
        System.out.println("-----------------");
        // 在3,8 范围内的包含3,不包含8
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("-----------------");
        // 包含3和8
        IntStream.rangeClosed(3, 8).forEach(System.out::println);
    }

}

```
13.3 IntStream使用
```java
package com.learn.jdk.chapter15;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo2
 * @Description: IntStream 应用
 * @Author: lin
 * @Date: 2019/12/31 15:35
 * History:
 * @<version> 1.0
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        //示例:将整型list中元素都乘以2，然后计算乘以2以后集合中元素和
        List<Integer> list = Arrays.asList(2, 4, 3, 5, 7);
//        int sum = 0;
//        int temp = 0;
//        // 原来的for循环方法处理
//        for (Integer integer : list) {
//             sum += integer*2;
//        }
//        System.out.println("集合元素相加===="+sum);

        // 现在使用stream来进行元素求和.
        // 这里我们就可以知道流的构成
        // list 就是 源
        // map(itm -> itm * 2): 中间操作
        // reduce() 就是终止操作
        // reduce(0, Integer::sum) 这个reduce代码的意思就是0 + itm*2,
        System.out.println("元素求和==="+list.stream().map(itm -> itm * 2).
                reduce(0, Integer::sum));
    }
}

```
13.4 Stream 的深度解析和源码实践
```java
package com.learn.jdk.chapter15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * chapter16
 * @ClassName: StreamDemo4
 * @Description: Stream 的深度解析和源码实践
 * @Author: lin
 * @Date: 2019/12/31 17:00
 * History:
 * @<version> 1.0
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        //1
//        Stream<String> stream = Stream.of("hello", "world", "lin");
        //将一个stream对象 转换成 字符串数组
        //String[] stringArray1 = stream.toArray(itm -> new String[itm]);
        //方法引用, 这个正好符合IntFunction<A[]> 的声明
        //
//        String[] stringArray = stream.toArray(String[]::new);
//        Arrays.asList(stringArray).forEach(System.out::println);

        //2:将这个流转换成list
        Stream<String> stream1 = Stream.of("hello", "world", "lin");
//        List<String> collect = stream1.collect(Collectors.toList());
        // 使用collect的下面方法进行处理,
        // 第一个参数:不接受参数返回一个结果,
        // 第二个参数:BiConsumer接收两个参数，accumulator是累加器
        // 就是对集合中每一个元素进行遍历, 然后把这个集合的每一个元素应用到这个结果上面
        // 将每一个元素添加到这个返回的集合中， 其中第一个参数theList就是要返回的集合，
        //  第二个item是遍历的stream的当中的元素，将每一个元素都添加到这个theList当中
        // 第三个参数:也是BiConsumer, 不过这个combiner是合并器，
        //  也就是将上一次的操作结果和下一次的操作结果合并在一起
        // theList1.addAll(theList2) 就是讲上一次遍历得到的每一个list都添加到最终的theList1中去,
        // 最后返回的就theList1, 这个theList1就是最终要返回的对象,实际上就是ArrayList这个对象
        //<R> R collect(Supplier<R> supplier,
        //                  BiConsumer<R, ? super T> accumulator,
        //                  BiConsumer<R, R> combiner);
//        List<String> collect = stream1.collect(()-> new ArrayList<>(), (theList, item) ->
//                theList.add(item), (theList1, theList2) -> theList1.addAll(theList2));

        //使用方法引用来实现
        List<String> collect = stream1.collect(LinkedList::new,
                LinkedList::add, LinkedList::addAll);
        collect.forEach(System.out::println);

    }
}

```
13.5 Stream 实例剖析1
```java
package com.learn.jdk.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * chapter17
 * @ClassName: StreamDemo5
 * @Description: Stream 实例剖析
 * @Author: lin
 * @Date: 2020/1/2 8:45
 * History:
 * @<version> 1.0
 */
public class StreamDemo5 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "lin");
        // 这里使用Collectors.toCollection(Supplier<C> collectionFactory)
        // 这里可以自定义转换成相应的集合
//        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
//        list.forEach(System.out::println);

        // 转换成TreeSet
//        Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set.getClass());
//        set.forEach(System.out::println);

        //3.将stream中字符串，拼接成一个字符输出出来,
        // Collectors.joining() 一个接着一个拼接
        String str = stream.collect(Collectors.joining()).toString();
        System.out.println("拼接字符串======"+str);
    }
}

```
13.6 Stream 实例剖析2
```java
package com.learn.jdk.chapter17;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo6
 * @Description: Stream 实例剖析
 * @Author: lin
 * @Date: 2020/1/2 9:13
 * History:
 * @<version> 1.0
 */
public class StreamDemo6 {
    public static void main(String[] args) {
        // 将集合中元素转换成大写
//        List<String> list = Arrays.asList("hello", "world", "lin", "stream");
//        System.out.println(list.stream()
//                .map(String::toUpperCase)
//                .collect(Collectors.toList()));
//        System.out.println("-------------");

        // 2.每个数字的平方，然后在打印出来
//        List<Integer> list1 = Arrays.asList(2, 3, 4, 6, 7);
//        System.out.println("每个数的平方========="+list1.stream().map(item -> item * item)
//                .collect(Collectors.toList()));

        System.out.println("--------------------");

        //3.使用flatMap, 将下面的集合乘方 之后，作为整体输入出来 ，
        // 也就是形成一个list
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3),
                 Arrays.asList(4, 5, 6));
        stream.flatMap(Collection::stream)
                .map(item -> item * item).forEach(System.out::println);


    }
}

```
13.7  Stream.generate() 方法使用，
Stream.iterate() 使用,如果不加限制那么就会产生无限流
```java
package com.learn.jdk.chapter17;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo7
 * @Description: 1. Stream.generate() 方法使用
 * @Author: lin
 * @Date: 2020/1/2 10:02
 * History:
 * @<version> 1.0
 */
public class StreamDemo7 {
    public static void main(String[] args) {
        //1. Stream.generate() 方法使用
//        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        // stream.findFirst() 返回流里面的第一个元素。
        // 为什么返回Optional，是为了避免NPE(空指针)
        // System.out.println(stream.findFirst().get());
//        stream.findFirst().ifPresent(System.out::println);

        // 2. Stream.iterate() 使用,如果不加限制那么就会产生无限流
        // 加上面limit就表示限制产生6个
        // 结果 1,3,5,7,9, 11
//        Stream.iterate(1, item -> item + 2)
//                .limit(6).forEach(System.out::println);
        // 找出该流中大于2的元素，然后将每个元素乘以2, 然后忽略掉流中的前两个元素,
        // 然后再取出流中的前两个元素，最后求出流中元素的总和。
//        System.out.println("求和==========" + Stream.iterate(1, item -> item + 2).limit(6)
//                .filter(ite -> ite > 2)
//                .map(ite -> ite * 2)
//                .skip(2)
//                .limit(2).reduce(0, Integer::sum));
        //第二种方式 使用mapToInt(), 将其转换为int, 如果使用map()那么里面会有一个自动拆箱和装箱的操作，
        // 这个转换会有一点点的性能损耗，所以jdk它会去避免这些损耗，因此提供了针对特定的原生类型 mapToInt()
        // 所以这里使用mapToInt()

        Stream<Integer> stream1 = Stream.iterate(1, item -> item + 2).limit(6);
//        System.out.println(stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).sum());

        // 这种方法是推荐使用的写法
        // 找出流中最小的元素
        // min() 方法返回的是Optional,因为这样更好的去规避空指针，
        // 而sum()返回的是int,就算stream中没有元素，他也只是返回 0.
        // 所以这就是这两种的区别
//         stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).min().ifPresent(System.out::println);
         
         //3.使用summaryStatistics()方法
//         IntSummaryStatistics intSummaryStatistics = stream1.filter(item -> item > 2)
//                .mapToInt(ite -> ite * 2)
//                .skip(2)
//                .limit(2).summaryStatistics();
//         System.out.println(intSummaryStatistics.getMin());
//         System.out.println(intSummaryStatistics.getCount());
//         System.out.println(intSummaryStatistics.getMax());

         //4.stream不能重复使用, 下面的的代码会报错,因为stream1.filter()会产生一个新的stream,
         //而 strem1.distinct 又重复使用了原来的stream1, 所以会报错
//           System.out.println(stream1);
//           System.out.println(stream1.filter(item -> item > 2));
//           System.out.println(stream1.distinct());

         //5.下面的方式可以 避免上面重复使用stream问题
         System.out.println(stream1);
         Stream<Integer> stream2 = stream1.filter(item -> item > 2);
         System.out.println(stream2);
         Stream<Integer> stream3 = stream2.distinct();
         System.out.println(stream3);

    }
}

```
13.8 
```java
package com.learn.jdk.chapter17;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo8
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 13:59
 * History:
 * @<version> 1.0
 */
public class StreamDemo8 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "lin");
        // 将集合中元素的首字母转换成大写，其他的字母不变
        //1.打印
//        list.stream().map(item ->
//                item.substring(0,1).toUpperCase() + item.substring(1))
//                .forEach(System.out::println);

        //2.下面的方式 不会打印出东西，因为没有终止操作
        //如果在程序的下面 加上forEach 就可以有数据打印出了
         list.stream().map(item -> {
               String result = item.substring(0,1).toUpperCase() + item.substring(1);
               System.out.println("t");
               return result;
         });
    }
}

```
13.8 IntStream和 distinct
```java
package com.learn.jdk.chapter17;

import java.util.stream.IntStream;

/**
 * @ClassName: StreamDemo9
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 14:19
 * History:
 * @<version> 1.0
 */
public class StreamDemo9 {
    public static void main(String[] args) {
        // 下面操作是不会结束的。是因为distinct 只是去重，而前面的iterate 一直返回0,1,
        // 没有一个限定范围
//        IntStream.iterate(0, i -> (i + 1) % 2)
//                .distinct()
//                .limit(6)
//                .forEach(System.out::println);

        // 该为这种方式就可以了, 限定流产生6个元素，再去重
        IntStream.iterate(0, i -> (i + 1) % 2)
                .limit(6).distinct().forEach(System.out::println);
    }
}
```
13.9 stream的短路和并发流
```java
package com.learn.jdk.chapter20;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * chapter 20
 * @ClassName: SteamDemo10
 * @Description: stream的短路和并发流
 * @Author: lin
 * @Date: 2020/1/2 16:29
 * History:
 * @<version> 1.0
 */
public class SteamDemo10 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        int count = 5000000;
        for (int i = 0; i < count; i++) {
             list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        // 纳秒
        long startTime = System.nanoTime();
        // 串行流
//        list.stream().sorted().count();
        // 开始排序
        //排序耗时:6787

        // 并行流
        list.parallelStream().sorted().count();
        //开始排序
        //排序耗时:3782

        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("排序耗时:" + millis);
    }
}
```
13.10 stream().mapToInt()
```java
package com.learn.jdk.chapter20;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: StreamDemo11
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 16:56
 * History:
 * @<version> 1.0
 */
public class StreamDemo11 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        //1、打印这个列表中 长度为5的第一个单词, 并且把长度打印出来

//        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5)
//                .findFirst().ifPresent(System.out::println);
        // 如果长度没有5的，那么打印出来的是空的

        //2、这种方式也可以
//        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5)
//                .limit(1).forEach(it -> System.out.println(it));

        //3、
        // Stream 可以看作为一个容器，这个容器存放的是对每个元素的操作
        // mapToInt是一种操作, filter是一种操作。
        // 比如说对流的处理 如迭代，判断、过滤等它会拿着容器中操作 逐个应用到stream中的每一个元素上，
        // 并且将这些操作串行化，一个元素应用了一个操作之后 接着第二个操作等。
        // "而不是" 对于一个元素应用完第一个操作，第二个元素应用完第二个操作。

        //注意: stream是存在短路运算的，只要找到符合条件的 后面的就不会再去执行
        list.stream().mapToInt(item -> {
          int length = item.length();
          System.out.println(item);
          return  length;
        }).filter(length -> length == 5)
                .findFirst().ifPresent(System.out::println);
    }
}

```
13.11 stream 和 flatMap
```java
package com.learn.jdk.chapter20;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamDemo12
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 17:41
 * History:
 * @<version> 1.0
 */
public class StreamDemo12 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello",
                "hello world hello", "hello welcome");
        // 找出列表中所有的单词 并进行去重

        // 1、这种方式不对
//        List<String[]> collect = list.stream().map(item -> item.split(" "))
//                .distinct().collect(Collectors.toList());
//        collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));


        // 2、下面使用flatMap
        //  Arrays::stream(T[] array) 接收一个数组的 返回一个stream对象。
        // flatMap 将多个中间的stream合并成一个stream.
        List<String> result = list.stream().map(item -> item.split(" "))
                .flatMap(Arrays::stream).distinct()
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}

```
13.12 stream分组与分区详解
```java
package com.learn.jdk.chapter21;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * chapter21
 * @ClassName: StreamDemo12
 * @Description: stream分组与分区详解
 * @Author: lin
 * @Date: 2020/1/2 21:44
 * History:
 * @<version> 1.0
 */
public class StreamDemo13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hi", "Hello", "你好,哈哈哈");
        List<String> list2 = Arrays.asList("zairian", "lisa",
                "wangle", "zarla");
        // 将两个集合组合起来，形成各自 人名打招呼。

        List<String> result = list.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).
                collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}

```
14、 stream 和 分组：group by  分区：partition by 
```java
package com.learn.jdk.chapter21;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamDemo14
 * @Description:
 * @Author: lin
 * @Date: 2020/1/2 22:17
 * History:
 * @<version> 1.0
 */
public class StreamDemo14 {
    public static void main(String[] args) {
        StudentTest test1 = new StudentTest("zaneta", 100, 20);
        StudentTest test2 = new StudentTest("lise", 80, 20);
        StudentTest test3 = new StudentTest("wadge", 90, 30);
        StudentTest test4 = new StudentTest("zeal", 70, 40);

        List<StudentTest> list = Arrays.asList(test1, test2, test3, test4);

        // 根据名称分组
//        Map<String, List<StudentTest>> map = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getName));
//        System.out.println(map);
        //根据分数分组
//        Map<Integer, List<StudentTest>> collect = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getScore));
//        System.out.println(collect);

        //根据名字分组，然后 count
//        Map<String, Long> collect2 = list.stream().
//                collect(Collectors.groupingBy(StudentTest::getName, Collectors.counting()));
//        System.out.println(collect2);

        //根据名称分组，再求分数的平均
        Map<String, Double> collect3 = list.stream().
                collect(Collectors.groupingBy(StudentTest::getName,
                        Collectors.averagingLong(StudentTest::getScore)));
        System.out.println(collect3);

        // 分区partition, 就是Boolean值，要么true, 要么false
        Map<Boolean, List<StudentTest>> listMap = list.stream().
                collect(Collectors.partitioningBy(studentTest -> studentTest.getScore() >= 90));
        System.out.println(listMap);


        // 分组：group by
        // 分区：partition by (结果只会有两个分区，在程序语言中表达就是true 和false. 分区是分组的一种特例)
    }
}

```
14.1 通过源码分析 ,深入理解stream, collect等
```java
package com.learn.jdk.chapter22;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * chapter22
 * @ClassName: StreamTest1
 * @Description: 通过源码分析 ,深入理解stream, collect等
 * @Author: lin
 * @Date: 2020/1/3 8:53
 * History:
 * @<version> 1.0
 */
public class StreamTest1 {
    public static void main(String[] args) {
        //
        StudentTe t1 = new StudentTe("zings", 70);
        StudentTe t2 = new StudentTe("lisa", 90);
        StudentTe t3 = new StudentTe("wang", 100);
        StudentTe t4 = new StudentTe("zarla", 90);

        List<StudentTe> studentTes = Arrays.asList(t1, t2, t3, t4);

        // 静态导入 Collectors类
        // 所以这里显示Collectors.toList()
//        List<StudentTe> studentTeList = studentTes.stream().
//                collect(toList());
//        studentTeList.forEach(System.out::println);

//        System.out.println("---------------------");
//
//        System.out.println("count: " + studentTes.stream().collect(counting()));
//        System.out.println("count: " + studentTes.stream().count());
    }

}

```
14.2、 收集器用法详解与多分组和分区
```java
package com.learn.jdk.chapter25;

import com.learn.jdk.chapter22.StudentTe;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * chapter25
 * @ClassName: StreamTest2
 * @Description: 收集器用法详解与多分组和分区
 * @Author: lin
 * @Date: 2020/1/3 16:21
 * History:
 * @<version> 1.0
 */
public class StreamTest2 {
    public static void main(String[] args) {

        // 测试
        StudentTe  te1 = new StudentTe("zings", 74);
        StudentTe  te2 = new StudentTe("lisa", 74);
        StudentTe  te3 = new StudentTe("wang", 100);
        StudentTe  te4 = new StudentTe("zarla", 80);
        StudentTe  te5 = new StudentTe("zarla", 80);

        List<StudentTe> studentTes = Arrays.asList(te1, te2, te3, te4, te5);
        //求出分数最低的学生

        studentTes.stream().collect(minBy(Comparator.comparingInt(StudentTe::getScore))).
                ifPresent(System.out::println);
        studentTes.stream().collect(maxBy(Comparator.comparingInt(StudentTe::getScore))).
                ifPresent(System.out::println);
        System.out.println(studentTes.stream().collect(averagingInt(StudentTe::getScore)));

        System.out.println(studentTes.stream().collect(summingInt(StudentTe::getScore)));
        // 求出摘要信息
        IntSummaryStatistics intSummaryStatistics = studentTes.stream().collect(summarizingInt(StudentTe::getScore));
        System.out.println(intSummaryStatistics);

        System.out.println("-----------------------");
        //拼接名字
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining()));
        //joining(", ")
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining(", ")));
        //加入前缀和后缀
        System.out.println(studentTes.stream().
                map(StudentTe::getName).collect(joining(", ", "<begin> ", " <end>")));

        System.out.println("-----------------------");
        // 分组， 第一次分组外层key 是一个integer类型，然后在对第一次分组的进行第二次分组
        // 第二次分组key是String类型 ,value是 List<StudentTe>.
        Map<Integer, Map<String, List<StudentTe>>> collect = studentTes.stream().
                collect(groupingBy(StudentTe::getScore, groupingBy(StudentTe::getName)));
        System.out.println("分组= " + collect);

        System.out.println("----------------------------");
        // 分区
        Map<Boolean, List<StudentTe>> collect1 = studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80));
        System.out.println("分区: "+ collect1);
        // 在根据分数大于80，进行再次分区。

        Map<Boolean, Map<Boolean, List<StudentTe>>> mapMap =
        studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80,
                        partitioningBy(studentTe -> studentTe.getScore() > 90)));
        //返回的结果就是嵌套的了
        System.out.println("再分区: " + mapMap);

        System.out.println("---------------------");
        // 求出每个分区中，学生的个数
        Map<Boolean, Long> longMap = studentTes.stream().
                collect(partitioningBy(studentTe -> studentTe.getScore() > 80, counting()));
        System.out.println("求出每个分区中，学生的个数 " + longMap);

        // 根据名字分组，在得到学生的分数
        // 使用collectingAndThen()去收集最小值，收集完之后 然后在从里面把它包含的值取出来，
        // 这个get一定是有值的，这是因为groupingBy()分组. 每一组中是一定有数据的。
        Map<String, StudentTe> collect2 = studentTes.stream().
                collect(groupingBy(StudentTe::getName,
                        collectingAndThen(minBy(Comparator.comparingInt(StudentTe::getScore)),
                                Optional::get)));
        System.out.println("分组: " + collect2);
    }
}

```
14.3 Comparator 详解与类型推断
```java
package com.learn.jdk.chapter26;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * chapter26
 * @ClassName: ComparatorDemo
 * @Description: Comparator 详解与类型推断
 * @Author: lin
 * @Date: 2020/1/5 10:58
 * History:
 * @<version> 1.0
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //根据字母进行升序的排序
//        Collections.sort(list);
//        System.out.println(list);
        // 字符串长度
//        Collections.sort(list, (item1 , item2) -> item1.length() - item2.length());
//        Collections.sort(list, (item1 , item2) -> item2.length() - item1.length());
//        System.out.println(list);

        // 使用方法引用, 降序
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
//        System.out.println(list);

        //这里使用item -> item.length()报错，
        // lambda表达式的参数推断, 如果编译器不能推断类型是，必须要明确指定参数类型
        // 这里推断 item 的类型 是根据 comparingInt(ToIntFunction<? super T> keyExtractor)方法来进行的，
        // 而这个方法里面的泛型T 是字符串类型，而? 是 T的父类，或者父类的父类。
        // 所以这里无法去推断类型，只能是给一个同样的类型Object。
        // 加上reversed()方法会影响类型的推断。
//        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());
//        System.out.println(list);

        //注意： 【这个编译报错】，
        // Collections.sort(list, Comparator.comparingInt((Boolean item) -> 1).reversed());


        //先按照字符串的长度，然后在按照字母的升序顺序排序(这里使用thenComparing)，使用jdk的不区分大小写
        //Collections.sort(list, Comparator.comparingInt(String::length).
        // thenComparing(String.CASE_INSENSITIVE_ORDER));


//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));

        // 方法引用的方式
//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing(Comparator.comparing(String::toLowerCase)));

        //Comparator.comparing()的重载方法， Comparator.reverseOrder 将顺序逆转过来
//        Collections.sort(list, Comparator.comparingInt(String::length).
//                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        // 首先根据字符串长度排序升序，再reversed进行降序排序，然后thenComparing是对于前面结果等于0的才去引用thenComparing，
        // 如果不等于0就不会应用thenComparing，
        // 将字母转换为小写，再逆序排序
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
//                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        // 输出结果 [welcome, world, nihao, hello]


        //thenComparing 比较器的串联
        // 再加一个thenComparing, 结果还是和上面的一样，为什么呢？
        // 这是因为在Comparator.comparingInt(String::length).reversed()，
        // thenComparing 第一次的应用的时候 其中的元素[welcome, nihao, hello, world].这个时候对其后面的三个元素进行比较
        // 也就是两两比较不会为零， 返回的  res = compare(c1, c2); res 不为零。 所以第二个thenComparing 不会引用到。
        // 因此结果还是一样的
        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));
        //

        System.out.println(list);
    }
}

```

15、扩展BinaryOperator
```java
package com.learn.jdk.extension;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * BinaryOperator：
 * 对两个相同类型的操作数进行的运算, 产生与该操作数相同的类型的结果
 * 这个是BiFunction的一种具体或者特例, BiFunction接收三个参数,前两个参数是输入参数类型,第三个参数是返回参数类型
 * @ClassName: BinaryOperatorDemo
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 10:34
 * History:
 * @<version> 1.0
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
       BinaryOperatorDemo binaryOperatorDemo = new BinaryOperatorDemo();
        //操作本身要符合BinaryOperator 中的apply的定义
        System.out.println("两数相加====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 + v2));

        System.out.println("两数相减====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 - v2));
        System.out.println("两数相乘====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 * v2));
        System.out.println("两数相除====" +binaryOperatorDemo.calculation(2, 3, (v1, v2) -> v1 / v2));

        // 取出长度小的
        System.out.println(binaryOperatorDemo.getSort("hello123", "world", (a, b) -> a.length() - b.length()));
        //上面的可以替换为 Comparator.comparingInt 方式 ,方法引用
        // binaryOperatorDemo.getSort("hello", "world", Comparator.comparingInt(String::length));

        // 首字母在ascii码 上排前的就是小的
        System.out.println(binaryOperatorDemo.getSort("hello", "world", (a, b) -> a.charAt(0) - b.charAt(0)));
    }

    /**
     * 操作本身是一种抽象
     * @param a
     * @param b
     * @param binaryOperator
     * @return
     */
    public int calculation(int a, int b ,  BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(a, b);
    }

    public String getSort(String a, String b, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(a, b);
    }
}

```

16、内部迭代和外部迭代本质
```
stream 和sql 语句很类似
select name from student where age > 23 and address ="shanghai" order by age desc;

内部迭代
描述性的语言
students.stream().filter(student ->student.getAge()-23)
 .filter(student -> student.getAddress().equals("shanghai"))
 .sorted(...).forEach(student -> System.out.println(student.getName()));

中间操作都会返回一个stream对象，比如说返回Stream<Student>, Stream<Integer>, Stream<String>
终止操作则不会返回Stream类型，可能不返会值， 也有可能返回其他类型的单个值。

外部迭代
jdk8 之前的写法，这种写法描述性不强
List<Student> list = new ArrayList<>();
for(int i=0; i< students.size(); i++){
 Student student = students.get(i);
  if(student.getAge() > 20 && student.getAddress().equals("shanghai")){
   list.add(student);
 }
}
Collections.sort(list, Comparator(...));
for(Student student : list){
  System.out.println(student.getName());
}

fork join
集合关注是数据与数据存储本身
流关注的则是对数据的计算

流与迭代器类似的一点是: 流是无法重复使用或消费的。

```
17、stream分组和分区
```
分组：group by
分区：partition by (结果只会有两个分区，在程序语言中表达就是true 和false)
```

18、深入源码分析 collect
```
1、collect 收集器
2、Collector 作为collect方法的参数
3、Collector 是一个接口, 她是一个可变的汇聚(归约)操作，将输入元素积累到一个可变的结果容器中；
  它会在所有元素都处理完毕后，将积累的结果转换为一个最终的表示(这是一个可选操作), 
  还原操作可以顺序或并行执行
有对应的实现类供我们去使用.

4、Collectors 本身提供了关于Collector的常见汇聚实现, Collectors本身实际上是一个工厂.
5、为了确保串行与并行操作结果的等价性，Collector函数需要满足两个条件: identity(同一性) 与associativity(关联性)
6、a(某一个线程它的执行分支所得到的一个部分结果) == combiner.apply(a, supplier.get())
7、函数式编程最大的特点: 表示做什么, 而不是如何做。 

注意：
串行
 A a1 = supplier.get();
 *     accumulator.accept(a1, t1); 第一个参数是每一次累积之后的中间结果
, 第二个参数是stream中要处理的下一个元素 , 所以开始调用supplier.get() 得到a1, 这是一个结果容器。
  这个结果容器是空的，每次要往这个容器中累积类容。所以第一次调用时这个容器是空的。
t1是流中的下一个元素
 *     accumulator.accept(a1, t2);
 *      

并行
       A a2 = supplier.get();
 *     accumulator.accept(a2, t1); 
       一个线程将结果累积到a2中，
 *     A a3 = supplier.get();
       先得到一个新的容器，然后另外一个线程将遇到的第一个元素t2累积到a3中。 这个a2和a3是两个不同的对象
 *     accumulator.accept(a3, t2);
       前面的代码执行完成后产生两个部分结果a2,a3, 
       然后调用combiner.apply(a2, a3)将两个部分结果合并起来 形成一个结果。
       将这个结果传递给finisher.apply(). 最后会把这个合并后的结果转换成 最终的r2结果. 
       对于结合性上来说 r1 必须等价于r2. 因为r1和r2 本身就是最终执行的结果。
 *     R r2 = finisher.apply(combiner.apply(a2, a3)); 
     
对于单线程和多线程执行过程不一样，但是执行结果必须是一样的。

reduce 和 collector 区别：
reduce: 不可变性，里面处理的对象都是不可变的的。对于reduce操作 它本来是不可变的，但是通过一种可变的操作去实现它。
 在单线程情况下是可以正常运行的，但是在并行流情况下就错乱了
collector(mutable container): 一种可变的行为,像LinkList, ArrayList就是可变结果容器

==========================================
一、
* @see Stream#collect(Collector)
 * @see Collectors
 *
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 *            hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @since 1.8
 */
public interface Collector<T, A, R> 
接口中,
1、参数T是流中每一个元素的类型， 
2、A 是可变的容器的类型，比如第一个元素累积到集合当中，
接着把第二个元素累积到集合当中....所以这个A实际上就是集合的类型。
可以认为这个A就是中间操作生成结果的类型
3、R表示汇聚结果的操作类型

二、
/**
 * A function that folds a value into a mutable result container.
 *
 * @return a function which folds a value into a mutable result container
 */
 BiConsumer<A, T> accumulator();
BiConsumer 类中:
public interface BiConsumer<T, U>
 第一个参数T表示每一次操作的中间结果的那个类型, 而后面的U表示后面流中待处理的下一个元素类。
所有根据BiConsumer类中的描述, 
这里collector接口中 BiConsumer<A, T>  的参数A 就表示结果类型，每一次都要对结果做处理，
并处理完之后这个结果作为下一次调用的第一个参数传进去。所以这个A就表示每一次处理的结果的类型
而T就表示流中待处理的下一个元素的类型

```

19、收集器用法详解和多级分组合分区
```
Collectors 类是一个辅助类，可以认为是一个工厂。它的作用就是向开发者提供常见的Collector实现。
并且Collectors的构造方法被设计为私有的，从而杜绝你创建对象的可能性. 
 它既然是工厂那么里面的方法大多都是静态方法。既然Collectors是工厂类 提供了常见的Collector接口的实现。
我们知道Collector是接口 既然要实现那么一定要返回实现类xxxImpl 的实例。 
CollectorImpl类和Collectors类的结合性是非常密切的，
所以jdk的设计者就直接将CollectorImpl实现类放在了Collectors里面了 因为其他的地方是不会用到的。所以就直接放到Collectors类里面

我们看Collectors 中实现的一些常用的方法，它返回的都是CollectorImpl实例。
public static <T>
    Collector<T, ?, List<T>> toList() {
        return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
                                   (left, right) -> { left.addAll(right); return left; },
                                   CH_ID);
    }

```

20、自定义收集器
```java
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
     * 累加器，不断的从流中遍历元素，将这个元素从<A,T>  T加A当中
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


```
20.1 stream 中collect的实现在ReferencePipeline中
```
    @Override
       @SuppressWarnings("unchecked")
       public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
           A container;
           if (isParallel()
                   && (collector.characteristics().contains(Collector.Characteristics.CONCURRENT))
                   && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
               container = collector.supplier().get();
               BiConsumer<A, ? super P_OUT> accumulator = collector.accumulator();
               forEach(u -> accumulator.accept(container, u));
           }
           else {
               container = evaluate(ReduceOps.makeRef(collector));
           }
           return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
                  ? (R) container
                  : collector.finisher().apply(container);
       }

==========================================================================
  public static <T, I> TerminalOp<T, I>
    makeRef(Collector<? super T, I, ?> collector) {
        Supplier<I> supplier = Objects.requireNonNull(collector).supplier();
        BiConsumer<I, ? super T> accumulator = collector.accumulator();
        BinaryOperator<I> combiner = collector.combiner();
        class ReducingSink extends Box<I>
                implements AccumulatingSink<T, I, ReducingSink> {
            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(T t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
            }
        }
        return new ReduceOp<T, I, ReducingSink>(StreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }

            @Override
            public int getOpFlags() {
                return collector.characteristics().contains(Collector.Characteristics.UNORDERED)
                       ? StreamOpFlag.NOT_ORDERED
                       : 0;
            }
        };
    }

```

20.2 castingIdentity的说明，给定一个输入直接就把他强制转换成结果
```

   @SuppressWarnings("unchecked")
    private static <I, R> Function<I, R> castingIdentity() {
        return i -> (R) i;
    }
```
20.3 Collector中IDENTITY_FINISH的解释

```java
    /**
     * Indicates that the finisher function is the identity function and
     * can be elided.  If set, it must be the case that an unchecked cast
     * from A to R will succeed.
表示finisher函数就是identity函数并且可以被省略掉,如果设置了,
那么必须是未检出的转换从A到R的类型转换一定是成功的. 
如果标注为这个特性,那么他会将中间结果直接进行一个强制类型转换,转换成结果的R类型 然后返回。
不会调用finisher这个方法
IDENTITY_FINISH
     */
        

/**
 * Indicates that this collector is <em>concurrent</em>, meaning that
 * the result container can support the accumulator function being
 * called concurrently with the same result container from multiple
 * threads.
 *
 * <p>If a {@code CONCURRENT} collector is not also {@code UNORDERED},
 * then it should only be evaluated concurrently if applied to an
 * unordered data source.
表示这个收集器是并行的，这意味着结果容器支持 accumulator函数 并行的被调用, 在相同的容器下使用多个线程。
也就是多个线程同时的去操作一个相同的结果容器。
如果 一个并行收集器它不是UNORDERED,那么它只能被并行的用于无序的数据源。
CONCURRENT
 */
       
```

21、自定义收集器深度剖析与并行流陷阱
  和收集器枚举值特性分析和并行流原理
```java
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


```
22、Collectors工厂类源码分析与实战
```
收集器:
对于Collectors静态工厂类来说,其实现一共分为两种情况:
1、通过CollectorImpl来实现。
2、通过reducing方法来实现, reducing方法本身有通过CollectorImpl实现的。


static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A,R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }



/**
     * Returns a {@code Collector} that accumulates the input elements into a
     * new {@code Collection}, in encounter order.  The {@code Collection} is
     * created by the provided factory.
     *
     * @param <T> the type of the input elements
     * @param <C> the type of the resulting {@code Collection}
     * @param collectionFactory a {@code Supplier} which returns a new, empty
     * {@code Collection} of the appropriate type
     * @return a {@code Collector} which collects all the input elements into a
     * {@code Collection}, in encounter order
     */
    这个方法更为广泛和通用
    public static <T, C extends Collection<T>>
    Collector<T, ?, C> toCollection(Supplier<C> collectionFactory) {
        对于这个方法必须要接收一个参数collectionFactory, 因为这个参数用于指定中间结果容器以及最终所返回的结果的集合类型,所以需要自己提供
        如果要返回一个LinkList那么就传入一个LinkList::new 那么就可以用这个方法
        return new CollectorImpl<>(collectionFactory, Collection<T>::add,
                                   (r1, r2) -> { r1.addAll(r2); return r1; },
                                   CH_ID);
    }

    /**
     * Returns a {@code Collector} that accumulates the input elements into a
     * new {@code List}. There are no guarantees on the type, mutability,
     * serializability, or thread-safety of the {@code List} returned; if more
     * control over the returned {@code List} is required, use {@link #toCollection(Supplier)}.
     *
     * @param <T> the type of the input elements
     * @return a {@code Collector} which collects all the input elements into a
     * {@code List}, in encounter order
     */
    public static <T>
    Collector<T, ?, List<T>> toList() {
        //对toList进行分析 
        // 首先CollectorImpl 实现类中有几个参数,
         supplier用于生产中间结果容器的 、
         accumulator累加器、
         combiner合并器、
         finisher完成器、
         characteristics 特性。
        // 它的实现如下,
        第一个参数是supplier,然后它用 ArrayList::new 强制的转换为(Supplier<List<T>>)。实际上它也可以这样写ArrayList<T>::new。
        第二个参数是accumulator累加器, 累积器是不断往中间结果中添加,这里调用List::add方法引用,传进来的第一次参数作为add方法的调用者,第二个参数作为add的参数
        第三个参数combiner合并器,这里使用lambda表达式(left, right) -> { left.addAll(right); return left; } 这里是将后面的部分结果right添加到第一个left中 然后返回left
        第四个参数CH_ID是个常量, 这个常量里面定义的是IDENTITY_FINISH,也就是说中间结果类型和最终的结果类型是同一个类型,
        也就是说toList()方法是不需要finisher的。
        return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
                                   (left, right) -> { left.addAll(right); return left; },
                                   CH_ID);
    }


  /**
     * Returns a {@code Collector} that accumulates the input elements into a
     * new {@code Set}. There are no guarantees on the type, mutability,
     * serializability, or thread-safety of the {@code Set} returned; if more
     * control over the returned {@code Set} is required, use
     * {@link #toCollection(Supplier)}.
     *
     * <p>This is an {@link Collector.Characteristics#UNORDERED unordered}
     * Collector.
     *
     * @param <T> the type of the input elements
     * @return a {@code Collector} which collects all the input elements into a
     * {@code Set}
     */
     // toSet()也是一种特化版本
    public static <T>
    Collector<T, ?, Set<T>> toSet() {
        // 首先中间结果容器是 set类型, 累加器是 Set::add, 
        然后合并器(left, right) -> { left.addAll(right); return left; }
        characteristics 特性是UNORDERED和IDENTITY_FINISH, 这说明是无序的同时 中间结果类型和最终结果类型是一致的
      
        return new CollectorImpl<>((Supplier<Set<T>>) HashSet::new, Set::add,
                                   (left, right) -> { left.addAll(right); return left; },
                                   CH_UNORDERED_ID);
    }


/**
     * Returns a {@code Collector} that concatenates the input elements into a
     * {@code String}, in encounter order.
     *
     * @return a {@code Collector} that concatenates the input elements into a
     * {@code String}, in encounter order
     */
      将多个字符串拼接,不用任何的分割符 分割
    public static Collector<CharSequence, ?, String> joining() {
        //首先new CollectorImpl(), 三个参数
         第一个是流中元素类是CharSequence
         第二个是中间结果容器 StringBuilder
         第三个是结果String, 这里可以知道StringBuilder和String类型不一样,中间结果类型和最终结果类型是不一致的。
         所以要使用finisher
        实现 首先 
         第一个参数 supplier 调用StringBuilder::new,
         第二个参数 accumulator累加器 StringBuilder::append 使用append不断往StringBuilder追加
         第三个参数 combiner合并器 (r1, r2) -> { r1.append(r2); return r1; } r2不断的append到r1中 然后返回r1,
         第四个参数 finisher完成器 StringBuilder::toString 这里要使用的原因是类型不一致 所以相当于把StringBuilder转换成字符串,
         第五个参数 characteristics 特性CH_NOID 表示三个特性都不具备。
        return new CollectorImpl<CharSequence, StringBuilder, String>(
                StringBuilder::new, StringBuilder::append,
                (r1, r2) -> { r1.append(r2); return r1; },
                StringBuilder::toString, CH_NOID);
    }


/**
     * Returns a {@code Collector} that concatenates the input elements,
     * separated by the specified delimiter, in encounter order.
     *
     * @param delimiter the delimiter to be used between each element
     * @return A {@code Collector} which concatenates CharSequence elements,
     * separated by the specified delimiter, in encounter order
     */
    中间有分割符
    public static Collector<CharSequence, ?, String> joining(CharSequence delimiter) {
        return joining(delimiter, "", "");
    }



  /**
     * Adapts a {@code Collector} accepting elements of type {@code U} to one
     * accepting elements of type {@code T} by applying a mapping function to
     * each input element before accumulation.
     *
     * @apiNote
     * The {@code mapping()} collectors are most useful when used in a
     * multi-level reduction, such as downstream of a {@code groupingBy} or
     * {@code partitioningBy}.  For example, given a stream of
     * {@code Person}, to accumulate the set of last names in each city:
     * <pre>{@code
     *     Map<City, Set<String>> lastNamesByCity
     *         = people.stream().collect(groupingBy(Person::getCity,
     *                                              mapping(Person::getLastName, toSet())));
     * }</pre>
     *
     * @param <T> the type of the input elements
     * @param <U> type of elements accepted by downstream collector
     * @param <A> intermediate accumulation type of the downstream collector
     * @param <R> result type of collector
     * @param mapper a function to be applied to the input elements
     * @param downstream a collector which will accept mapped values
     * @return a collector which applies the mapping function to the input
     * elements and provides the mapped results to the downstream collector
     */
   // 这个方法就是映射, 适配一个Collector接收一个U类型的把它适配到一个T类型的, 也就是说这个收集器接收一个类型的你把它映射成另外的一个类型，
        在累积之前都应用一个映射函数。
    public static <T, U, A, R>
    Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,
                               Collector<? super U, A, R> downstream) {
        BiConsumer<A, ? super U> downstreamAccumulator = downstream.accumulator();
        return new CollectorImpl<>(downstream.supplier(),
                                   (r, t) -> downstreamAccumulator.accept(r, mapper.apply(t)),
                                   downstream.combiner(), downstream.finisher(),
                                   downstream.characteristics());
    }

```










