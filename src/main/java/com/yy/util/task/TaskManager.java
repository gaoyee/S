package com.yy.util.task;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class TaskManager {
    /**
     * 业务线程池（非阻塞任务）
     */
    private ThreadPool businessPool;

    private static final TaskManager ins = new TaskManager();

    private TaskManager() {
    }

    public static TaskManager getIns() {
        return ins;
    }


    /**
     * 初始化
     */
    public void init(int businessPooSize) {
        this.businessPool = new ThreadPool("BUSINESS-POOL", businessPooSize);

        //启动线程池
        this.businessPool.start();

    }

    /**
     * 添加任务
     */
    public void addTask(TaskType type, long bindId, Runnable task) {
        businessPool.addTask(bindId, task);
    }

    public ThreadPool getBusinessPool() {
        return this.businessPool;
    }


}
