package com.yy.util.task.bf;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class TestVolatile {
    private static volatile int age = 0;

    public static void main(String[] args) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int times = 0;
                while(true){
                    age = age + 1;
                    System.out.println(Thread.currentThread().getName() + "[" + age + "]");
                    times =times +1;
                }

            }
        });
        thread.setName("thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    age = age + 1;
                    System.out.println(Thread.currentThread().getName() + "[" + age + "]");
                }

            }
        });

        thread2.setName("thread2");
        thread.start();
        thread2.start();

//        while (true)
//        {
//            if(age == 10000){
//                System.exit(1);
//            }
//        }


    }
}
