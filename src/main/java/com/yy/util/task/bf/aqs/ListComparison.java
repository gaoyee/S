package com.yy.util.task.bf.aqs;

import java.util.Arrays;
import java.util.List;
 
public class ListComparison {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<Integer> list3 = Arrays.asList(3, 2, 1);
 
        boolean areListsEqual = list1.equals(list2); // 返回 true
        boolean areListsNotEqual = list1.equals(list3); // 返回 false
 
        System.out.println("List1 and List2 are equal: " + areListsEqual);
        System.out.println("List1 and List3 are equal: " + areListsNotEqual);
    }
}