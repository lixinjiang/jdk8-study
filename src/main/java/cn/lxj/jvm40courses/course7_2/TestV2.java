package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV2
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class TestV2 {
    public static void target(int i){}

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV2");
        Method method = klass.getMethod("target", int.class);
        long cur = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i ++) {
            if (i % 100_000_000 == 0) {
                long tem = System.currentTimeMillis();
                System.out.println(tem - cur);
                cur = tem;
            }
            method.invoke(null,128);
        }
    }
}
