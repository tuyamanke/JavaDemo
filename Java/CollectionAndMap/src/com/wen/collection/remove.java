package com.wen.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wen on 2018/7/7
 *
 * Collection接口是List、Set、Queue接口的父接口
 * 该接口里的方法可操作List、Set、Queue集合
 * 这里的集合和高中数学里的集合还是有区别的，高中数学里集合的三要素：确定性、互异性、无序性，满足这三个要素的Java集合类型是Set，而List、Queue都可以有重复元素（不满足互异性）
 * boolean remove(Object o)：删除集合中的指定元素o，当集合中有一个或多个元素o时，该方法只删除第一个符合条件的元素，删除成功后该方法返回true
 */
public class remove {
    public static void main(String[] args) {
        Collection a = new ArrayList();
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("better");
        ((ArrayList) a).add("best");
        System.out.println("Collection a: " + a);
        //只删除a中的第一个"good"
        a.remove("good");
        System.out.println("Collection a after remove: " + a);
    }
}
