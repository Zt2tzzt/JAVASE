# Java 正则表达式

在 Java 中，经常需要验证字符串的格式，

- 例如：年龄必须是 2 位的数字；
- 用户名必须是 8 位长度而且只能包含大小写字母、数字。

正则表达式可用来：

- 验证字符串是否满足特定的规则。
- 在一段文本中，查找满足要求的内容。

案例理解：验证 QQ 号码，必须是 5--15 位长度；而且必须全部是数字；而且首位不能为 0；

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexpDemo01.java

```java
package com.kkcf.regexp;

public class RegexpDemo01 {
    public static void main(String[] args) {
        String qqStr = "123e456";

        boolean flag = qqStr.matches("[1-9]\\d{4,14}");
        System.out.println(flag); // false
    }
}
```

> 在 Java 中，被 `^` 和 `$` 包裹的正则表达式，表示从开头匹配到末尾。
>
> `String` 类里的 `matches` 方法，已经有了这个含义了。

## 一、正则表达式-字符类

语法示例：

1. `[abc]`：代表 a 或者 b 或者 c 字符中的一个。
2. `[^abc]`：代表除 a, b, c 以外的任何字符。
3. `[a-z]`：代表 a-z 的所有小写字符中的一个。
4. `[A-Z]`：代表 A-Z 的所有大写字符中的一个。
5. `[0-9]`：代表 0-9 之间的某一个数字字符。
6. `[a-zA-Z0-9]`：代表 a-z 或者 A-Z 或者 0-9 之间的任意一个字符。
7. `[a-dm-p]`：a 到 d 或 m 到 p 之间的任意一个字符。

`String` 类的 `public boolean matches(String regex)` 方法，判断是否与正则表达式匹配，匹配返回 true

```java
package com.itheima.a08regexdemo;

public class RegexDemo2 {
    public static void main(String[] args) {
        // 只能是 a b c
        System.out.println("-----------1-------------");
        System.out.println("a".matches("[abc]")); // true
        System.out.println("z".matches("[abc]")); // false

        // 不能出现 a b c
        System.out.println("-----------2-------------");
        System.out.println("a".matches("[^abc]")); // false
        System.out.println("z".matches("[^abc]")); // true
        System.out.println("zz".matches("[^abc]")); //false
        System.out.println("zz".matches("[^abc][^abc]")); //true

        // a 到 z；A 到 Z（包括头尾的范围）
        System.out.println("-----------3-------------");
        System.out.println("a".matches("[a-zA-z]")); // true
        System.out.println("z".matches("[a-zA-z]")); // true
        System.out.println("aa".matches("[a-zA-z]"));//false
        System.out.println("zz".matches("[a-zA-Z]")); //false
        System.out.println("zz".matches("[a-zA-Z][a-zA-Z]")); //true
        System.out.println("0".matches("[a-zA-Z]"));//false
        System.out.println("0".matches("[a-zA-Z0-9]"));//true


        // [a-d[m-p]] a 到 d，或 m 到 p
        System.out.println("-----------4-------------");
        System.out.println("a".matches("[a-d[m-p]]"));//true
        System.out.println("d".matches("[a-d[m-p]]")); //true
        System.out.println("m".matches("[a-d[m-p]]")); //true
        System.out.println("p".matches("[a-d[m-p]]")); //true
        System.out.println("e".matches("[a-d[m-p]]")); //false
        System.out.println("0".matches("[a-d[m-p]]")); //false

        // [a-z&&[def]] a-z 和 def 的交集为: d，e，f
        System.out.println("----------5------------");
        System.out.println("a".matches("[a-z&[def]]")); //false
        System.out.println("d".matches("[a-z&&[def]]")); //true
        System.out.println("0".matches("[a-z&&[def]]")); //false

        // [a-z&&[^bc]] a-z和非bc的交集。(等同于[ad-z])
        System.out.println("-----------6------------_");
        System.out.println("a".matches("[a-z&&[^bc]]"));//true
        System.out.println("b".matches("[a-z&&[^bc]]")); //false
        System.out.println("0".matches("[a-z&&[^bc]]")); //false

        // [a-z&&[^m-p]] a到z和除了m到p的交集。(等同于[a-1q-z])
        System.out.println("-----------7-------------");
        System.out.println("a".matches("[a-z&&[^m-p]]")); //true
        System.out.println("m".matches("[a-z&&[^m-p]]")); //false
        System.out.println("0".matches("[a-z&&[^m-p]]")); //false
    }
}
```

## 二、正则表达式-逻辑运算符

语法示例：

