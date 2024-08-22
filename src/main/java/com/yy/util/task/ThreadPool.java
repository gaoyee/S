package com.yy.util.task;

import java.util.concurrent.atomic.AtomicLong;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class ThreadPool {

    public static int BIND_ID_ROUND = -1;

    private final String name;
    /**
     * 线程池大小
     */
    private final int poolSize;

    /**
     * 线程列表
     */
    private final GameThread[] threadList;

    /**
     * 当前默认的绑定线程id，
     * 如果task的bindId为-1，那么根据此值来确定是进入那个线程，然后defaultBindId++。
     * 当defaultBindId < 0 的时候，重置为0
     */
    private AtomicLong defaultBindId = new AtomicLong(0);

    /**
     * 构造线程池
     */
    public ThreadPool(String poolName, int poolSize) {
        this.name = poolName;
        this.poolSize = poolSize;
        this.threadList = new GameThread[this.poolSize];
        for (int i = 0; i < poolSize; i++) {
            String name = poolName + "-" + i;
            this.threadList[i] = new GameThread(name);
        }

    }


    /**
     * 启动线程池
     */
    public void start() {
        for (GameThread thread : this.threadList) {
            thread.start();
        }
    }

    /**
     * 停止线程池
     */
    public void shutDown() {
        for (GameThread thread : this.threadList) {
            try {
                thread.shutdownWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * 线程填入空任务
     */
    public void fillEmptyTask() {
        for (GameThread thread : this.threadList) {
            thread.fillEmptyTask();
        }
    }

    /**
     * 检查线程状态
     */
    public void checkThreadStatus() {
        for (GameThread gameThread : this.threadList) {
            gameThread.checkStatus();
        }
    }

    public void addAllTask(Runnable task) {
        for (GameThread thread : this.threadList) {
            thread.addTask(task);
        }
    }

    public int poolSize() {
        return threadList.length;
    }

    /**
     * 线程池添加任务
     */
    public boolean addTask(long threadBindId, Runnable task) {
        if (this.poolSize <= 0) {
            //线程池中没有线程
            throw new RuntimeException("THREAD_POOL_EMPTY:" + this.name);
        }

        GameThread taskThread;
        //如果bindId >= 0 则进入指定的线程
        if (threadBindId >= 0) {
            int threadIdx = (int) (threadBindId % this.poolSize);
            taskThread = this.threadList[threadIdx];
        } else {
            //bindId < 0,根据默认bindId选择
            threadBindId = this.defaultBindId.incrementAndGet();
            if (threadBindId < 0) {
                this.defaultBindId.set(0);
                threadBindId = this.defaultBindId.incrementAndGet();
            }
            int threadIdx = (int) (threadBindId % this.poolSize);
            taskThread = this.threadList[threadIdx];
        }

        //寻找任务对象，发布任务
        return taskThread.addTask(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < this.threadList.length; i++) {
                sb.append(i).append(":").append(this.threadList[i].toString()).append(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
