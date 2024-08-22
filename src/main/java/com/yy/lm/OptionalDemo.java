package com.yy.lm;

import com.yy.lm.model.Author;

import java.util.Optional;
import java.util.function.BiConsumer;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Author> author2 = getAuthor2();
        author2.ifPresent(author -> System.out.println(author.getAge()));

    }
    public static Author getAuthor(){
        return null;
    }

    public static Optional<Author> getAuthor2(){
        return Optional.of(new Author());
    }
}
