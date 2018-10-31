package cn.lxj.jvm40courses.course8_2;

import java.util.function.IntConsumer;

/**
 * TestV1
 * description TODO
 * create class by lxj 2018/10/30
 **/
public class TestV1 {
    private static void target(int i){}

    public static void main(String[] args) {
        long cur = System.currentTimeMillis();
        for (int i =0; i <= 2_000_000_000; i ++) {
            if (i % 100_000_000 == 0) {
                long tem = System.currentTimeMillis();
                System.out.println(tem - cur);
                cur = tem;
            }

//            ((IntConsumer) TestV1::target).accept(128);
//            target(128);
            ((IntConsumer) j -> TestV1.target(j)).accept(128);
        }
    }
}
