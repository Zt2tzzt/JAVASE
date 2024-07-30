# Java 常见 API 集合进阶 HashMap 原码

## 一、HashMap 源码

`HashMap` 中的 `Node` 内部类，用于表示存储在哈希表 table 数组中的链表结点。

`Node` 内部类，实现了 `Entry` 接口，所以 Node 对象，也被称为 Entry 对象。

`HashMap` 类中的成员变量 `next` 记录了链表结构中，下一个元素的地址值。

java/util/HashMap.java

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;

        return o instanceof Map.Entry<?, ?> e
                && Objects.equals(key, e.getKey())
                && Objects.equals(value, e.getValue());
    }
}
```

`HashMap` 中的 `TreeNode` 内部类，表示存储在哈希表 table 数组中的红黑树结点。

`TreeNode` 内部类，也实现了 `Entry` 接口；

java/util/HashMap.java

```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;

    // ……
}
```

`HashMap` 底层的数组 `table`：

java/util/HashMap.java

```java
transient Node<K,V>[] table;
```

`table` 默认的长度是 `16`

java/util/HashMap.java

```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```

`table` 默认的加载因子是 `0.75`

java/util/HashMap.java

```java
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

`table` 数组的最大容量

java/util/HashMap.java

```java
static final int MAXIMUM_CAPACITY = 1 << 30;
```

使用空参构造，创建 `HashMap` 集合对象：

- 数组 `table` 默认长度为 `0`；
- 加载因子 `loadFactor` 设为 `0.75`；

java/util/HashMap.java

```java
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
```

当在 `HashMap` 集合对象中，存入元素时，数组 `table` 才初始化；

java/util/HashMap.java

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

- 可见，`HashMap` 集合的唯一性仅与键相关，与值无关。
- `putVal` 方法，
  - 参数一：键的哈希值；
  - 参数二：键；
  - 参数三：值；
  - 参数四：如果键重复了，是否保留；
    - 传入 `false`，表示老元素保留，不会覆盖。
    - 传入 `true`，表示老元素不保留，会被覆盖。

HashMap 中 putVal 方法解读：

```java
//参数一：键的哈希值
//参数二：键
//参数三：值
//参数四：如果键重复了是否保留
// true，表示老元素的值保留，不会覆盖
// false，表示老元素的值不保留，会进行覆盖
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) {
    //定义一个局部变量，用来记录哈希表中数组的地址值。
    Node<K,V>[] tab;

    //临时的第三方变量，用来记录键值对对象的地址值
    Node<K,V> p;

    //表示当前数组的长度
    int n;

    //表示索引
    int i;

    //把哈希表中数组的地址值，赋值给局部变量tab
    tab = table;

    if (tab == null || (n = tab.length) == 0){
        //1.如果当前是第一次添加数据，底层会创建一个默认长度为 16，加载因子为 0.75 的数组
        //2.如果不是第一次添加数据，会看数组中的元素是否达到了扩容的条件
        //如果没有达到扩容条件，底层不会做任何操作
        //如果达到了扩容条件，底层会把数组扩容为原先的两倍，并把数据全部转移到新的哈希表中
        tab = resize();
        //表示把当前数组的长度赋值给n
        n = tab.length;
    }

    //拿着数组的长度跟键的哈希值进行计算，计算出当前键值对对象，在数组中应存入的位置
    i = (n - 1) & hash;//index
    //获取数组中对应元素的数据
    p = tab[i];


    if (p == null){
        //底层会创建一个键值对对象，直接放到数组当中
        tab[i] = newNode(hash, key, value, null);
    }else {
        Node<K,V> e;
        K k;

        //等号的左边：数组中键值对的哈希值
        //等号的右边：当前要添加键值对的哈希值
        //如果键不一样，此时返回false
        //如果键一样，返回true
        boolean b1 = p.hash == hash;

        if (b1 && ((k = p.key) == key || (key != null && key.equals(k)))){
            e = p;
        } else if (p instanceof TreeNode){
            //判断数组中获取出来的键值对是不是红黑树中的节点
            //如果是，则调用方法putTreeVal，把当前的节点按照红黑树的规则添加到树当中。
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        } else {
            //如果从数组中获取出来的键值对不是红黑树中的节点
            //表示此时下面挂的是链表
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    //此时就会创建一个新的节点，挂在下面形成链表
                    p.next = newNode(hash, key, value, null);
                    //判断当前链表长度是否超过8，如果超过8，就会调用方法treeifyBin
                    //treeifyBin方法的底层还会继续判断
                    //判断数组的长度是否大于等于64
                    //如果同时满足这两个条件，就会把这个链表转成红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1)
                        treeifyBin(tab, hash);
                    break;
                }
                //e：0x0044  ddd  444
                //要添加的元素： 0x0055   ddd   555
                //如果哈希值一样，就会调用equals方法比较内部的属性值是否相同
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
                        break;
                }

                p = e;
            }
        }

        //如果e为null，表示当前不需要覆盖任何元素
        //如果e不为null，表示当前的键是一样的，值会被覆盖
        //e:0x0044  ddd  555
        //要添加的元素： 0x0055   ddd   555
        if (e != null) {
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null){

                //等号的右边：当前要添加的值
                //等号的左边：0x0044的值
                e.value = value;
            }
            afterNodeAccess(e);
            return oldValue;
        }
    }

    //threshold：记录的就是数组的长度 * 0.75，哈希表的扩容时机  16 * 0.75 = 12
    if (++size > threshold){
            resize();
    }

    //表示当前没有覆盖任何元素，返回null
    return null;
}
```

