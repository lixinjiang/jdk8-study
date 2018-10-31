package cn.lxj.jvm40courses.course8_1;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * FooV2
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class FooV2 {
    public static void bar(Object o){
        new Exception().printStackTrace();
    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle bar = lookup.findStatic(FooV2.class, "bar", t);
        bar.invokeExact(new Object());
    }
}
