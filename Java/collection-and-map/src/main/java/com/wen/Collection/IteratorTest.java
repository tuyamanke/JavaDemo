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
        a.add("good");a.add("good");a.add("good");a.add("better");a.add("best");
    }

    @Test
    public void iteratorTest(){
        //获取集合对象对应的迭代器
        Iterator iterator = a.iterator();
        /**
         * 因为例子中执行a.remove("better")或a.remove(str)后，移除了a的倒数第二个有效元素，
         * 原来的倒数第一往前移了一位，占据了原来倒数第二的位置，致使原来倒数第一的位置不在size范围内了（不在有效范围内），
         * 而此时iterator的cursor又正好指向原来倒数第一的首地址，故hasNext()方法返回false，跳出了循环，没有执行next()方法中对iterator的expectedModCount和a的modCount的比对，故没有抛出异常，
         * 但显然没有输出原来的倒数第一，所以结果也不符合预期
         */
        while(iterator.hasNext()){
            // iterator.next()返回的是Object类型，因此需要强制类型转换
            String str = (String) iterator.next();
            System.out.println(str);
            if ("better".equals(str)){
                //从集合对象中删除上一次next方法返回的元素
                //iterator.remove();
                a.remove("better");
            }
            //对str变量赋值，不会改变集合对象的元素本身
            str = "bad";
        }
        System.out.println(a);
    }

    /**
     * 使用Iterator对象的forEachRemaining()方法遍历集合
     */
    @Test
    public void forEachRemainingTest(){
        //获取集合对象对应的迭代器
        Iterator iterator = a.iterator();
        iterator.forEachRemaining(obj -> {
            /**
             * 因为每次循环都会比较modCount和expectedModCount是否相等，
             * 所以不存在只移除倒数第二个元素不抛异常的问题，移除任何一个元素都会抛异常，因为没有hasNext()方法判断是否跳出循环
             */
            if ("better".equals(obj)){
                a.remove("better");
            }
            System.out.println(obj);
        });
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

    /**
     * 使用for循环的forEach()方法，底层是用Iterator对象进行遍历的
     */
    @Test
    public void forEachTest02(){
        for (Object obj : a){
            System.out.println(obj);
            if ("better".equals(obj)){
                /**
                 * 下面代码可能会引发java.util.ConcurrentModificationException异常
                 * 因为for循环的forEach()方法底层是用Iterator对象进行遍历的，每次循环都会比较modCount和expectedModCount是否相等，
                 * 所以不存在只移除倒数第二个元素不抛异常的问题，移除任何一个元素都会抛异常，因为没有hasNext()方法判断是否跳出循环
                 */
                a.remove("better");
            }
        }
        System.out.println(a);
    }

}
