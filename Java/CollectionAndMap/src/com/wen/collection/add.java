package com.wen.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 * boolean add(Object o)：向调用该方法的集合对象里添加一个元素，若调用该方法的集合对象被添加操作改变了，返回true
 */
public class add {
    public static void main(String[] args) {
        List a = new ArrayList();
        //将指定元素添加到a中
        a.add("good");
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        System.out.println("Collection a: " + a);
    }
}
