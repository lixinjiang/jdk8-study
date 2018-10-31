package cn.lxj.jvm40courses.course8_2;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * LambdaIml
 * description TODO
 * create class by lxj 2018/10/30
 **/
public class LambdaIml {
    public static void main(String[] args) {
        int x =1;
        IntStream intStream = IntStream.of(1, 2, 3).map(i -> i * 2).map(i -> i * x);
        System.out.println(Arrays.toString(intStream.toArray()));
    }
}
