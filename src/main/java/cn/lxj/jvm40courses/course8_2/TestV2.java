package cn.lxj.jvm40courses.course8_2;

import java.util.function.IntConsumer;

/**
 * TestV2
 * description TODO
 * create class by lxj 2018/10/30
 **/
public class TestV2 {
    private static void target(int i) {
    }

    public static void main(String[] args) {
        int x = 2;
        long cur = System.currentTimeMillis();
        for (int i = 0; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long tem = System.currentTimeMillis();
                System.out.println(tem - cur);
                cur = tem;
            }

//            ((IntConsumer) TestV2::target).accept(128);
//            target(128);
            ((IntConsumer) j -> TestV2.target(x + j)).accept(128);
        }
    }
}
