package cn.lxj.java36courses.course15;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * PricesInfo
 * description 在getPrice的两个方法中，都添加了读锁。在setPrices方法里增加了写锁
 * create by lxj 2018/8/10
 **/
public class PricesInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;    // 读写锁

    /**
     * 构造函数
     */
    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();// 重入读写锁
    }

    /**
     * 读锁获取price1
     *
     * @return
     */
    public double getPrice1() {
        // 读取资源锁定
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    /**
     * 读锁获取price2
     *
     * @return
     */
    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }


    /**
     * 写锁设置price1和price2
     * @param price1
     * @param price2
     */
    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.printf("%s,Writer: Attempt to modify the prices.\n", Thread.currentThread().getName());
        this.price1 = price1;
        this.price2 = price2;
        System.out.printf("%s,Writer: Prices have been modified.\n", Thread.currentThread().getName());
        lock.writeLock().unlock();
    }
}