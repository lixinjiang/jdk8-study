package cn.lxj.jvm40courses.course19;

/**
 * FooV1
 * description TODO
 * create class by lxj 2018/11/1
 **/
public class FooV1 {
    public void foo1() {
        Object o = new Object();
    }

    public static boolean bar() {
        return false;
    }

    public void foo2() {
        bar();
    }

    public void foo3() {
        for (int i = 1_0_0; i > 0; i -- ) {

        }
    }

    public static int bar(int i) {
        return ((i + 1) - 2) * 3 / 4;
    }
}
