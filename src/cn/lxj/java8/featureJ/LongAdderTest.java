package cn.lxj.java8.featureJ;

import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdderTest
 * description TODO
 * create by lxj 2018/4/25
 **/
public class LongAdderTest {
    private static LongAdder la = new LongAdder();

    public static int a = 0;

    public static void add() {
        la.increment();
        a++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    add();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    add();
                }
            }
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println("---------la--------"+la);
        System.out.println("----------a--------"+a);
    }
}
