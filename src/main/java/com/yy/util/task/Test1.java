package com.yy.util.task;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class Test1 {

    public static void main(String[] args)throws Exception {
        TaskManager.getIns().init(8);

        Thread.sleep(5000L);

        int  num = 50;
        for (int i = 0; i < num; i++) {
            int finalI = i;
            TaskManager.getIns().addTask(null, i, new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +"___hello ___" + finalI);
                }
            });
        }






    }


}