## 二、TreeMap 源码

`TreeMap` 集合中，每一个元素，实际上就是一个 `Entry` 对象；

`TreeMap` 集合类中，有 `Entry` 内部类；

java/util/TreeMap.java

```java
static final class Entry<K,V> implements Map.Entry<K,V> {
    K key;
    V value;
    Entry<K,V> left;
    Entry<K,V> right;
    Entry<K,V> parent;
    boolean color = BLACK;
}
```

- `key` 是键
- `value` 是值
- `left` 是左子结点；
- `right` 是右子结点
- `parent` 是父结点
- `color` 是

`TreeMap` 集合类中，有如下部分成员变量：

```java
private final Comparator<? super K> comparator;

private transient Entry<K,V> root;

private transient int size = 0;
```

- `comparator` 表示比较器，用于指定比较规则。
- `root` 表示红黑树的根节点；
- `size` 表示红黑树中的节点个数。

`TreeMap` 集合类的空参构造：

java/util/TreeMap.java

```java
public TreeMap() {
    comparator = null;
}
```

- 表示没有比较器对象。

`TreeMap` 集合类的带参构造，传递自定义的比较器：

java/util/TreeMap.java

```java
public TreeMap(Comparator<? super K> comparator) {
    this.comparator = comparator;
}
```

`TreeMap` 集合添加元素，执行 `put` 方法：

java/util/TreeMap.java

