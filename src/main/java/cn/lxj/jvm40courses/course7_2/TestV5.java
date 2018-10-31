package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV5
 * description
 * 添加两个虚拟参数：
 * -Djava.lang.Integer.IntegerCache.high=128
 * -Dsun.reflect.noInflation=true
 * create class by lxj 2018/10/29
 **/
public class TestV5 {
    public static void target(int i){}

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV5");
        Method method = klass.getMethod("target", int.class);
        method.setAccessible(true);//关闭权限检查
        polluteProfile();

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

    public static void polluteProfile() throws Exception {
        Method method1 = TestV5.class.getMethod("target1", int.class);
        Method method2 = TestV5.class.getMethod("target2", int.class);
        for (int i =0;i < 2000;i ++) {
            method1.invoke(null, 0);
            method2.invoke(null, 0);
        }
    }

    public static void target1(int i){}
    public static void target2(int i){}
}
