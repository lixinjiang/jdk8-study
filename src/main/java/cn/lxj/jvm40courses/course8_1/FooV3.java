package cn.lxj.jvm40courses.course8_1;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * FooV3
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class FooV3 {
    public void bar(Object o){}

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        MethodHandle bar = lookup.findVirtual(FooV3.class, "bar", methodType);
        long cur = System.currentTimeMillis();
        for (int i =1; i < 2_000_000_000; i ++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - cur);
                cur = temp;
            }
            bar.invokeExact(new FooV3(), new Object());
        }
    }
}
