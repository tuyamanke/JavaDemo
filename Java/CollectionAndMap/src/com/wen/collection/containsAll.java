package com.wen.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 * boolean containsAll(Collection c)：返回调用该方法的集合对象里是否存在集合对象c里的所有元素；
 */
public class containsAll {
    public static void main(String[] args) {
        List a = new ArrayList();
        List b = new ArrayList();
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        b.add("good");
        b.add("good");
        b.add("good");
        b.add("better");
        System.out.println("Collection a: " + a);
        System.out.println("Collection b: " + b);
        //a.containsAll(b)：返回a中是否存在b里的所有元素，判断是否存在用contains方法
        System.out.println("Collection a containsAll(b): " + a.containsAll(b));
    }
}
