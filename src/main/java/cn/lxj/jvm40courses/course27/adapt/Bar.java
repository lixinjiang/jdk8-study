package cn.lxj.jvm40courses.course27.adapt;

import java.util.function.IntBinaryOperator;

/**
 * Bar
 * description TODO
 * create class by lxj 2018/11/2
 **/
public class Bar {
    @Adapt(IntBinaryOperator.class)
    public static int add(int a,int b) {
        return a + b;
    }
}
