##集合相关类图

Java的集合类主要由两个接口派生而出：Collection、Map，二者都在java.util包下。

Collection接口下派生出Set、List、Queue接口；

Java集合大致可以分为Set、List、Queue、Map四种体系。

- Set代表无序、不可重复的集合；
- List代表有序、可重复的集合；
- Queue是Java5增加的集合体系，代表一种队列集合实现。
- Map代表具有映射关系的集合；

集合和数组不同，数组元素既可以是基本类型的值，也可以是对象（实际上保存的是对象的引用变量）；而集合里只能保存对象（实际上保存的是对象的引用变量）。

![](25-Java中的集合.assets/01.png)

![](25-Java中的集合.assets/02.png)

访问方式：

- Set：元素本身
- List ：索引
- Queue：方法
- Map：Key

常用实现类：

- Set：HashSet、TreeSet
- List：ArrayList、LinkedList
- Queue：ArrayDeque
- Map：HashMap、TreeMap

## Collection接口

**Collection接口是Set、List、Queue接口的父接口**，故该接口里的方法可以操作Set、List、Queue集合对象。

```java
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
```



- boolean add(Object o)：向调用该方法的集合对象里添加一个元素，若集合对象被添加操作改变了，返回true；
- boolean addAll(Collection c)：把集合对象c里的所有元素添加到调用该方法的集合对象中，若调用该方法的集合对象被添加操作改变了，返回true；
- void clear()：清除调用该方法的集合对象里的所有元素，将集合对象的长度变为0；
- boolean contains(Object o)：返回调用该方法的集合对象里是否存在元素o；
- boolean containsAll(Collection c)：返回调用该方法的集合对象里是否存在集合对象c里的所有元素，具体判断每个元素是否属于某个集合对象时用contains方法；
- boolean isEmpty()：返回调用该方法的集合对象是否为空，长度为0返回true，否则返回fasle；
- Iterator iterator()：返回一个Iterator对象，用于遍历调用该方法的集合对象里的元素；
- boolean remove(Object o)：删除调用该方法的集合对象中的指定元素o，当集合对象中存在一个或多个元素o时（List中可以存在重复元素），该方法只删除第一个符合条件的元素，删除成功后返回true；
- boolean removeAll(Collection c)：从调用该方法的集合对象中删除所有属于集合对象c的元素，如果删除了一个或一个以上的元素，返回true，具体判断每个元素是否属于某个集合对象时用contains方法；
- boolean retainAll(Collection c)：从调用该方法的集合对象中删除所有不属于集合对象c的元素，若该操作改变了调用该方法的集合对象，则返回true，具体判断每个元素是否属于某个集合对象时用contains方法；
- int size()：返回调用该方法的集合对象里元素的个数；
- Object toArray()：把调用该方法的集合对象转换成一个数组，所有的集合元素变成对应的数组元素。

## Iterable接口

**Iterable接口是Collection接口的父接口**，Iterable接口在java.lang包下，因此Collection集合对象可以直接调用该方法，Java8为Iterable接口新增了一个forEach(Consumer action)默认方法，该方法的参数类型是一个函数式接口（可用Lambda表达式），当程序调用该方法遍历集合时，程序会依次将集合元素传递给Consumer的accept(T t)方法（该接口中唯一的抽象方法）。如：`a.forEach(obj -> System.out.print(obj + " "));`（a是一个Collection集合对象）。

```java
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
```



## Iterator接口

Iterator接口在java.util包下，与Iterable接口、Collection接口、Map接口没有父子关系。Iterator接口主要用于遍历Collection集合中的元素，Iterator对象也被称为迭代器。

Iterator的四大方法：

- boolean hasNext()：如果被迭代集合对象的元素还没有被遍历完，则返回true；
- Object next()：返回集合对象里的下一个元素，返回Object类型，一般需要强转一下；
- void remove()：删除集合对象里上一次next方法返回的元素；
- void forEachRemaining(Consumer action)：此为Java8为Iterator新增的默认方法，可用Lambda表达式来遍历集合对象中的元素。

```java
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
```

运行后输出：

```java
good
good
good
better
best
[good, good, good, best]
```



Iterator仅用于遍历集合对象，本身并不提供盛装对象的能力。创建Iterator对象必须有一个被迭代的集合对象调用iterator()方法，如`Iterator iterator = a.iterator();`。**Iterator对象必须依附于Collection集合对象，若有一个Iterator对象，则必然有一个与之关联的Collection对象**。

当使用Iterator对集合对象的元素进行迭代时，Iterator并不是把集合对象的元素本身传给了迭代变量，而是把集合对象的元素的值传给了迭代变量，所以修改迭代变量的值对集合对象的元素并无影响。

**当使用Iterator迭代访问Collection集合对象的元素时，Collection集合对象里的元素不能被改变，只有通过Iterator对象的remove()方法删除上一次next()方法返回的集合对象的元素才可以，否则引发java.util.ConcurrentModificationException异常。**

如把上面程序中的`iterator.remove();`改为：`a.remove("better");`或`a.remove(str);`输出结果为：

```java
good
good
good
better
[good, good, good, best]
```

1. 初始化：

   ArrayList集合对象a：**0→good**、**1→good**、**2→good**、**3→better**、**4→best**，5→null、6→null、7→null、8→null、9→null，a的modCount（修改次数）为5（因为往里加了5次元素），size为5（因为往a里加了5个元素），默认初始容量（Capacity）为10；

   Iterator对象iterator的cursor（当前元素指针）指向0号元素首地址，expectedModCount(期望的修改次数)为5（a.iterator()产生iterator时将modCount传过去了）；

