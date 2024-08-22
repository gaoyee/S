package com.yy.aqs;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class SynchronizedTest {

    private LongAdder longAdder = new LongAdder();
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        reentrantLock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }

    public void add() {
        longAdder.increment();
    }
}
