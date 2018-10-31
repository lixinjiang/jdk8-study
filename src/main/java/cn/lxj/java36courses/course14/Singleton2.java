package cn.lxj.java36courses.course14;

/**
 * Singleton2
 * description 升级2
 * create by lxj 2018/8/1
 **/
public class Singleton2 {
    private static volatile Singleton2 singleton2 = null;

    private Singleton2() {
    }

    public static Singleton2 getSingleton() {
        if (singleton2 == null) {//尽量避免重复进入同步块
            synchronized (Singleton2.class) {// 同步class块，意味着对同步类方法进行调用
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
