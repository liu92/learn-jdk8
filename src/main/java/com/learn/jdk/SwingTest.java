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
