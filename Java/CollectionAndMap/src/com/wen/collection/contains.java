package com.wen.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 * boolean contains(Object o)：返回调用该方法的集合对象里是否存在元素o
 */
public class contains {
    public static void main(String[] args) {
        List a = new ArrayList();
        a.add("good");
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        System.out.println("Collection a: " + a);
        //判断a中是否存在"good"
        System.out.println("a contains(\"good\") : " + a.contains("good"));
    }
}
