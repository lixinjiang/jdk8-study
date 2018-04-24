package cn.lxj.java8.featureH;

import java.util.function.Function;

/**
 * MainTestFunction
 * description 测试Function接口
 * Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）：
 * create by lxj 2018/4/24
 **/
public class MainTestFunction {
    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("123").getClass());
        Function<String, Object> toInteger2 = toInteger.andThen(String::valueOf);
        System.out.println(toInteger2.apply("123").getClass());
    }
}
