package com.yy.lm;

import java.util.function.IntBinaryOperator;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class BunsTest2 {
    public static void main(String[] args) {
        get(Integer::sum);
        int c =get((left, right) -> right - left);
        System.out.println("c = " + c);
    }

    public static int get(IntBinaryOperator intBinaryOperator) {
        int a = 6;
        int b = 7;
        return intBinaryOperator.applyAsInt(a, b);
    }
}
