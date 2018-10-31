package cn.lxj.jvm40courses.course13;

/**
 * TestV1
 * description
 * create class by lxj 2018/10/30
 **/
public class TestV1 {
    int a = 0, b = 0;

    public void method1() {
        int r2 = a;
        b = 1;
    }

    public void method2() {
        int r1 = b;
        a = 2;
    }

    @Override
    public String toString() {
        return "(" + a + "," + b + ")";
    }

    public static void main(String[] args) {
        TestV1 v1 = new TestV1();
        v1.method1();
        System.out.println(v1);
    }
}
