package cn.lxj.jvm40courses.course11_GC;

/**
 * SafepointTest
 * description TODO
 * create class by lxj 2018/10/30
 **/
public class SafepointTest {
    static double sum = 0;

    public static void foo() {
        for (int i = 0; i < 0x7777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
    }

    public static void main(String[] args) {
        new Thread(SafepointTest::foo).start();
//        new Thread(SafepointTest::bar).start();
//        System.out.println(Integer.toString(0x77777777));
    }
}
