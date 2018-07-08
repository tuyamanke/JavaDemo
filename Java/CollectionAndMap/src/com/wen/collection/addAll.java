package com.wen.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 * boolean addAll(Collection c)：把集合对象c里的所有元素添加到调用该方法的集合对象中，若调用该方法的集合对象被添加操作改变了，返回true
 */
public class addAll {
    public static void main(String[] args) {
        List a = new ArrayList();
        List b = new ArrayList();
        a.add("good");
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        b.add("good");
        b.add("bad");
        b.add("worse");
        b.add("worst");
        System.out.println("Collection a: " + a);
        System.out.println("Collection b: " + b);
        //把b中的所有元素添加到a中
        a.addAll(b);
        System.out.println("Collection a after addAll(b): " + a);
    }
}