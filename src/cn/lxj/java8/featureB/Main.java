package cn.lxj.java8.featureB;

import cn.lxj.java8.featureA.DefalutTest;

/**
 * Main
 * description Lambda测试主函数
 * create by lxj 2018/4/24
 **/
public class Main {
    public static void main(String[] args) {
        // 匿名内部类，java8之前的实现方式
        DefalutTest dt1 = new DefalutTest() {
            @Override
            public int sub(int a, int b) {
                return a - b;
            }
        };

        // Lambda表达式--匿名内部类实现方式1
        DefalutTest dt2 = (a, b) -> {
            return a - b;
        };
        System.out.println(dt2.sub(2, 1));

        // Lambda表达式--省略花括号实现方式2
        DefalutTest dt3 = (a, b) -> a - b;
        System.out.println(dt3.sub(4, 5));

        // 测试final
        int c = 5;
        DefalutTest dt4 = (a, b) -> a - c;
//        c=8; //内部类中引用局部变量，后期试图修改此变量，内部类报错，强行修改，编译也无法通过
        System.out.println(dt4.sub(5, 6));

        // 无参数，并且执行语句只有一条
        LambdaTest lt1 = () -> System.out.println("测试无参");
        lt1.print();

        System.out.println("Lambda引用实例的测试***********分隔符***********");
        LambdaTest2 lt2 = s -> System.out.println(s);
        lt2.print("有一个参数");

        // 改写为 将lt3调用时的实际参数传递给了PrintStream类中的println方法，并调用该方法
        LambdaTest2 lt3 = System.out::println;
        lt3.print("实例引用方式调用！");
        System.out.println("Lambda引用测试完成***********分隔符*************");

        System.out.println("Lambda引用类方法***********分隔符***************");

    }
}