```java
参数一：键
参数二：值
参数三：当键重复的时候，是否需要覆盖值
  true：覆盖
  false：不覆盖

private V put(K key, V value, boolean replaceOld) {
    //获取根节点的地址值，赋值给局部变量t
    Entry<K,V> t = root;
    //判断根节点是否为null
    //如果为null，表示当前是第一次添加，会把当前要添加的元素，当做根节点
    //如果不为null，表示当前不是第一次添加，跳过这个判断继续执行下面的代码
    if (t == null) {
        //方法的底层，会创建一个Entry对象，把他当做根节点
        addEntryToEmptyMap(key, value);
        //表示此时没有覆盖任何的元素
        return null;
    }
    //表示两个元素的键比较之后的结果
    int cmp;
    //表示当前要添加节点的父节点
    Entry<K,V> parent;

    //表示当前的比较规则
    //如果我们是采取默认的自然排序，那么此时comparator记录的是null，cpr记录的也是null
    //如果我们是采取比较去排序方式，那么此时comparator记录的是就是比较器
    Comparator<? super K> cpr = comparator;
    //表示判断当前是否有比较器对象
    //如果传递了比较器对象，就执行if里面的代码，此时以比较器的规则为准
    //如果没有传递比较器对象，就执行else里面的代码，此时以自然排序的规则为准
    if (cpr != null) {
        do {
            parent = t;
            cmp = cpr.compare(key, t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else {
                V oldValue = t.value;
                if (replaceOld || oldValue == null) {
                    t.value = value;
                }
                return oldValue;
            }
        } while (t != null);
    } else {
      //把键进行强转，强转成Comparable类型的
      //要求：键必须要实现Comparable接口，如果没有实现这个接口
      //此时在强转的时候，就会报错。
      Comparable<? super K> k = (Comparable<? super K>) key;
      do {
          //把根节点当做当前节点的父节点
          parent = t;
          //调用compareTo方法，比较根节点和当前要添加节点的大小关系
          cmp = k.compareTo(t.key);

          if (cmp < 0)
          //如果比较的结果为负数
          //那么继续到根节点的左边去找
              t = t.left;
          else if (cmp > 0)
          //如果比较的结果为正数
          //那么继续到根节点的右边去找
              t = t.right;
          else {
          //如果比较的结果为0，会覆盖
              V oldValue = t.value;
              if (replaceOld || oldValue == null) {
                  t.value = value;
              }
              return oldValue;
          }
      } while (t != null);
    }
    //就会把当前节点按照指定的规则进行添加
    addEntry(key, value, parent, cmp < 0);
    return null;
}



private void addEntry(K key, V value, Entry<K, V> parent, boolean addToLeft) {
      Entry<K,V> e = new Entry<>(key, value, parent);
      if (addToLeft)
          parent.left = e;
      else
          parent.right = e;
      //添加完毕之后，需要按照红黑树的规则进行调整
      fixAfterInsertion(e);
      size++;
      modCount++;
  }



private void fixAfterInsertion(Entry<K,V> x) {
    //因为红黑树的节点默认就是红色的
    x.color = RED;

    //按照红黑规则进行调整

    //parentOf:获取x的父节点
    //parentOf(parentOf(x)):获取x的爷爷节点
    //leftOf:获取左子节点
    while (x != null && x != root && x.parent.color == RED) {
        //判断当前节点的父节点是爷爷节点的左子节点还是右子节点
        //目的：为了获取当前节点的叔叔节点
        if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            //表示当前节点的父节点是爷爷节点的左子节点
            //那么下面就可以用rightOf获取到当前节点的叔叔节点
            Entry<K,V> y = rightOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                //叔叔节点为红色的处理方案

                //把父节点设置为黑色
                setColor(parentOf(x), BLACK);
                //把叔叔节点设置为黑色
                setColor(y, BLACK);
                //把爷爷节点设置为红色
                setColor(parentOf(parentOf(x)), RED);

                //把爷爷节点设置为当前节点
                x = parentOf(parentOf(x));
            } else {
                //叔叔节点为黑色的处理方案
                //表示判断当前节点是否为父节点的右子节点
                if (x == rightOf(parentOf(x))) {
                    //表示当前节点是父节点的右子节点
                    x = parentOf(x);
                    //左旋
                    rotateLeft(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateRight(parentOf(parentOf(x)));
            }
        } else {
            //表示当前节点的父节点是爷爷节点的右子节点
            //那么下面就可以用leftOf获取到当前节点的叔叔节点
            Entry<K,V> y = leftOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                setColor(parentOf(x), BLACK);
                setColor(y, BLACK);
                setColor(parentOf(parentOf(x)), RED);
                x = parentOf(parentOf(x));
            } else {
                if (x == leftOf(parentOf(x))) {
                    x = parentOf(x);
                    rotateRight(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateLeft(parentOf(parentOf(x)));
            }
        }
    }

    //把根节点设置为黑色
    root.color = BLACK;
}
```

## 三、面试题分析

### 1.面试题一

面试题一：`TreeMap` 添加元素的时候，键是否需要重写 `hashCode` 和 `equals` 方法？

答：不需要，TreeMap 底层红黑树会对元素键进行比较，如果值相同，则覆盖。

### 2.面试题二

面试题二：`HashMap` 是哈希表结构的，JDK8 开始由数组，链表，红黑树组成的。

既然有红黑树结构，`HashMap` 的键，是否需要实现 `Compareable` 接口或者传递比较器对象呢？

答：不需要。因为在 `HashMap` 的底层，默认是利用哈希值的大小关系，来创建红黑树的。

### 3.面试题三

面试题三：`TreeMap` 和 `HashMap` 谁的效率更高？

如果是 `HashMap` 的最坏情况，即添加了 8 个元素，这 8 个元素在哈希表中的一个桶里形成了链表；

此时改用 `TreeMap` 的效率要更高。

但是这种情况出现的几率非常小。一般而言，还是 `HashMap` 的效率要更高。

### 4.面试题四

面试题四：你觉得在 `Map` 系列集合中，java 会提供一个如果键重复了，不会覆盖的 `put` 方法吗？

答：有，该方法是 `putIfAbsent` 方法，它本身不重要。主要传递一个思想：

- 代码中的逻辑都有两面性，如果我们只知道了其中的 A 面，而且代码中还发现了有变量可以控制两面性的发生。那么该逻辑一定会有B 面。一般习惯：
  - boolean 类型的变量控制只有 A、B 两面的情况，因为 boolean 只有两个值。
  - int 类型的变量控制至少有三面的情况，因为 int 可以取多个值。

### 5.面试题五

面试题五：三种双列集合 `HashMap`、`LinkedHashMap`、`TreeMap` 如何选择？

- 默认：`HashMap`（效率最高）；
- 如果要保证存、取有序：`LinkedHashMap`；
- 如果要进行大小排序：`TreeMap`。
