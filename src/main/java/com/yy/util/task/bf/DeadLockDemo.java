package com.yy.util.task.bf;

public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) throws InterruptedException {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }
        });
        t1.start();
        t2.start();
//        while(true)
//        {
//            Thread.sleep(5000L);
//            System.out.println("t1 = " + t1);
//            System.out.println("t2 = " + t2);
//        }
    }
}