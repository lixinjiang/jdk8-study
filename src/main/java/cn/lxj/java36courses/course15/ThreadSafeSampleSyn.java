package cn.lxj.java36courses.course15;

/**
 * ThreadSafeSample2
 * description      加锁线程安全的操作
 * create by lxj 2018/8/10
 **/
public class ThreadSafeSampleSyn {
    public long sharedState; // 共享状态

    public void safeAction() {//线程安全的操作
        synchronized (this) {
            System.out.println("Thread-number:" + Thread.currentThread().getId());
            while (sharedState < Long.MAX_VALUE) {
                long former = sharedState++;
                long latter = sharedState;
                if (former != latter - 1) {
                    System.out.println("Observed data race,former is " + former + "," + "latter is " + latter);
                } else {
                    //System.out.println("eles:former:" + former + ",latter:" + latter);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSampleSyn sampleSyn = new ThreadSafeSampleSyn();

        Thread threadA = new Thread() {
            @Override
            public void run() {
                sampleSyn.safeAction();
            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                sampleSyn.safeAction();
            }
        };

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}