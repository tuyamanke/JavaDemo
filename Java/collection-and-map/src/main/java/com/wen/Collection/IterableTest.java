package com.wen.Collection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/7/8
 *
 * Iterable接口是Collection接口的父接口，因此Collection集合对象可以直接调用Iterable接口中的方法
 */
public class IterableTest {
    private List a = new ArrayList();

    @Before
    public void initList(){
        a.add("good");a.add("good");a.add("good");a.add("better");a.add("best");
    }

    /**
     * 使用集合对象的forEach()方法遍历集合，
     * 该forEach()方法是实现的Iterable接口的抽象方法，但它的实现依然用了Iterator对象，依然会比较modCount和expectedModCount是否相等
     * modCount和expectedModCount不想等就抛出java.util.ConcurrentModificationException异常
     */
    @Test
    public void forEachTest(){
        a.forEach(obj -> {
            /**
             * 因为每次循环都会比较modCount和expectedModCount是否相等，
             * 所以不存在只移除倒数第二个元素不抛异常的问题，移除任何一个元素都会抛异常，因为没有hasNext()方法判断是否跳出循环
             */
            if ("better".equals(obj)){
                a.remove("better");
            }
            System.out.println(obj);
        });
        System.out.println(a);
    }

}
