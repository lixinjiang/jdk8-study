package cn.lxj.jvm40courses.course14;

/**
 * TestV1
 * description TODO
 * create class by lxj 2018/10/31
 **/
public class TestV1 {
    public void foo1(Object lock) {
        // 代码块
        synchronized (lock) {
            lock.hashCode();
        }
    }

    public synchronized void foo2(Object lock) {
        lock.hashCode();
    }
}