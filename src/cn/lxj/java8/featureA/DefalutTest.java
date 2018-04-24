package cn.lxj.java8.featureA;

/**
 * DefalutTest 接口的默认方法
 * description  在接口中新增了default方法和static方法，这两种方法可以有方法体
 *              ，即static修饰的有方法体的方法不会被继承或者实现，但是静态变量会被实现
 * create by lxj 2018/4/23
 **/
public interface DefalutTest {

    default void defaultMethod(){
        System.out.println("DefaultTest default method");
    }

    // final静态变量
    static int a = 5;
    
    int sub(int a, int b);

    static void staticMethod(){
        System.out.println("DefaultTest default method ");
    }
}
