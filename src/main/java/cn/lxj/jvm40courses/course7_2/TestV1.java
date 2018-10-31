package cn.lxj.jvm40courses.course7_2;

import java.lang.reflect.Method;

/**
 * TestV1
 * description TODO
 * 使用 -verbose加载打印类
 * create class by lxj 2018/10/29
 **/
public class TestV1 {
    public static void target(int i){
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("cn.lxj.jvm40courses.course7_2.TestV0");
        Method method = klass.getMethod("target", int.class);
        for (int i =0; i < 20; i ++) {
            method.invoke(null, i);
        }
    }
}
