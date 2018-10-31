package cn.lxj.java36courses.course16;

/**
 * MonitorTest
 * description TODO
 * create by lxj 2018/8/15
 **/
public class MonitorTest {
    int count;// 计数器

    synchronized void bump() {
        count++;
    }
    static int classCount;

    static synchronized void classBump() {
        classCount ++;
    }
}
