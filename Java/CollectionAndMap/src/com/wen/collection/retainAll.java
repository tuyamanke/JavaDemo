package com.wen.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 * boolean retainAll(Collection c)：从调用该方法的集合对象中删除集合对象c里不包含的元素，若该操作改变了调用该方法的集合对象，则返回true
 */
public class retainAll {
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
        //删除a中所有属于a但不属于b的元素，判断是否属于用contains方法
        a.retainAll(b);
        System.out.println("Collection a after retainAll(b): " + a);
    }
}
