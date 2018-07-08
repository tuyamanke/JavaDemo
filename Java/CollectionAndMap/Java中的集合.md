Collection接口是List、Set、Queue接口的父接口，该接口里的方法可操作List、Set、Queue集合。

这里的集合和高中数学里的集合还是有区别的，高中数学里集合的三要素：

- 确定性
- 互异性
- 无序性

满足这三个要素的Java集合类型是Set，而List、Queue都可以有重复元素（不满足互异性）。

判断某个元素是否属于集合对象时用contains方法，只要集合对象中存在一个该元素（List、Queue都可以有重复元素）就返回true。

Collection接口是Set、List、Queue接口的父接口，故该接口里的方法可以操作Set、List、Queue集合对象。

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