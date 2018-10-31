package cn.lxj.java36courses.course15;

/**
 * ThreadSafeSample
 * description 模拟两次对共享状态的操作
 * create by lxj 2018/8/9
 **/
public class ThreadSafeSample {
    public int sharedState; // 共享状态

    public void nonSafeAction() {    // 线程不安全操作
        System.out.println("Thread-number:" + Thread.currentThread().getId());
        while (sharedState < Integer.MAX_VALUE) {
            int former = sharedState++;
            int latter = sharedState;
            if (former != latter - 1) {
                System.out.println("Observed data race,former is " + former + "," + "latter is " + latter);
            } else {
                //System.out.println("eles:former:" + former + ",latter:" + latter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
//        ThreadSafeSample sample2 = new ThreadSafeSample();// 单例才存在线程安全问题，多例不存在，除非共享同一个外部变量

        Thread threadA = new Thread() {
            public void run() {
                sample.nonSafeAction();
            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                sample.nonSafeAction();
            }
        };
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}