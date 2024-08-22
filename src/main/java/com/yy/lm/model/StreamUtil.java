package com.yy.lm.model;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class StreamUtil {
    public static List<Author> getAuthorList() {
        List<Author> ret = new ArrayList<>();
        int authorNum = 5;
        for (int i = 0; i < authorNum; i++) {
            Author author = new Author();
            author.setAge(random());
            author.setId(i + 1);
            author.setName(randomName());
            int bookNum = randomSmall();
            List<Book> bookList = new ArrayList<>();
            for (int i1 = 0; i1 < bookNum; i1++) {
                bookList.add(randomBook());
            }
            author.setBooks(bookList);
            ret.add(author);



        }
        System.out.println(ret);
        System.out.println("------------------------------------------------------");
        return ret;
    }




    public static Book randomBook() {
        Book book = new Book();
        book.setId(random());
        book.setCategory(randomIntro());
        book.setName(randomName());
        book.setScore(random());
        book.setIntro(randomIntro());
        return book;
    }


    public static int randomSmall() {
        return RandomIntExample.getRandomInt(1, 5);
    }

    public static int random() {
        return RandomIntExample.getRandomInt(1, 100);
    }

    public static String randomName() {

        return RandomChineseChars.getRandomChineseChars(conteng.trim().replaceAll("\n\t", ""), random() % 3 +1);
    }

    public static String randomIntro() {

        return RandomChineseChars.getRandomChineseChars(conteng.trim().replaceAll("\n\t", ""), random() % 9+1);
    }


    public static final String conteng = "君不见黄河之水天上来，奔流到海不复回。  \n" +
            "君不见高堂明镜悲白发，朝如青丝暮成雪。  \n" +
            "人生得意须尽欢，莫使金樽空对月。  \n" +
            "天生我材必有用，千金散尽还复来。  \n" +
            "烹羊宰牛且为乐，会须一饮三百杯。  \n" +
            "岑夫子，丹丘生，将进酒，杯莫停。  \n" +
            "与君歌一曲，请君为我倾耳听。  \n" +
            "钟鼓馔玉不足贵，但愿长醉不愿醒。  \n" +
            "古来圣贤皆寂寞，惟有饮者留其名。  \n" +
            "陈王昔时宴平乐，斗酒十千恣欢谑。  \n" +
            "主人何为言少钱，径须沽取对君酌。  \n" +
            "五花马、千金裘，呼儿将出换美酒，与尔同销万古愁。\n";
}
