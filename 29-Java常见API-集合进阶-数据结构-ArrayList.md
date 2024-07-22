# Java 常见 API 集合进阶之数据结构、ArrayList

## 一、Java 数据结构

数据结构，就是计算机存储，组织数据的方式。

不同的业务场景，要使用不同的数据结构，提高操作（存储、获取）数据的效率。

数据结构的三个要素：

- 每种数据结构长什么样子？
- 如何添加数据？
- 如何删除数据？

### 1.Java 栈结构

栈结构的特点是：先进后出，后进先出。

数据进入栈的过程，称为“压栈”；数据退出栈的过程，称为“弹栈”；

JVM 虚拟机中的栈内存，就是一种栈结构。

### 2.Java 队列结构

队列结构的特点是：先进先出，后进后出。

数据进入队列的过程，称为“入队列”；数据离开队列的过程，称为“出队列”

### 3.Java 数组结构

Java 中数组，在内存中是一片连续的空间，

数组中元素的查询，速度快：

- 通过数组地址值和元素索引直接定位的，查询任意位置的元素，耗时相同。

数组中元素的删除，效率低：

- 要将元素删除的同时，还要将后面所有的元素，向前移一位。

数组中元素的添加，效率低：

- 要将元素添加到指定索引，还要将该索引后面所有的元素，向后移一位。
- 如果添加元素之前，数组已经存满，那么还要创建新数组。

所以，数组是查询快，增、删慢的数据结构。

### 4.Java 链表结构

链表结构中的每一个元素，称为一个结点。

链表中的结点是独立对象，在内存中是不连续的，每个结点包含数据值和下一个结点的地址值。

![链表](NodeAssets/链表.jpg)

链表中元素的查询，效率低：

- 无论查询哪个元素，都要从头开始查找。

链表中的元素的删除、添加，速度快：

- 只要修改插入节点上一个结点记录的地址值，并将插入节点中保存的地址值，指向下一个结点即可。

在链表的基础上，还可以拓展双向链表的概念，即一个结点中，存储了数据值，前结点地址值、后结点地址值的结点。

当查询链表中第 n 个结点时，先判断该结点离头结点近，还是离尾结点近；再从近的一端开始挨个查找。查找效率稍微提升了。

![双向链表](NodeAssets/双向链表.jpg)

## 二、ArrayList 底层原理

ArrayList 底层使用数组来实现。类中的成员 `size` 表示容器的长度，也表示下一个元素要插入的位置。

ArrayList 中，不但有 `add` 方法，还有 `addAll` 方法，用于一次性添加很多元素。在容器扩容时要考虑这种情况。

ArrayList 空参构造创建的集合对象：

1. 会在底层创建一个默认长度为 0 的数组 `elementData`；
2. 当添加一个元素时，底层会创建一个新的长度为 10 的数组。
3. 当数组存满时，会扩容 1.5 倍。并把原数组中的元素拷贝过来。
4. 如果一次添加多个元素，1.5 倍还放不下，则新创建数组的长度以实际为准。

3、4 步源码分析：

jdk/internal/util/ArraysSupport.java

```java
public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
    // preconditions not checked because of inlining
    // assert oldLength >= 0
    // assert minGrowth > 0

    int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
    if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
        return prefLength;
    } else {
        // put code cold in a separate method
        return hugeLength(oldLength, minGrowth);
    }
}
```

## 三、LinkedList 底层原理

LinkedList 底层数据结构是双向链表，查询慢，增删快，但是如果操作的是首尾元素，速度也是极快的。

LinkedList 本身多了很多直接操作首尾元素的特有 API。如下方所示：

| 方法名                      | 说明                             |
| --------------------------- | -------------------------------- |
| `public void addFirst(E e)` | 在该列表开头插入指定的元素       |
| `public void addLast(E e)`  | 将指定的元素追加到此列表的末尾   |
| `public E getFirst()`       | 返回此列表中的第一个元素         |
| `public E getLast()`        | 返回此列表中的最后一个元素       |
| `public E removeFirst()`    | 从此列表中删除并返回第一个元素   |
| `public E removeLast()`     | 从此列表中删除并返回最后一个元素 |
