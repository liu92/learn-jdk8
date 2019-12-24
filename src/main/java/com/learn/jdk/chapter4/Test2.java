package com.learn.jdk.chapter4;

/**
 *  chapter4 深入函数式接口与方法引用
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