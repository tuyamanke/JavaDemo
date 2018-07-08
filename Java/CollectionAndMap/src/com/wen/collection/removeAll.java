package com.wen.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wen on 2018/7/7
 *
 * boolean removeAll(Collection b)：从调用该方法的集合中删除所有属于集合c的元素，相当于调用该方法的集合减集合c，如果删除了一个或一个以上的元素，则该方法返回true
 */
public class removeAll {
    public static void main(String[] args) {
        Collection a = new ArrayList();
        Collection b = new ArrayList();
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("good");
        ((ArrayList) a).add("better");
        ((ArrayList) a).add("best");
        ((ArrayList) b).add("good");
        ((ArrayList) b).add("bad");
        ((ArrayList) b).add("worse");
        ((ArrayList) b).add("worst");
        System.out.println("Collection a: " + a);
        System.out.println("Collection b: " + b);
        //删除集合a中所有属于集合b的元素
        a.removeAll(b);
        System.out.println("Collection a after removeAll: " + a);
    }
}
