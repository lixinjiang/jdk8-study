package cn.lxj.java36courses.course16;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * RWSample
 * description 读写锁使用示例，实际开销大
 * create by lxj 2018/9/3
 **/
public class RWSample {
    private final Map<String, String> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(String key) {
        r.lock();// 读锁获取
        System.out.println("读锁锁定！");
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public String put(String key,String entry){
        w.lock();// 获取写锁
        System.out.println("写锁锁定！");
        try {
            return m.put(key, entry);
        }finally {
            w.unlock();
        }
    }
}
