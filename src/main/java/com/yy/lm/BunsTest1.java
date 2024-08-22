package com.yy.lm;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class BunsTest1 {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("a")).start();

        new Thread(() -> System.out.println("b")).start();
    }


}