2. 执行next()方法

   第一次执行next()方法：打印出0号元素good，iterator的cursor指针移向1号元素首地址；

   第二次执行next()方法：打印出1号元素good，iterator的cursor指针移向2号元素首地址；

   第三次执行next()方法：打印出2号元素good，iterator的cursor指针移向3号元素首地址；

   第四次执行next()方法：打印出3号元素better，iterator的cursor指针移向4号元素首地址，并且此时str符合条件"better".equals(str)，这个时候：

   - 如果执行`a.remove("better")`或`a.remove(str)`，则a发生了变化：**0→good**、**1→good**、**2→good**、**3→best**、4→null，5→null、6→null、7→null、8→null、9→null，**size变为4，modCount为6**，而此时iterator并不知道，它的cursor依然指向4号元素首地址，4号元素已经不在有效元素之内，故hasNext()方法返回false，跳出循环。故最终输出结果为：

     ```java
     good
     good
     good
     better
     [good, good, good, best]
     ```

     

   - 如果执行`iterator.remove()`，则a发生上面的变化之后，iterator也知道a发生的变化（因为是通过它来操作的嘛），它的cursor减为指向3号元素首地址，故输出best，一切正常进行。故最终输出结果为：

     ```java
     good
     good
     good
     better
     best
     [good, good, good, best]
     ```

     这里有一个疑问：**为什么没抛出java.util.ConcurrentModificationException异常？**

     因为例子中执行`a.remove("better")`或`a.remove(str)`后，移除了a的倒数第二个有效元素，原来的倒数第一往前移了一位，占据了原来倒数第二的位置，致使原来倒数第一的位置不在size范围内了（不在有效范围内），而此时iterator的cursor又正好指向原来倒数第一的首地址，故hasNext()方法返回false，跳出了循环，没有执行next()方法中对iterator的expectedModCount和a的modCount的比对，故没有抛出异常，但显然没有输出原来的倒数第一，所以结果也不符合预期。

     所以：**如何抛出java.util.ConcurrentModificationException异常？**

     知道上面的原因后，我们可以在原来程序中初始化a的代码后再加一个a.add(null)，这个null是自己加的也是有效元素（因为size变大了，要和原来初始容量中剩余空间里的null区分开），这样执行`a.remove("better")`或`a.remove(str)`后，移除了a中的倒数第三个元素，后两个往前移，而此时iterator的cursor指向原来倒数第二的首地址，存放的是原来倒数第一个元素，这个地址是有效的，hasNext()方法返回true，继续执行，当执行到iterator.next()方法时，对iterator的expectedModCount和a的modCount的比对，由于是通过a进行的移除元素，而iterator不知道，故a的modCount比iterator的expectedModCount大1，二者不相等，抛出java.util.ConcurrentModificationException异常。

## Iterator和Iterable的异同

- Iterable和Iterator都是接口；
- Collection接口继承了Iterable接口，而没继承Iterator接口；
- Iterable接口在java.lang包下，而Iterator接口在java.util包下（Collection和Map也在java.util包下）；
- Iterable接口中有个iterator()方法可以产生Iterator类型的接口。

问题来了：**Iterable接口中有个iterator()方法可以产生Iterator类型的接口？**

如上面代码中有

```java
//获取集合对象对应的迭代器
Iterator iterator = a.iterator();
```

通过Iterable接口中的iterator()方法产生Iterator对象，为什么要这么做，而不是让Collection接口直接实现Iterator接口呢？

因为Iterator接口的核心方法next()和hasNext() 是依赖于迭代器的当前迭代位置（cursor）的。 如果Collection直接实现Iterator接口，势必导致集合对象中包含当前迭代位置的指针（cursor）。 当集合在不同方法间被传递时，由于当前迭代位置不可预置，那么next()方法的结果会变成不可预知。 除非再为Iterator接口添加一个reset()方法，用来重置当前迭代位置。但即时这样，Collection也只能同时存在一个当前迭代位置，而Iterable则不然，每次调用iterator()方法都会返回一个从头开始计数的迭代器，多个迭代器是互不干扰的。 

## modCount到底是干什么的

在ArrayList、LinkedList、HashMap等等的内部实现增，删，改中我们总能看到modCount的身影，modCount字面意思就是修改次数，但为什么要记录modCount的修改次数呢？ 大家发现一个公共特点没有，所有使用modCount属性的全是线程不安全的，这是为什么呢？说明这个玩意肯定和线程安全有关系喽，那有什么关系呢？阅读源码，发现这玩意只有在本数据结构对应迭代器中才使用，以HashMap为例：

```java
abstract class HashIterator {
        Node<K,V> next;        // next entry to return
        Node<K,V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }
```

```java
public final Map.Entry<K,V> next() { return nextNode(); }
```

由以上代码可以看出，在一个迭代器初始的时候会赋予它调用这个迭代器的对象的modCount，在迭代器遍历的过程中（next()方法中），一旦发现这个对象的modCount和迭代器中存储的expectedModCount不一样那就抛异常。

**Fail-Fast机制**

我们知道java.util.HashMap不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了HashMap对象，那么将抛出ConcurrentModificationException，这就是所谓Fail-Fast机制。这一机制在源码中的实现是通过modCount域，modCount顾名思义就是修改次数，对HashMap对象内容的修改将增加这个值，那么在迭代器初始化过程中会将这个值赋给迭代器的 expectedModCount。在迭代过程中，判断modCount跟expectedModCount是否相等，如果不相等就表示已经有其他线程修改了HashMap对象，注意到modCount声明为volatile，保证线程之间修改的可见性。

所以在这里和大家建议，**当大家遍历那些非线程安全的数据结构时，尽量使用迭代器**。