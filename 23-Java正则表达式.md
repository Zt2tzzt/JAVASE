# Java 正则表达式

在 Java 中，经常需要验证字符串的格式，

- 例如：年龄必须是 2 位的数字；用户名必须是 8 位长度而且只能包含大小写字母、数字。

这个时候，就要用到正则表达式，它可用来：

- 验证字符串是否满足特定的规则。
- 在一段文本中，匹配（查找）满足要求的内容。

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

1. `[abc]`：代表 a 或 b 或 c 字符中的一个。
2. `[^abc]`：代表除 a, b, c 以外的任何字符。
3. `[a-z]`：代表 a-z 的所有小写字符中的一个。
4. `[A-Z]`：代表 A-Z 的所有大写字符中的一个。
5. `[0-9]`：代表 0-9 之间的某一个数字字符。
6. `[a-zA-Z0-9]`：代表 a-z 或者 A-Z 或者 0-9 之间的任意一个字符。
7. `[a-dm-p]`：a 到 d 或 m 到 p 之间的任意一个字符。

`String` 类的 `matches` 方法，判断是否与正则表达式匹配，匹配返回 `true` 否则返回 `false`。

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
        System.out.println("zz".matches("[^abc]")); // false
        System.out.println("zz".matches("[^abc][^abc]")); // true

        // a 到 z；A 到 Z（包括头尾的范围）
        System.out.println("-----------3-------------");
        System.out.println("a".matches("[a-zA-Z]")); // true
        System.out.println("z".matches("[a-zA-Z]")); // true
        System.out.println("aa".matches("[a-zA-Z]")); // false
        System.out.println("zz".matches("[a-zA-Z]")); // false
        System.out.println("zz".matches("[a-zA-Z][a-zA-Z]")); // true
        System.out.println("0".matches("[a-zA-Z]")); // false
        System.out.println("0".matches("[a-zA-Z0-9]")); // true


        // [a-d[m-p]] a 到 d，或 m 到 p
        System.out.println("-----------4-------------");
        System.out.println("a".matches("[a-d[m-p]]")); // true
        System.out.println("d".matches("[a-d[m-p]]")); // true
        System.out.println("m".matches("[a-d[m-p]]")); // true
        System.out.println("p".matches("[a-d[m-p]]")); // true
        System.out.println("e".matches("[a-d[m-p]]")); // false
        System.out.println("0".matches("[a-d[m-p]]")); // false

        // [a-z&&[def]] a-z 和 def 的交集为: d，e，f
        System.out.println("----------5------------");
        System.out.println("a".matches("[a-z&[def]]")); // false（一个 & 号再正则表达式中没有任何含义）
        System.out.println("d".matches("[a-z&&[def]]")); // true
        System.out.println("0".matches("[a-z&&[def]]")); // false

        // [a-z&&[^bc]] a-z 和非 bc 的交集。(等同于[ad-z])
        System.out.println("-----------6------------_");
        System.out.println("a".matches("[a-z&&[^bc]]")); // true
        System.out.println("b".matches("[a-z&&[^bc]]")); // false
        System.out.println("0".matches("[a-z&&[^bc]]")); // false

        // [a-z&&[^m-p]] a 到 z 和除了 m 到 p 的交集。(等同于[a-1q-z])
        System.out.println("-----------7-------------");
        System.out.println("a".matches("[a-z&&[^m-p]]")); // true
        System.out.println("m".matches("[a-z&&[^m-p]]")); // false
        System.out.println("0".matches("[a-z&&[^m-p]]")); // false
    }
}
```

## 二、正则表达式-逻辑运算符

语法示例：

1. `&&`：并且；
2. `|`：或者；

```java
public class Demo {
   public static void main(String[] args) {
      String str = "had";

      // 1.要求字符串不是小写辅音字符开头，后跟 ad
      String regex = "[a-z&&[^aeiou]]ad";
     
      System.out.println("1." + str.matches(regex));

      // 2.要求字符串是 aeiou 中的某个字符开头，后跟 ad
      regex = "[a|e|i|o|u]ad"; // 这种写法相当于：regex = "[aeiou]ad";
     
      System.out.println("2." + str.matches(regex));
   }
}
```

> 在 Java 的正则表达式中 `\` 表示转义字符。用于 Java 中的特殊字符转义，比如双引号 `"`

## 三、正则表达式-预定义字符

语法示例：

