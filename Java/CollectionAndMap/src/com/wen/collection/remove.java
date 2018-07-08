package com.wen.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/7
 *
 *
 * boolean remove(Object o)：删除集合中的指定元素o，当集合中有一个或多个元素o时，该方法只删除第一个符合条件的元素，删除成功后该方法返回true
 */
public class remove {
    public static void main(String[] args) {
        List a = new ArrayList();
        a.add("good");
        a.add("good");
        a.add("good");
        a.add("better");
        a.add("best");
        System.out.println("Collection a: " + a);
        //只删除a的第一个"good"
        a.remove("good");
        System.out.println("Collection a after remove(\"good\"): " + a);
    }
}
