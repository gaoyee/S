package com.yy.lm;

import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class BunsTest3 {
    public static void main(String[] args) {

        IntPredicate intPredicate = (a) -> a > 0;
        Function<Integer, Boolean> function = a -> a < 0;
//        test(intPredicate);
//        test2(function);
        IntConsumer intConsumer = (a) -> {
            System.out.println(a);
        };

        test3(intConsumer);


    }

    public static void test(IntPredicate intPredicate) {
        boolean test = intPredicate.test(7);
        System.out.println("test = " + test);
    }

    public static void test2(Function<Integer, Boolean> function) {
        boolean test = function.apply(7);
        System.out.println("test = " + test);
    }

    public static void test3(IntConsumer intConsumer) {
        int a = 5;
        intConsumer.accept(a);
    }

}
