package cn.lxj.java36courses.course16;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedSample
 * description
 * create by lxj 2018/9/3
 **/
public class StampedSample {
    private final StampedLock sl = new StampedLock();
    void mutate(){
        long stamp = sl.writeLock();
        try {
            // wirte();
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    // ...
}
