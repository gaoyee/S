package com.yy.util.task;
/*****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * http://next-1b.com
 * @author: chuer
 *****************************************************************************/
public enum TaskType {
    NULL,//null
    BUSINESS,//业务线程池
    COMMON, //通用线程池
    OTHER, //耗时线程池
    CENTER, //中心服协议处理线程池
    BATTLE, //战斗服协议处理耗时线程池
    REDIS, //处理redis线程池
}
