package com.wen.collection;

import java.util.*;

/**
 * Created by wen on 2018/7/7
 *
 * boolean removeAll(Collection b)：从调用该方法的集合中删除所有属于集合c的元素，相当于调用该方法的集合减集合c，如果删除了一个或一个以上的元素，则该方法返回true
 */
public class removeAll {
    public static void main(String[] args) {
        List a = new ArrayList();
        List b = new ArrayList();
        a.add("good");
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        b.add("good");
        b.add("good");
        b.add("bad");
        b.add("worse");
        b.add("worst");
        System.out.println("Collection a: " + a);
        System.out.println("Collection b: " + b);
        //删除a中所有属于a且属于b的元素，判断是否属于用contains方法
        a.removeAll(b);
        System.out.println("Collection a after removeAll: " + a);
    }
}
