package com.yy.util.task.bf;

import java.util.Date;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class TestInit {




    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int times = 0;
                while (true) {

                    if (Thread.currentThread().isInterrupted()) {
                        times = times + 1;
                        System.out.println("我被终端了" + times);
                        if (times == 10000) {
                            boolean interrupted = Thread.interrupted();
                        }
                    } else {
                        System.out.println("我正常运行了" + times + "时间" + new Date());
                    }
                    if(times == 10000){
                        break;
                    }


                }
            }
        });
        thread.setName("test中断");
        thread.start();
        while (true) {
            Thread.sleep(5000L);
            thread.interrupt();
            break;


        }


    }
}
