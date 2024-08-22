package com.yy.lm.model;

import java.util.ArrayList;
import java.util.Collections;  
import java.util.List;  
  
public class RandomChineseChars {  
  
    public static String getRandomChineseChars(String chineseString, int n) {  
        // 假设输入字符串不为空且n在合理范围内  
        if (chineseString == null || chineseString.isEmpty() || n <= 0 || n > chineseString.length()) {  
            throw new IllegalArgumentException("Invalid input");  
        }  
  
        // 将字符串中的每个汉字作为元素添加到列表中  
        List<Character> charList = new ArrayList<>();  
        for (int i = 0; i < chineseString.length(); i++) {  
            char c = chineseString.charAt(i);  
            // 假设我们只处理汉字，忽略其他字符（如标点、空格等）  
            if (Character.isIdeographic(c)) {  
                charList.add(c);  
            }  
        }  
  
        // 如果n大于汉字的数量，则只取汉字的数量  
        if (n > charList.size()) {  
            n = charList.size();  
        }  
  
        // 打乱列表  
        Collections.shuffle(charList);  
  
        // 提取前n个汉字  
        StringBuilder result = new StringBuilder();  
        for (int i = 0; i < n; i++) {  
            result.append(charList.get(i));  
        }  
  
        return result.toString();  
    }  
  
    public static void main(String[] args) {  
        String chineseString = "你好世界，这是一个测试字符串。";  
        int n = 4;  
        String randomChars = getRandomChineseChars(chineseString, n);  
        System.out.println(randomChars);  
    }  
}