package cn.lxj.java36courses.course14;

/**
 * Singleton3   利用内部类持有的静态方法来实现理论依据是对象初始化过程中隐含的初始化锁
 * description 能够抱枕happen-before，避免指令重排，即构造对象的store能够被保证一定在volatile read之前
 * create by lxj 2018/8/8
 **/
public class Singleton3 {
    private Singleton3() {
    }

    public static Singleton3 getSingleton3() {
        return Holder.singleton3;
    }

    // 利用内部类持有静态对象的方式实现，理论依据是对象初始化过程中隐含的初始化锁
    private static class Holder {
        private static Singleton3 singleton3 = new Singleton3();
    }
}