1. `.`： 匹配任何字符（不能匹配 `\n` 回车符号）；
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
      // \\d 只能是任意的一位数字
      // 简单来记，在 Java 中两个 \ 表示一个 \
      System.out.println("a".matches("\\d")); // false
      System.out.println("3".matches("\\d")); // true
      System.out.println("333".matches("\\d")); // false

      // \\w 只能是一位单词字符，等同于 [a-zA-Z_0-9]
      System.out.println("z".matches("\\w")); // true
      System.out.println("2".matches("\\w")); // true
      System.out.println("21".matches("\\w")); // false
      System.out.println("你".matches("\\w")); // false

      // 非单词字符
      System.out.println("你".matches("\\W")); // true
      System.out.println("---------------------------------------------");
      // 以上正则匹配只能校验单个字符。

      // 必须是数字 字母 下划线 至少 6位
      System.out.println("2442fsfsf".matches("\\w{6,}")); // true
      System.out.println("244f".matches("\\w{6,}")); // false

      // 必须是数字和字符 必须是 4 位
      System.out.println("23dF".matches("[a-zA-Z0-9]{4}")); // true
      System.out.println("23 F".matches("[a-zA-Z0-9]{4}")); // false
      System.out.println("23dF".matches("[\\w&&[^_]]{4}")); // true
      System.out.println("23_F".matches("[\\w&&[^_]]{4}")); // false
   }
}
```

> Java 中，`\\` 就表示 `\`，前面的 `\` 用来转义。

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
      System.out.println("2442fsfsf".matches("\\w{6,}")); // true
      System.out.println("244f".matches("\\w{6,}")); // false

      // 必须是数字和字符 必须是 4 位
      System.out.println("23dF".matches("[a-zA-Z0-9]{4}")); // true
      System.out.println("23 F".matches("[a-zA-Z0-9]{4}")); // false
      System.out.println("23dF".matches("[\\w&&[^_]]{4}")); // true
      System.out.println("23_F".matches("[\\w&&[^_]]{4}")); // false
   }
}
```

案例理解：

需求：请编写正则表达式，验证用户名是否满足要求。

要求：大小写字母，数字，下划线一共 4-16 位；

请编写正则表达式，验证身份证号码是否满足要求。

- 简单要求：18 位，前 17 位任意数字，最后一位可以是数字可以是大写或小写的 x
- 复杂要求：按照身份证号码的格式严格要求。

```java
String regexp1 = "^[a-zA-Z0-9_]{4,16}$";

String regexp2 = "^[0-9]{17}[\\dXx]$";

String regexp3 = "^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dXx]$";
```

## 五、正则表达式-忽略大小写

忽略大小写，比如匹配的时候忽略 abc 的大小写：`"(?i)abc"`

```java
//(?i) ：表示忽略后面数据的大小写
// 忽略 abc 的大小写
String regex = "(?i)abc";

// a 需要一模一样，忽略 bc 的大小写
String regex = "a(?i)bc";

// ac 需要一模一样，忽略 b 的大小写
String regex = "a((?i)b)c";
```

## 六、正则表达式-阶段总结

`[]`，表示里面的内容出现一次。

`()`，用于分组。

## 七、正则表达式-爬虫

使用正则表达式爬虫在一段文本中，匹配（查找）满足要求的内容的功能，实现爬虫。爬虫分为本地爬虫、网络爬虫。

### 1.Java Pattern 类、Matcher 类

在 Java 中：

- `Pattern` 类，表示正则表达式；
- `Matcher` 类，表示文本匹配器，利用正则表达式的规则，从头读取字符串，并获取子字符串。

#### 1.Pattern 类 complie 静态方法

在 Java 中，获取一个正则表达式的对象，要调用 `Pattern` 类的静态方法 `compile`

```java
Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,16}$");
```

#### 2.Pattern 类 matcher 方法

