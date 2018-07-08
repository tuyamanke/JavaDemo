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
     * 使用forEach方法遍历集合
     */
    @Test
    public void forEachTest(){
        a.forEach(obj -> System.out.print(obj + " "));
    }

}
