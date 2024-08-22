package com.yy.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class Test {
    private static MyLuck lock = new MyLuck();
    private static Lock lock2 = new ReentrantLock();



    public static void draw () throws Exception{
        System.out.println(Thread.currentThread().getName() +"come");
        Thread.sleep(3000L);
        System.out.println(Thread.currentThread().getName() +" out");

    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    draw();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        },"Thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    draw();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        },"Thread2").start();

    }



}
