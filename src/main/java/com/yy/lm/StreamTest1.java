package com.yy.lm;

import com.yy.lm.model.Author;
import com.yy.lm.model.Book;
import com.yy.lm.model.StreamUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class StreamTest1 {
    public static void main(String[] args) {
        List<Author> authorList = StreamUtil.getAuthorList();


        Integer reduce = authorList.stream().map(Author::getAge).reduce(1, (integer, integer2) -> integer * integer2);
        System.out.println("reduce = " + reduce);


        Integer reduce1 = authorList.stream().map(Author::getAge).reduce(Integer.MIN_VALUE, (integer, integer2) -> integer2 > integer ? integer2 : integer);
        System.out.println("reduce1 = " + reduce1);




    }


}
