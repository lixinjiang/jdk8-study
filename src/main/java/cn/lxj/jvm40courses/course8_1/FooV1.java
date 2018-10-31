package cn.lxj.jvm40courses.course8_1;

import cn.lxj.jvm40courses.course6.Foo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * FooV1
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class FooV1 {
    public static void bar(Object o){}
    public static MethodHandles.Lookup lookup(){
        return MethodHandles.lookup();
    }

    public static void main(String[] args) throws Exception {
        // 获取方法句柄的不同方式
        MethodHandles.Lookup l = FooV1.lookup();//具备Foo的访问权限
        Method m = Foo.class.getDeclaredMethod("bar", Object.class);
        MethodHandle mh0 = l.unreflect(m);

        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle mh1 = l.findStatic(Foo.class, "bar", t);
    }
}
