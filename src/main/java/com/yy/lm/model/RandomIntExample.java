package com.yy.lm.model;

import java.util.Random;
  
public class RandomIntExample {  
  
    /**  
     * 生成一个指定范围内的随机整数（包含范围的开始和结束）  
     *   
     * @param min 最小值（包含）  
     * @param max 最大值（包含）  
     * @return 指定范围内的随机整数  
     */  
    public static int getRandomInt(int min, int max) {  
        // 创建一个Random实例  
        Random rand = new Random();  
          
        // 返回一个介于 [min, max] 的随机整数  
        // 注意：nextInt(int bound) 返回的是一个从 0（包含）到指定值 bound（不包含）的随机整数  
        // 因此，我们需要通过计算将范围调整到 [min, max]  
        return rand.nextInt((max - min) + 1) + min;  
    }  
  
    public static void main(String[] args) {  
        // 示例：生成一个介于 1 到 10 之间的随机整数  
        int randomInt = getRandomInt(1, 10);  
        System.out.println("随机整数: " + randomInt);  
    }  
}