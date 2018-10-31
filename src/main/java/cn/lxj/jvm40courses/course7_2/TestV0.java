package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV0
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class TestV0 {
    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV0");
        Method method = klass.getMethod("target", int.class);
        method.invoke(null, 0);
    }
}
