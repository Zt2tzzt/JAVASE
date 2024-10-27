# Java 枚举类型 enum

在 Java 中，枚举（`enum`）类型是一种特殊的类，用于表示一组固定的常量。它非常适合表示一组有限的可能值，例如星期、颜色、状态等。下面是枚举的一些基本用法：

## 一、定义枚举

可以用 `enum` 关键字来定义一个枚举类型。例如，定义一个表示星期的枚举：

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

每个枚举常量都是 `Day` 类型的一个实例，且它们都是 `public static final` 的，所以枚举类型是不可变的。

## 二、枚举的基本使用

```java
Day today = Day.MONDAY;

switch (today) {
    case MONDAY:
        System.out.println("今天是星期一！");
        break;
    case FRIDAY:
        System.out.println("今天是星期五！");
        break;
    default:
        System.out.println("今天不是周一或周五！");
}
```

## 三、给枚举添加属性和方法

可以为枚举添加属性和方法，以增加功能。例如，为星期枚举添加一个代表是否为工作日的布尔属性：

```java
public enum Day {
    MONDAY(true), TUESDAY(true), WEDNESDAY(true), THURSDAY(true), FRIDAY(true),
    SATURDAY(false), SUNDAY(false);

    private final boolean isWeekday;

    Day(boolean isWeekday) {
        this.isWeekday = isWeekday;
    }

    public boolean isWeekday() {
        return isWeekday;
    }
}
```

然后，可以像调用方法一样获取每一天是否为工作日：

```java
Day today = Day.FRIDAY;
System.out.println("Is today a weekday? " + today.isWeekday());
```

## 四、枚举的内置方法

Java 枚举类型自带一些实用的方法，例如：

- `values()`：返回所有枚举常量的数组。
- `ordinal()`：返回枚举常量的序号，从 0 开始。
- `name()`：返回枚举常量的名称。

```java
for (Day day : Day.values()) {
    System.out.println(day + " ordinal: " + day.ordinal());
}
```

## 五、枚举的使用场景

- 使用枚举来定义一组有意义的常量，避免硬编码。
- 枚举可以带属性、构造方法和方法，使其比常量更具灵活性。
- 在 switch 语句中使用枚举，代码更加清晰易懂。
