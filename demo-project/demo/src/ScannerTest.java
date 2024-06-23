// 1.导报
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        // 2.创建对象
        Scanner sc = new Scanner(System.in);

        // 3.键盘录入
        System.out.println("请输入一个整数：");

        int num1 = sc.nextInt();

        System.out.println("请输入二个整数：");

        int num2 = sc.nextInt();

        System.out.println("两数之和为：" + (num1 + num2));

        sc.close();
    }
}
