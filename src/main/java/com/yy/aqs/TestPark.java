package com.yy.aqs;

import java.util.concurrent.locks.LockSupport;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class TestPark {

    public static void main(String[] args) {

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                testSync();
            }
        });
        thread.start();

        System.out.println("Main = " + "1");

        LockSupport.unpark(thread);




    }

    public static void testSync(){
        LockSupport.park();
        System.out.println("Thread = " + "2");
    }




}
