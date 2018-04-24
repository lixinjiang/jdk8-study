package cn.lxj.java8.featureH;

import cn.lxj.java8.featureB.LambdaClassTest;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * MainTestConsumer
 * description 测试Consumer接口
 * create by lxj 2018/4/24
 **/
public class MainTestConsumer {
    public static void main(String[] args) {
        Supplier<LambdaClassTest> personSupplier = LambdaClassTest::new;
//        Consumer<LambdaClassTest> greeter = (lt) -> System.out.println("Hello, " + lt.getTest());
//        greeter.accept(personSupplier.get());
    }
}
