package cn.lxj.java36courses.course15;

//import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockDemo
 * description 重入锁Demo，尽量写入到try-catch-finally，典型的代码结构
 * create by lxj 2018/8/10
 **/
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock fairLock = new ReentrantLock(true);// 使用重入锁，创建公平锁，一般不需要
        fairLock.lock();    // 每一lock（）动作，都要带一个释放锁，不要讲锁的获取卸载try内，这样如果抛出了异常，也会导致锁无法释放
        try {
//            Condition condition = fairLock.newCondition();// 该condition可以保证在同一锁的情况下可以根据不同情况执行等待或唤醒动作
//            condition.await();
//            condition.notify();
//            condition.notifyAll();
            System.out.println("重入锁的公平锁，Demo");
        } catch (Exception e) {

        } finally {
            fairLock.unlock();
        }
    }
}