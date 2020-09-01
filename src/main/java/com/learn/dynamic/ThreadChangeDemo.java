package com.learn.dynamic;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadChangetDemo
 * @Description: 动态修改线程池
 * @Author: lin
 * @Date: 2020/7/24 16:04
 * History:
 * @<version> 1.0
 */
public class ThreadChangeDemo {

    public static void main(String[] args) throws InterruptedException {
      dynamicModifyExecutor();
    }

    /**
     * 自定义线程池
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor(){
     return  new ThreadPoolExecutor(2,
             5,
             60,
             TimeUnit.SECONDS,
             new ResizableCapacityLinkedBlockIngQueue<>(10),
             new DefaultThreadFactory("-动态线程数"));
    }

    /**
     * 先提交任务给线程池，并修改线程池参数
     */
    private  static  void  dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        int count = 15;
        for (int i = 0; i < count; i++) {
            executor.submit(()->{
               threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPoolStatus(executor, "======改变之前===");
        TimeUnit.SECONDS.sleep(1);
        //核心线程池大小
        executor.setCorePoolSize(10);
        //最大线程数
        executor.setMaximumPoolSize(10);
        executor.prestartAllCoreThreads();
        ResizableCapacityLinkedBlockIngQueue queue = (ResizableCapacityLinkedBlockIngQueue)executor.getQueue();
        queue.setCapacity(100);
        threadPoolStatus(executor, "============================改变之后======================");
        Thread.currentThread().join();
    }


    /**
     * 打印线程状态
     * @param executor
     * @param name
     */
    private static  void  threadPoolStatus(ThreadPoolExecutor executor, String name){
        ResizableCapacityLinkedBlockIngQueue queue = (ResizableCapacityLinkedBlockIngQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "_" + name + "-:" +
                "核心线程数:" + executor.getCorePoolSize() +
                " 活动线程数:" + executor.getActiveCount() +
                " 最大线程数:" + executor.getMaximumPoolSize() +
                " 线程池活跃度:" + div(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                " 任务完成数:" + executor.getCompletedTaskCount() +
                " 队列大小:" + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数:" + queue.size() +
                " 队列剩余大小:" + queue.remainingCapacity() +
                " 队列使用度:" + div(queue.size(), queue.size() + queue.remainingCapacity()));
    }

    private static  String div(int number1, int number2){
        return  String.format("%1.2f%%",
                Double.parseDouble(number1 + "") / Double.parseDouble(number2 + "") * 100);
    }
}



















