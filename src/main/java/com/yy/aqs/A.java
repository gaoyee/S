package com.yy.aqs;

import java.util.concurrent.atomic.AtomicInteger;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class A {
    //ghp_p54ZvKAcvAYp2EP
    // VTz30h1acE4rlen02JUR9

    private AtomicInteger atomicInteger = new AtomicInteger();




    //硬件级别还是有锁的, aba问题
    public synchronized void add() {
        atomicInteger.incrementAndGet();
    }


}
