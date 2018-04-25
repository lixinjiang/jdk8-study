package cn.lxj.java8.featureJ;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLockTest
 * description TODO
 * create by lxj 2018/4/25
 **/
public class StampedLockTest {
    private static final StampedLock sl = new StampedLock();
    private static int x;
    private static int y;

    /**
     * x,y移动，获取写锁，释放写锁
     * @param deltax
     * @param deltay
     * @throws InterruptedException
     */
    public static void move(int deltax, int deltay) throws InterruptedException {
        System.out.println("写线程----" + Thread.currentThread().getName());
        long sw = sl.writeLock();   // 获取写锁
        try {
            x = x + deltax;
            y = y + deltay;
        } finally {
            sl.unlockWrite(sw); // 释放写锁
        }
    }

    /**
     * 读取数据，获取读锁，悲观锁，读取线程
     * @return
     */
    public static int distanceFromOrigin() {
        long sr = sl.tryOptimisticRead();   // 获取乐观读锁，不会阻塞写锁
        int currentx = x;
        int currenty = y;
        // 读完后验证期间是否有写操作改变了数据sl.validate(sr)为true则表示期间无写操作，否则表示数据可能被改变
        System.out.println(currentx + "----第一次读取数据----" + currenty);
        if (!sl.validate(sr)) {
            sr = sl.readLock(); // 使用悲观锁，会阻塞写锁
            try {
                System.out.println("悲观锁读线程----" + Thread.currentThread().getName());
                currentx = x;
                currenty = y;
            } finally {
                sl.unlockRead(sr);  // 释放悲观读锁
            }
        } else {
            System.out.println("乐观锁读线程----" + Thread.currentThread().getName());
        }
        System.out.println(currentx + "----第二次读取数据----" + currenty);
        return currentx * currenty;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int q = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        move(q, q + 8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        distanceFromOrigin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
