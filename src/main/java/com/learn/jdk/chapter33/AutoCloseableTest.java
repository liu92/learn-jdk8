package com.learn.jdk.chapter33;

/**
 * 在忘记关闭流等等这些的时候,可以使用AutoCloseable的close方法来进行关闭
 * 这个方式可以自动关闭
 * try-with-resources statement.
 * @ClassName: AutoCloseableTest
 * @Description:
 * @Author: lin
 * @Date: 2020/1/15 16:20
 * History:
 * @<version> 1.0
 */
public class AutoCloseableTest implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("close invoked!");
    }


    public void doSomething(){
        System.out.println("doSomething invoked!");
    }

    public static void main(String[] args) throws Exception {
        //try-with-resources statement.
       try(AutoCloseableTest autoCloseableTest = new AutoCloseableTest()) {
                 autoCloseableTest.doSomething();
       }
    }
}
