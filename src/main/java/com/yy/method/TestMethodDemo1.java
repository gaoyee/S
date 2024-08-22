package com.yy.method;

import com.yy.lm.model.Author;
import com.yy.lm.model.StreamUtil;

import java.util.List;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class TestMethodDemo1 {
    public static void main(String[] args) {
        List<Author> authorList = StreamUtil.getAuthorList();
        authorList.stream().map(author -> new StringBuffer((author.getName()))).forEach(System.out::println);
    }

}