1. `&&`：并且；
2. `|`：或者；
3. `\`：转义字符。

```java
public class Demo {
   public static void main(String[] args) {
      String str = "had";

      //1.要求字符串是小写辅音字符开头，后跟 ad
      String regex = "[a-z&&[^aeiou]]ad";
      System.out.println("1." + str.matches(regex));

      //2.要求字符串是 aeiou 中的某个字符开头，后跟 ad
      regex = "[a|e|i|o|u]ad";//这种写法相当于：regex = "[aeiou]ad";
      System.out.println("2." + str.matches(regex));
   }
}
```

## 三、正则表达式-预定义字符

语法示例：

1. `.`： 匹配任何字符。
2. `\d`：任何数字 `[0-9]` 的简写；
3. `\D`：任何非数字 `[^0-9]` 的简写；
4. `\s`： 空白字符：`[\t\n\x0B\f\r]` 的简写
5. `\S`： 非空白字符：`[^\s]` 的简写
6. `\w`：单词字符：`[a-zA-Z_0-9]` 的简写
7. `\W`：非单词字符：`[^\w]`

```java
public class Demo {
   public static void main(String[] args) {
      //.表示任意一个字符
      System.out.println("你".matches("..")); //false
      System.out.println("你".matches(".")); //true
      System.out.println("你a".matches(".."));//true

      // \\d 表示任意的一个数字
      // \\d只能是任意的一位数字
      // 简单来记:两个\表示一个\
      System.out.println("a".matches("\\d")); // false
      System.out.println("3".matches("\\d")); // true
      System.out.println("333".matches("\\d")); // false

      //\\w只能是一位单词字符[a-zA-Z_0-9]
      System.out.println("z".matches("\\w")); // true
      System.out.println("2".matches("\\w")); // true
      System.out.println("21".matches("\\w")); // false
      System.out.println("你".matches("\\w"));//false

      // 非单词字符
      System.out.println("你".matches("\\W")); // true
      System.out.println("---------------------------------------------");
      // 以上正则匹配只能校验单个字符。


      // 必须是数字 字母 下划线 至少 6位
      System.out.println("2442fsfsf".matches("\\w{6,}"));//true
      System.out.println("244f".matches("\\w{6,}"));//false

      // 必须是数字和字符 必须是4位
      System.out.println("23dF".matches("[a-zA-Z0-9]{4}"));//true
      System.out.println("23 F".matches("[a-zA-Z0-9]{4}"));//false
      System.out.println("23dF".matches("[\\w&&[^_]]{4}"));//true
      System.out.println("23_F".matches("[\\w&&[^_]]{4}"));//false
   }
}
```

> Java 中，`\` 表示转义字符，用于 Java 中的特殊字符，比如双引号 `"`
>
> Java 中，`\\` 就表示 `\`，前面的 `\` 用来转义

## 四、正则表达式-数量词

语法示例：

1. `X?`：0 次或 1 次
2. `X*`：0 次到多次
3. `X+`：1 次或多次
4. `X{n}`：恰好 n 次
5. `X{n,}`：至少 n 次
6. `X{n,m}`：n 到 m 次（n 和 m 都是包含的）

```java
public class Demo {
   public static void main(String[] args) {
      // 必须是数字 字母 下划线 至少 6位
      System.out.println("2442fsfsf".matches("\\w{6,}"));//true
      System.out.println("244f".matches("\\w{6,}"));//false

      // 必须是数字和字符 必须是4位
      System.out.println("23dF".matches("[a-zA-Z0-9]{4}"));//true
      System.out.println("23 F".matches("[a-zA-Z0-9]{4}"));//false
      System.out.println("23dF".matches("[\\w&&[^_]]{4}"));//true
      System.out.println("23_F".matches("[\\w&&[^_]]{4}"));//false
   }
}
```

Java 和 JavaScript 都支持正则表达式，并且它们的基础语法非常相似，因为两者都遵循类似的正则表达式标准。然而，它们在如何使用和一些高级特性上存在差异。以下是一些主要的区别：

## 五、Java 和 JavaScript 的正则表达式

### 1.创建方式

JavaScript 创建正则表达式：

- 使用字面量方式创建正则表达式，例如：`/pattern/flags`
- 或者使用 `new RegExp('pattern', 'flags')` 构造函数。

Java 创建正则表达式：

- 必须使用 `Pattern.compile("pattern")` 方法来编译正则表达式。

### 2.特殊标志

- JavaScript 支持全局 (`g`)、忽略大小写 (`i`)、多行 (`m`)、粘附 (`y`) 和 Unicode (`u`) 标志，直接在正则表达式字面量中指定。
- Java 也支持类似的功能，但是通过不同的方法调用来实现，例如 `Pattern.CASE_INSENSITIVE`，`Pattern.MULTILINE` 等。

### 3.分组引用

- JavaScript 中，分组捕获的结果可以通过 `RegExp` 对象的 `exec()` 方法返回的数组的索引访问，或者通过 `$1`, `$2` 等在字符串替换中引用。
- Java 中，分组捕获的结果通过 `Matcher` 类的 `group(n)` 方法获取，其中 `n` 是组编号。

### 4.替换

- JavaScript 使用 `string.replace(/pattern/, replacement)` 方法进行替换，其中 `replacement` 可以是一个字符串或一个函数。
- Java 使用 `Matcher.replaceAll(replacement)` 或 `Matcher.replaceFirst(replacement)` 方法，其中 `replacement` 是一个字符串。

### 5.匹配和搜索

- JavaScript 提供了 `test()` 和 `exec()` 方法在 `RegExp` 对象中，分别用于测试和执行匹配。
- Java 使用 `Matcher.find()`, `Matcher.lookingAt()`, `Matcher.matches()` 等方法来查找和验证模式。

### 6.性能和优化

- JavaScript 的正则表达式性能可能受到运行环境的影响，而 Java 的正则表达式引擎通常更注重性能优化。

### 7.其他特性

- Java 支持更多的正则表达式特性，如原子组、前瞻断言、后瞻断言等。