在 Java 中，获取一个文本匹配器对象，可以调用 `Pattern` 实例对象的 `matcher` 方法：

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo02 {
    public static void main(String[] args) {
        String str = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        Pattern pattern = Pattern.compile("Java\\d{0,2}");

        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
```

- `matcher` 是文本匹配器对象。
- `str` 是字符串。
- `pattern` 是正则表达式对象。

#### 3.Matcher 类 find 方法

`matcher.find()` 表示使用文本匹配器，从头开始读取，寻找是否有满足规则的子字符串。

- 如果有，返回 `true`，并在底层，记录子字符串的`起始索引`，以及`结束索引 + 1`。
- 如果没有，返回 `false`。

#### 4.Matcher 类 group 方法

`matcher.group()` 表示根据 `find` 方法记录的索引，返回截取的子字符串。

`matcher` 实例对象要在 `str` 字符串中找符合 `pattern` 规则的子字符串。

### 1.Java 网络爬虫模拟

案例理解：把链接:`https://m.sengzan.com/jiaoyu/29104.html?ivksa=1025883i` 中所有的身份证号码都爬取出来

```java
package com.kkcf.regexp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo03 {
    public static void main(String[] args) throws IOException {
        // 创建一个 URL 对象
        URL url = new URL("https://m.sengzan.com/jiaoyu/29104.html?ivk sa=1025883i");

        // 连接上这个网址（保证网络是畅通）
        URLConnection conn = url.openConnection();

        // 创建一个缓冲字符输入流对象，读取网络中的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;

        // 获取正则表达式的对象 pattern
        String regex = "[1-9]\\d{17}";
        Pattern pattern = Pattern.compile(regex); // 在读取的时候每次读一整行

        while ((line = br.readLine()) != null) {
            // 拿着文本匹配器的对象 matcher 按照 pattern 的规则去读取当前的这一行信息
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }

        br.close();
    }
}
```

### 2.Java 本地爬虫模拟

需求：把下面文本中的座机电话，邮箱，手机号，热线都爬取出来。

```txt
来黑毛驴程序员学习Java，手机号:18512516758，18512508907或者联系邮箱:boniu@itcast.cn，座机电话:01036517895，010-98951256邮箱:bozai@itcast.cn，热线电话:400-618-9090 ，400-618-4000，4006184000，4006189090手机号的正则表达式:1[3-9]\d{9}
```

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo04.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo04 {
    public static void main(String[] args) {
        /*
            手机号的正则表达式:1[3-9]\d{9}
            邮箱的正则表达式:\w+@[\w&&[^_]]{2,6}(\.[a-zA-Z]{2,3}){1,2}
            座机电话的正则表达式:θ\d{2,3}-?[1-9]\d{4,9}
            热线电话的正则表达式:400-?[1-9]\\d{2}-?[1-9]\\d{3}
        */

        String s = "来黑毛驴程序员学习Java，" +
                "电话:18512516758，18512508907" + "或者联系邮箱:boniu@itcast.cn，" +
                "座机电话:01036517895，010-98951256" + "邮箱:bozai@itcast.cn，" +
                "热线电话:400-618-9090 ，400-618-4000，4006184000，4006189090";

        String regex = "(1[3-9]\\d{9})" +
                "|(\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2})" +
                "|(0\\d{2,3}-?[1-9]\\d{4,9})" +
                "|(400-?[1-9]\\d{2}-?[1-9]\\d{3})";

        // 1.获取正则表达式的对象
        Pattern p = Pattern.compile(regex);

        // 2.获取文本匹配器的对象
        // 利用 m 去读取 s，会按照 p 的规则找里面的子字符串
        Matcher m = p.matcher(s);

        // 3.利用循环获取每一个数据
        while (m.find()) {
            String str = m.group();
            System.out.println(str);
        }
    }
}
```

### 3.Java 有条件的爬取

案例理解：

需求：有如下文本，按要求爬取数据。

```txt
Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台。
```

需求1：爬取版本号为 8、11、17 的 Java 文本，但是只要 "Java"，不显示版本号。

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo05.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo05 {
    public static void main(String[] args) {
        String s = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
                "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        // 1.定义正则表达式:
        String regex = "((?i)Java)(?=8|11|17)";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
```

正则表达式中 `?=` 说明

- `?` 表示为前面的数据 Java
- `=` 表示在 Java 后面要跟随的数据，但是在获取的时候，只获取 `?` 代表的前半部分。

需求2：爬取版本号为 8，11，17 的 Java 文本。正确爬取结果为：Java8、Java11、Java17、Java17

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo05.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo05 {
    public static void main(String[] args) {
        String s = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
                "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        // 1.定义正则表达式

        //需求2，以下两种写法都可以
        String regex1 = "((?i)Java)(8|11|17)";
        String regex2 = "((?i)Java)(?:8|11|17)";

        Pattern p = Pattern.compile(regex2);

        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
```

正则表达式中 `?:` 说明：

- `?` 理解为前面的数据 `Java`
- `:` 表示在 `Java` 后面要跟随的数据。  

需求3：爬取除了版本号为 8，11，17 的 Java 文本。

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo05.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo05 {
    public static void main(String[] args) {
        String s = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
                "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        // 1.定义正则表达式
        String regex1 = "((?i)Java)(?!8|11|17)";

        Pattern p = Pattern.compile(regex1);

        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
```

正则表达式中 `?!` 说明：

- `?` 理解为前面的数据 `Java`
- `!` 表示在 `Java` 后面不能跟随的数据，

### 6.Java 贪婪爬取、非贪婪爬取

在爬取时，尽可能多的获取数据，称为贪婪爬取（默认的方式）。

- 数量词 `+` 号，表示贪婪匹配；

在爬取时，尽可能少的获取数据，称为非贪婪爬取。

- 数量词 `+` 号后面，加 `?` 号表示非贪婪爬取。

案例理解：在字符串 `"abbbbbbbbbbbbaaaaaaaaaaaaa"` 中，获取 `"ab"` 字符串，并尽可能多的获取 `b`。使用贪婪爬取。

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo06.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo06 {
    public static void main(String[] args) {
        String s = "abbbbbbbbbbbbaaaaaaaaaaaaaaaaaa";

        String regex = "ab+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group()); // abbbbbbbbbbbb
        }
    }
}
```

获取 `"ab"` 字符串。使用非贪婪爬取。

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo06.java

```java
package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo06 {
    public static void main(String[] args) {
        String s = "abbbbbbbbbbbbaaaaaaaaaaaaaaaaaa";

        String regex = "ab+?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group()); // ab
        }
    }
}
```

## 八、正则表达式-字符串方法中的使用

在 `String` 类中，有以下方法，涉及到正则表达式：

| 方法名                                                  | 说明                                                         |
| ------------------------------------------------------- | ------------------------------------------------------------ |
| `public boolean matches(String regex)`                  | 判断字符串是否满足正则表达式的规则。                         |
| `public String[] split(String regex)`                   | 将当前字符串中匹配 regex 正则表达式的符号作为"分隔符"，来切割字符串。 |
| `public String replaceAll(String regex, String newStr)` | 可以将当前字符串中匹配 regex 正则表达式的字符串替换为 newStr。 |

### 1.String 类 replaceAll 方法

`String` 类的 `replaceAll` 方法的使用：

案例理解：有一段字符串：`"小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠"`

要求：把字符串中三个中文名之间的字母，替换为 `"vs"`；

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo07.java

```java
package com.kkcf.regexp;

public class RegexDemo07 {
    public static void main(String[] args) {
        String s = "小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠";

        String result1 = s.replaceAll("[\\w&&[^_]]+", "vs");

        System.out.println(result1); // 小诗诗vs小丹丹vs小惠惠
    }
}
```

- `replaceAll` 方法，在底层也会创建 matcher 文本解析器的对象；
- 然后从头开始，去读取字符串中的内容，只要有满足的，那么就用第一个参数去替换。

### 2String 类 split 方法

`String` 类的 `split` 方法，结合正则表达式的使用：

案例理解：有一段字符串：`"小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠"`

要求：把字符串中的三个姓名切割出来

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo07.java

```java
package com.kkcf.regexp;

public class RegexDemo07 {
    public static void main(String[] args) {
        String s = "小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠";

        String[] arr = s.split("[\\w&&[^_]]+");

        for (String string : arr) {
            System.out.print(string + " ");
        }
    }
}
```

## 九、正则表达式-分组

正则表达式中，`()` 表示分组。

正则表达式中，每个分组，是有组号的，也就是序号。

- 组号从 1 开始，连续不间断。
- 以左括号为基准（嵌套关系不影响组号的顺序），最左边的是第 1 组；其次是第 2 组；依此类推……

案例理解：

需求 1：判断一个字符串的开始字符和结束字符是否一致？只考虑一个字符。

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo08.java

```java
package com.kkcf.regexp;

public class RegexDemo08 {
    public static void main(String[] args) {
        String regex1 = "(.).+\\1";

        System.out.println("a123a".matches(regex1)); // true
        System.out.println("b456b".matches(regex1)); // true
        System.out.println("17891".matches(regex1)); // true
        System.out.println("&abc&".matches(regex1)); // true
        System.out.println("a123b".matches(regex1)); // false
    }
}
```

- `\\1` 表示第 1 组，用于把正则表达式第 1 组的内容再拿出来用一次。

需求 2：判断一个字符串的开始部分和结束部分是否一致？可以有多个字符

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo08.java

```java
package com.kkcf.regexp;

public class RegexDemo08 {
    public static void main(String[] args) {
        String regex2 = "(.+).+\\1";

        System.out.println("abc123abc".matches(regex2)); // true
        System.out.println("b456b".matches(regex2)); // true
        System.out.println("123789123".matches(regex2)); // true
        System.out.println("&!@abc&!@".matches(regex2)); // true
        System.out.println("abc123abd".matches(regex2)); // false
    }
}
```

需求 3：判断一个字符串的开始部分和结束部分是否一致？开始部分内部每个字符也需要一致

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo08.java

```java
package com.kkcf.regexp;

public class RegexDemo08 {
    public static void main(String[] args) {
        String regex3 = "((.)\\2*).+\\1";

        System.out.println("aaa123aaa".matches(regex3));
        System.out.println("bbb456bbb".matches(regex3));
        System.out.println("111789111".matches(regex3));
        System.out.println("&&abc&&".matches(regex3));
        System.out.println("aaa123aab".matches(regex3));
    }
}
```

- `(.)`，把首字母看做一组。
- `\\2`，把首字母拿出来再次使用。
- `*`，作用于 `\\2`，表示后面重复的内容出现零次或多次。

### 1.Java 捕获分组

正则表达式内部，获取分组，使用 `\\组号` 的方式。

正则表达式外部，获取分组，使用 `$组号` 的方式。

案例理解：将字符串 `"我要学学编编编编程程程程程程"`，替换为 `"我要学编程"`

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo09.java

```java
package com.kkcf.regexp;

public class RegexDemo09 {
    public static void main(String[] args) {
        String str = "我要学学编编编编程程程程程程";

        String result = str.replaceAll("(.)\\1+", "$1");

        System.out.println(result);
    }
}
```

- `(.)`，表示把重复内容的第一个字符看做一组；
- `\\1`，表示第 1 组（第一个字符）再次出现；
- `+`，表示出现至少一次；
- `$1`，表示把正则表达式中第一组的内容，再拿出来用。

### 2.Java 非捕获分组

正则表达式中，分组后，不需要再用本组数据，仅仅是把数据括起来，称为非捕获分组，它**不占用组号**。

非捕获分组，有如下表示形式：

| 符号   | 含义                       | 举例              |
| ------ | -------------------------- | ----------------- |
| `(?:)` | 获取符号后所有             | java(?:8\|11\|17) |
| `(?=)` | 获取符号前面部分             | java(?=8\|11\|17) |
| `(?!)` | 获取不是指定内容的符号前面部分 | java(?!8\|11\|17) |

理解下方代码报错原因：

demo-project/base-code/Day18/src/com/kkcf/regexp/RegexDemo10.java

```java
package com.kkcf.regexp;

public class RegexDemo10 {
    public static void main(String[] args) {
        String regex2 ="[1-9]\\d{16}(\\d Xx)\\1";
        String regex3 ="[1-9]\\d{16}(?:\\d Xx)\\1"; // 编译报错，?: 表示非捕获分组，不计算组号

        System.out.println("41080119930228457x".matches(regex3));
    }
}
```

## 五、Java 和 JavaScript 的正则表达式

Java 和 JavaScript 都支持正则表达式，并且它们的基础语法非常相似，因为两者都遵循类似的正则表达式标准。

然而，它们在如何使用和一些高级特性上存在差异。以下是一些主要的区别：

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
- Java 使用 `Matcher.replaceAll(pattern, replacement)` 或 `Matcher.replaceFirst(replacement)` 方法，其中 `replacement` 是一个字符串。

### 5.匹配和搜索

- JavaScript 提供了 `test()` 和 `exec()` 方法在 `RegExp` 对象中，分别用于测试和执行匹配。
- Java 使用 `Matcher.find()`, `Matcher.lookingAt()`, `Matcher.matches()` 等方法来查找和验证模式。

### 6.性能和优化

- JavaScript 的正则表达式性能可能受到运行环境的影响，而 Java 的正则表达式引擎通常更注重性能优化。

### 7.其他特性

- Java 支持更多的正则表达式特性，如原子组、前瞻断言、后瞻断言等。
