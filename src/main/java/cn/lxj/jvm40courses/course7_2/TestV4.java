package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV4
 * description
 * 添加两个虚拟参数：
 * -Djava.lang.Integer.IntegerCache.high=128
 * -Dsun.reflect.noInflation=true
 * create class by lxj 2018/10/29
 **/
public class TestV4 {
    public static void target(int i){}

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV4");
        Method method = klass.getMethod("target", int.class);
        method.setAccessible(true);//关闭权限检查

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
