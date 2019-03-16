package com.dangqp.moduleone.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title:com.dangqp.moduleone.thread
 * Description:
 * Copyright: Copyright (c) 2019
 *
 * @author dangqp
 * @version 1.0
 * @created 2019/03/15  13:54
 */
public class ReEntryThread {
    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        //ReentrantLock lock = new ReentrantLock(true); // 公平锁
        //ReentrantLock lock = new ReentrantLock(false);// 非公平锁 默认 效率高
        Condition condition = lock.newCondition();
        if (lock.tryLock()){
            try {
                //get lock
                lock.lock();
                // call wait method 使用condition让线程等待必须先获取锁
                condition.await();
                // wake up thread
                condition.signal();
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread name is "+Thread.currentThread().getName()+i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }
}
