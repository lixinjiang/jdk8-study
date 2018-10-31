package cn.lxj.java36courses.course16;

/**
 * BumpTest
 * description TODO
 * create by lxj 2018/8/15
 **/
public class BumpTest {
    int count;

    void bump() {
        synchronized (this) {
            count++;
        }
    }

    static int classCount;

    static void classBump() {
        try {
            synchronized (Class.forName("BumpTest")) {
                classCount++;
            }
        } catch (Exception e) {

        }
    }
}