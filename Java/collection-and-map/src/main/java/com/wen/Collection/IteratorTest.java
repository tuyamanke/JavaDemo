package com.wen.Collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wen on 2018/7/8
 */
public class IteratorTest {
    private List a = new ArrayList();

    @Before
    public void initList(){
        a.add("good");a.add("good");a.add("good");a.add("better");a.add("best");a.add(null);
    }

    @Test
    public void iteratorTest(){
        //获取集合对象对应的迭代器
        Iterator iterator = a.iterator();
        while(iterator.hasNext()){
            // iterator.next()返回的是Object类型，因此需要强制类型转换
            String str = (String) iterator.next();
            System.out.println(str);
            if ("better".equals(str)){
                //从集合对象中删除上一次next方法返回的元素
                iterator.remove();
            }
            //对str变量赋值，不会改变集合对象的元素本身
            str = "bad";
        }
        System.out.println(a);
    }


}
