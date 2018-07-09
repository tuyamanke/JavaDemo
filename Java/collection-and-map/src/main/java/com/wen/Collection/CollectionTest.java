package com.wen.Collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/8
 *
 * 测试Collection接口的add、addAll、contains、containsAll、remove、removeAll、retainAll方法
 * 因为Set、List、Queue接口都继承自Collection接口，故Collection接口的这些方法可用于操作这些类型的集合对象
 */
public class CollectionTest {
    private List a = new ArrayList();
    private List b = new ArrayList();

    @Before
    public void initList(){
        a.add("good");a.add("good");a.add("good");a.add("better");a.add("best");
        b.add("good");b.add("good");b.add("bad");b.add("worse");b.add("worst");
        System.out.println("a: " + a);System.out.println("b: " + b);
    }

    /**
     * a.addAll(b): 把b中的所有元素添加到a中
     */
    @Test
    public void addAllTest(){
        a.addAll(b);
        System.out.println("a.addAll(b): " + a);
    }

    /**
     * a.contains("good"): 判断a中是否存在"good"
     */
    @Test
    public void containsTest(){
        System.out.println("a.contains(\"good\"): " + a.contains("good"));
    }

    /**
     * a.containsAll(b): 判断b中的所有元素是否都属于a，判断每个元素是否属于a用contains方法
     */
    @Test
    public void containsAllTest(){
        System.out.println("a.containsAll(b): " + a.containsAll(b));
    }

    /**
     * a.remove("good"): 只删除a的第一个"good"
     */
    @Test
    public void removeTest(){
        a.remove("good");
        System.out.println("a.remove(\"good\"): " + a);
    }

    /**
     * 删除a中所有属于b的元素，判断每个元素是否属于b用contains方法
     */
    @Test
    public void removeAllTest(){
        a.removeAll(b);
        System.out.println("a.removeAll(b): " + a);
    }

    /**
     * 删除a中所有不属于b的元素，判断每个元素是否属于b用contains方法
     */
    @Test
    public void retainAllTest(){
        a.retainAll(b);
        System.out.println("a.retainAll(b): " + a);
    }
}
