package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV3
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class TestV3 {
    public static void target(int i){}

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV3");
        Method method = klass.getMethod("target", int.class);

        Object[] arg = new Object[1];//在循环外构造参数数组
        arg[0] = 128;

        long cur = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i ++) {
            if (i % 100_000_000 == 0) {
                long tem = System.currentTimeMillis();
                System.out.println(tem - cur);
                cur = tem;
            }
            method.invoke(null,arg);
        }
    }
}
