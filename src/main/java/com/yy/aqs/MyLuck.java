package com.yy.aqs;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class MyLuck {

    private volatile int state;

    public void lock() {

        while (state != 0) {

        }
        state = 1;


    }

    public void unlock() {
        state = 0;
    }
}
