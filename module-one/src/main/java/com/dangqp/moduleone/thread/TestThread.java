package com.dangqp.moduleone.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Title:com.dangqp.moduleone.thread
 * Description:
 * Copyright: Copyright (c) 2019
 *
 * @author dangqp
 * @version 1.0
 * @created 2019/03/15  09:48
 */
public class TestThread {

    /**
     * ping线程运行了
     */
    @Test
    public void testStart(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程运行了");
            }
        });
        t.start();
        System.out.print("ping");
    }

    /**
     * 线程运行了
     * ping
     */
    @Test
    public void testRun(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程运行了");
            }
        });
        t.run();
        System.out.print("ping");

    }

    /**
     * 必须有线程调用
     * 下面这种单元测试无法输出结果
     */
    @Test
    public void testExcutors(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        pool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3, TimeUnit.SECONDS);

        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("linger 1 second,then every 3 seconds run");
            }
        },1,3,TimeUnit.SECONDS);
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111111");
            }
        });
    }

    @Test
    public void testStop(){

    }
    /**
     * 11111111111
     * linger 1 second,then every 3 seconds run
     * delay 3 seconds
     * linger 1 second,then every 3 seconds run
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
//        pool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        },3, TimeUnit.SECONDS);
//
//        pool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("linger 1 second,then every 3 seconds run");
//            }
//        },1,3,TimeUnit.SECONDS);
//        pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("11111111111");
//            }
//        });
        //ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(1);
        Thread t1 = new Thread(getThread_one("thread one"));
        Thread t2 = new Thread(getThread_one("thread two"));
        Thread t3 = new Thread(getThread_one("thread third"));
        Thread t4 = new Thread(getThread_one("thread four"));
        /**
         * 可以实现顺序执行
         */
        Thread thread = Thread.currentThread();
        try {
            thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        /**
         * 这样也可以
         */
        singleThreadExecutor.submit(getThread_one("one"));
        singleThreadExecutor.submit(getThread_one("two"));
        singleThreadExecutor.submit(getThread_one("three"));
        singleThreadExecutor.submit(getThread_one("four"));
        singleThreadExecutor.shutdown();



    }

    public static volatile boolean exist = false;


    private static Runnable getThread_one(String s) {
        return () -> {
            System.out.println(s);
        };
    }

    static class ThradSafe extends Thread{
        public void run(){
            while (!exist){
                System.out.println("lalallaallalalalallalalala");
            }
        }
    }


}
