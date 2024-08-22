package com.yy.util.task;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class GameThread implements Runnable{

    private Thread thread;

    private static final int STATE_NEW = 0;//新建
    private static final int STATE_RUN = 1;//运行中
    private static final int STATE_WAIT_CLOSE=2;//等待关闭
    private static final int STATE_CLOSE = 3;//已经关闭


    private static final Object lockObj = new Object();

    private static final Runnable EMPTY_TASK = ()->{};

    private volatile int state = STATE_NEW;

    private final BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<>();


    @Override
    public void run() {
        Runnable task;

        try{
            while (state == STATE_RUN){
               task = this.taskQueue.take();
               task.run();

            }
            if(this.state == STATE_WAIT_CLOSE){
                synchronized (this){
                    if(!this.taskQueue.isEmpty()){
                        task = this.taskQueue.poll();
                        while (task!=null){
                            task.run();
                            task = this.taskQueue.poll();
                        }
                    }
                    this.state = STATE_CLOSE;
                }
            }


        }catch (Exception e)
        {
            this.state = STATE_CLOSE;

        }


    }

    public void start(){
        this.state = STATE_RUN;
        this.thread.start();
    }

    /**
     * 检查线程状态
     */
    public void checkStatus() {
        if (this.state == STATE_RUN) {
            if (this.thread != null) {
                final Thread.State state = this.thread.getState();
                //如果线程
                if (state == Thread.State.TERMINATED) {
                    //重启线程
                    this.restart();
                }
            }
        }
    }

    /**
     * 重启线程
     */
    private void restart() {
        this.thread = new Thread(this, this.thread.getName());
        this.state = STATE_RUN;
        this.thread.start();
        System.out.println("THREAD_STARTUP_RE name:" + this.thread.getName());
    }


    /**
     * 停止线程
     */
    public void shutdownWait() throws InterruptedException {
        if (this.state == STATE_RUN) {
            this.state = STATE_WAIT_CLOSE;
            this.taskQueue.offer(EMPTY_TASK);//填一个空任务
            this.thread.join();
        }
    }

    /**
     * 添加空任务
     */
    public void fillEmptyTask() {
        this.addTask(EMPTY_TASK);
    }


    /**
     * 添加任务
     */
    public boolean addTask(Runnable task) {
        if (this.state == STATE_RUN) {
            //任务加入队列
            if (!this.taskQueue.offer(task)) {
                task.run();//任务队列满了，除非达到了Integer.MAX_VALUE才会满
                System.out.println("THREAD_ADD_TASK_ERROR thread queue full:" + this.thread.getName() + " " );
            }
        } else {
            //线程关闭了，还在往队列里放任务
            synchronized (lockObj) {//锁一个全局对象，否则容易出现死锁
                task.run();
                System.out.println("THREAD_ADD_TASK_ERROR thread stop name:" + this.thread.getName() + " " );
            }
        }
        return true;
    }

    public GameThread(final String name) {
        this.thread = new Thread(this, name);
    }
    /**
     * 转字符串
     */
    @Override
    public String toString() {
        return this.taskQueue.size() + ":" + this.thread.getState();
    }
}
